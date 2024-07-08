package com.pixabyte.helpdeskapi.authentication.application;

import com.pixabyte.helpdeskapi.authentication.domain.*;
import org.springframework.stereotype.Service;

@Service
public class LoginUserUseCase {

    private final UserRepository userRepository;
    private final TokenGenerator tokenGenerator;
    private final PasswordEncoder passwordEncoder;
    public LoginUserUseCase(
            UserRepository userRepository,
            TokenGenerator tokenGenerator, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenGenerator = tokenGenerator;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 1. Buscar si el email ya esta registrado en nuestra base de datos, en caso de no existir error
     * 2. Validar si la contraseña recibida coincida con la contraseña en base de datos
     * 3. En caso de que no, regresar un error
     * 4. Generar un token JWT
     * 5. Regresar el token
     * */
    public LoginUserResponse execute(LoginUserRequest request) {
        var userOpt = userRepository.findUserByEmail(request.email());
        if (userOpt.isEmpty()) {
            throw new UserNotFound();
        }

        var user = userOpt.get();

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new AuthenticationFailed();
        }

        var token = tokenGenerator.generate(user);
        return new LoginUserResponse(token);
    }

}
