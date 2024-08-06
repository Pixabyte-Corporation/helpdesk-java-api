package com.pixabyte.helpdeskapi.authentication.infrastructure.controllers;

import com.pixabyte.helpdeskapi.authentication.application.login.LoginUserRequest;
import com.pixabyte.helpdeskapi.authentication.application.login.LoginUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginPostController {

    private final LoginUserUseCase loginUserUseCase;

    public LoginPostController(LoginUserUseCase loginUserUseCase) {
        this.loginUserUseCase = loginUserUseCase;
    }

    @PostMapping("/authentication/authenticate")
    public ResponseEntity<LoginPostResponse> login(@RequestBody LoginPostRequest request) {
        var loginUserRequest = new LoginUserRequest(
                request.email(),
                request.password()
        );
        var tokenResponse = loginUserUseCase.execute(loginUserRequest);
        var response = new LoginPostResponse(tokenResponse.token());
        return ResponseEntity.ok(response);
    }

}
