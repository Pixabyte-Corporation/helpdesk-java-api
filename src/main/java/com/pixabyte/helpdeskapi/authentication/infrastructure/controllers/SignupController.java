package com.pixabyte.helpdeskapi.authentication.infrastructure.controllers;

import com.pixabyte.helpdeskapi.authentication.application.SignupUserRequest;
import com.pixabyte.helpdeskapi.authentication.application.SignupUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {

    private final SignupUserUseCase signupUserUseCase;

    public SignupController(SignupUserUseCase signupUserUseCase) {
        this.signupUserUseCase = signupUserUseCase;
    }

    @PostMapping("/authentication/signup")
    public ResponseEntity <Void> signup(@RequestBody SignupPostRequest body) {
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
