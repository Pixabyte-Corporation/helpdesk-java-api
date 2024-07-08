package com.pixabyte.helpdeskapi;

import com.pixabyte.helpdeskapi.authentication.infrastructure.security.config.HelpDeskUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HelpDeskApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelpDeskApplication.class, args);
    }
    private final Logger logger = LoggerFactory.getLogger(HelpDeskApplication.class);

    @GetMapping("/greeting")
    public String hello(Authentication authentication) {
        HelpDeskUserDetails userDetails = (HelpDeskUserDetails) authentication.getPrincipal();
        logger.warn("El usuario autenticado tiene estos datos: id:{} email:{} organizationId:{}",
                userDetails.getId().toString(),
                userDetails.getEmail(),
                userDetails.getOrganizationId().toString());
        return "Hola";
    }

}
