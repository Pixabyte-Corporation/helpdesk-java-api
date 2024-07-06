package com.pixabyte.helpdeskapi.authentication.application;

import com.pixabyte.helpdeskapi.authentication.domain.*;
import org.springframework.stereotype.Service;

@Service
public class SignupUserUseCase {
    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final RoleRepository roleRepository;
    public SignupUserUseCase(
            UserRepository userRepository,
            OrganizationRepository organizationRepository,
            RoleRepository roleRepository
    ) {
        this.userRepository = userRepository;
        this.organizationRepository = organizationRepository;
        this.roleRepository = roleRepository;
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
        var organizationOpt = organizationRepository.findOrganizationById(request.id());
        if (organizationOpt.isEmpty()) {
            throw new OrganizationNotFound();
        }
        var roleOpt = roleRepository.findRoleById(request.id());
        if (roleOpt.isEmpty()) {
            throw new RoleNotFound();
        }
        var user = User.builder()
                .id(request.id())
                .organizationId(request.organizationId())
                .roleId(request.roleId())
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .build();
        userRepository.save(user);
    }

}
