package com.pixabyte.helpdeskapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationController {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @PostMapping("authenticate")
    public ResponseEntity<AuthenticateResponse> authenticate(@RequestBody AuthenticateRequest body) {
        logger.info("Hemos recibido los datos correctamente: {}", body.email());
        var email = body.email();
        var password = body.password();
        var userCredentialsOpt = userCredentialsRepository.findByEmail(email);
        if (userCredentialsOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        var user = userCredentialsOpt.get();
        if (!password.equals(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        var response = new AuthenticateResponse(email+password);
        return ResponseEntity.status(HttpStatus.OK).body(response);

        /**
         * 1. Buscar al usuario por email
         * 2 Si el usuario no existe, regresar un error
         * 3. Si el usuario existe, comparar las contraseñas
         * 4. Si las contraseñas no son iguales, regresamos un error
         * 5. Si las contraseñas son iguales, creamos un token jwt
         * 6. Regresamos el token generado
        * */

    }

    @PostMapping("signup")
    public ResponseEntity<Void> signup(@RequestBody SignupRequest body) {
        UserCredentials userCredentials = new UserCredentials(
                body.userId(),
                body.name(),
                body.email(),
                body.password(),
                body.organizationId(),
                body.roleId(),
                null,
                null);
        userCredentialsRepository.save(userCredentials);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
