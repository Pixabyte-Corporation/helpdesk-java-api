package com.pixabyte.helpdeskapi.authentication.infrastructure.controllers;

import com.pixabyte.helpdeskapi.authentication.application.SignupUserRequest;
import com.pixabyte.helpdeskapi.authentication.application.SignupUserUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {

    private final SignupUserUseCase signupUserUseCase;
    private final Logger logger = LoggerFactory.getLogger(SignupController.class);

    public SignupController(SignupUserUseCase signupUserUseCase) {
        this.signupUserUseCase = signupUserUseCase;
    }

    @PostMapping("/authentication/signup")
    public ResponseEntity <Void> signup(@RequestBody SignupPostRequest body) {
        logger.info("Starting Signup - organizationId={} email={}, password={} id={}, roleId={}",
                body.organizationId(),
                body.email(),
                body.password(),
                body.id(),
                body.roleId()
                );
        signupUserUseCase.execute(new SignupUserRequest(
                body.organizationId(),
                body.roleId(),
                body.id(),
                body.email(),
                body.password(),
                body.name()
        ));
        return ResponseEntity.ok().build();
    }

}
