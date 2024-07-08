package com.pixabyte.helpdeskapi.authentication.infrastructure.security.config;

import com.pixabyte.helpdeskapi.authentication.domain.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Key;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final String jwtSecret;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    public JwtAuthenticationFilter(
            @Value("${helpdesk.security.jwt.secret}") String jwtSecret,
            UserRepository userRepository) {

        this.jwtSecret = jwtSecret;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /**
         * 1. Obtener la cabecera http
         * 2. si no esta presente, terminamos.
         * 3. Si el token no tiene el formato adecaduo, terminamos
         * 4. Obtener el token
         * 5. Extraer datos del token
         * 6. Crear UserDetails - necesitamos información del token
         * 7. Buscar al usuario para crear el userDetails
         * 8. Agregar el userDetails al contexto de Spring Security
         * */
        String tokenHeader = request.getHeader("Authorization");
        if (Objects.isNull(tokenHeader) || !tokenHeader.startsWith("Bearer ")) {
            logger.warn("No hay token de autenticación");
            filterChain.doFilter(request, response);
            return;
        }
        String jwtToken = tokenHeader.substring(7);
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
        var userId = extractUserId(claims, Claims::getSubject);
        var userOpt = userRepository.findById(UUID.fromString(userId));
        if (userOpt.isEmpty()) {
            logger.error("No existe el usuario propietario del token");
            filterChain.doFilter(request, response);
            return;
        }
        var user = userOpt.get();
        var userDetails = HelpDeskUserDetails.builder()
                .id(user.getId())
                .organizationId(user.getOrganizationId())
                .email(user.getEmail())
                .password(null)
                .build();
        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(upat);
        filterChain.doFilter(request, response);
    }

    private String extractUserId(Claims claims, Function<Claims, String> claimsResolver) {
        return claimsResolver.apply(claims);
    }

    private Key getSigninKey() {
        byte[] secretBytes = Decoders.BASE64URL.decode(jwtSecret);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}
