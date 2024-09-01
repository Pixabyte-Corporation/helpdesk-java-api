package com.pixabyte.helpdeskapi.authentication.infrastructure.security.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class MDCFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(MDCFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
                HelpDeskUserDetails userDetails = (HelpDeskUserDetails) authentication.getPrincipal();
                MDC.put("userId", userDetails.getId().toString());
            }
            MDC.put("method", request.getMethod());
            MDC.put("path", request.getRequestURI());
            filterChain.doFilter(request, response);
            MDC.put("status", String.valueOf(response.getStatus()));
        } finally {
            long duration = System.currentTimeMillis() -startTime;
            MDC.put("durationMs", String.valueOf(duration));
            logger.info("Request completed");
            MDC.clear();
        }
    }
}
