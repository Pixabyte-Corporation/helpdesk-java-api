package com.pixabyte.helpdeskapi.authentication.application;

import com.pixabyte.helpdeskapi.authentication.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SignupUserUseCaseTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private OrganizationRepository organizationRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    private SignupUserUseCase signupUserUseCase;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        signupUserUseCase = new SignupUserUseCase(
                userRepository,
                organizationRepository,
                roleRepository,
                passwordEncoder
        );
    }

    @Test
    void shouldThrowsUserAlreadyExistWhenUserEmailAlreadyExistsInDatabase() {
        UUID organizationId = UUID.randomUUID();
        UUID roleId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        String email = "pepe@gmail.com";
        String password = "awesomepass";
        String name = "Pepe";
        SignupUserRequest request = new SignupUserRequest(
                organizationId,
                roleId,
                userId,
                email,
                password,
                name
        );
        User databaseUser = User.builder()
                .email(email)
                .build();

        when(userRepository.findUserByEmail(email)).thenReturn(Optional.of(databaseUser));
        assertThrows(UserAlreadyExists.class, () -> signupUserUseCase.execute(request));
        verify(userRepository, never()).save(any());
    }

    @Test
    void shouldThrowsOrganizationNotFoundWhenRequestOrganizationIdDoesntExists() {
        UUID organizationId = UUID.randomUUID();
        UUID roleId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        String email = "pepe@gmail.com";
        String password = "awesomepass";
        String name = "Pepe";
        SignupUserRequest request = new SignupUserRequest(
                organizationId,
                roleId,
                userId,
                email,
                password,
                name
        );
        Organization org = Organization.builder()
                .id(organizationId)
                .build();

        when(userRepository.findUserByEmail(email)).thenReturn(Optional.empty());
        when(organizationRepository.findOrganizationById(any())).thenReturn(Optional.empty());
        assertThrows(OrganizationNotFound.class, () -> signupUserUseCase.execute(request));
        verify(userRepository, never()).save(any());
    }

}