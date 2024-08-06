package com.pixabyte.helpdeskapi.authentication.application.signup;

import com.pixabyte.helpdeskapi.authentication.domain.*;
import com.pixabyte.helpdeskapi.authentication.domain.exceptions.OrganizationNotFound;
import com.pixabyte.helpdeskapi.authentication.domain.exceptions.RoleNotFound;
import com.pixabyte.helpdeskapi.authentication.domain.exceptions.UserAlreadyExists;
import com.pixabyte.helpdeskapi.authentication.domain.repositories.OrganizationRepository;
import com.pixabyte.helpdeskapi.authentication.domain.repositories.RoleRepository;
import com.pixabyte.helpdeskapi.authentication.domain.repositories.UserRepository;
import com.pixabyte.helpdeskapi.shared.domain.EventBus;
import org.springframework.stereotype.Service;

@Service
public class SignupUserUseCase {
    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final EventBus eventBus;

    public SignupUserUseCase(
            UserRepository userRepository,
            OrganizationRepository organizationRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder, EventBus eventBus) {
        this.userRepository = userRepository;
        this.organizationRepository = organizationRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.eventBus = eventBus;
    }

    /**
     * 1. Validar si el correo ya esta regsitrado, si lo esta regresar un error
     * 2. Validar si la organización existe
     * 3. Validar si el role existe
     * 4. Si ya esta registrado, lanzar un error
     * 5. Crear un User
     * 4. Guardar el nuevo User
     * */
    public void execute(SignupUserRequest request) {
        var userOpt = userRepository.findUserByEmail(request.email());
        if (userOpt.isPresent()) {
            throw new UserAlreadyExists("El usuario ya existe con ese correo");
        }
        var organizationOpt = organizationRepository.findOrganizationById(request.organizationId());
        if (organizationOpt.isEmpty()) {
            throw new OrganizationNotFound();
        }
        var roleOpt = roleRepository.findRoleById(request.roleId());
        if (roleOpt.isEmpty()) {
            throw new RoleNotFound();
        }
        var hashedPassword = passwordEncoder.encode(request.password());
        var user = User.createUser(
                request.id(),
                request.email(),
                hashedPassword,
                request.name(),
                request.organizationId(),
                request.roleId()
        );
        userRepository.save(user);
        user.pullEvents().forEach(eventBus::publish);
    }

}
