package com.pixabyte.helpdeskapi.authentication.application;

import com.pixabyte.helpdeskapi.authentication.domain.*;
import com.pixabyte.helpdeskapi.shared.domain.EventBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static com.pixabyte.helpdeskapi.authentication.application.SignupUserRequestMother.randomRequest;
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
    @Mock
    private EventBus eventBus;
    private SignupUserUseCase signupUserUseCase;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        signupUserUseCase = new SignupUserUseCase(
                userRepository,
                organizationRepository,
                roleRepository,
                passwordEncoder,
                eventBus);
    }

    @Test
    public void shouldRegisterUser() {
        SignupUserRequest request = randomRequest();
        var organization = Organization
                .builder()
                .id(request.organizationId())
                .build();
        var role = Role.builder()
                .id(request.roleId())
                .build();

        when(userRepository.findUserByEmail(request.email())).thenReturn(Optional.empty());
        when(organizationRepository.findOrganizationById(any())).thenReturn(Optional.of(organization));
        when(roleRepository.findRoleById(any())).thenReturn(Optional.of(role));
        when(passwordEncoder.encode(any())).thenReturn("superEncoeedPassword");

        signupUserUseCase.execute(request);

        verify(userRepository).save(any(User.class));
        verify(eventBus).publish(any(UserRegisteredEvent.class));
    }


    @Test
    void shouldThrowsUserAlreadyExistWhenUserEmailAlreadyExistsInDatabase() {
        SignupUserRequest request = randomRequest();
        User databaseUser = User.builder()
                .email(request.email())
                .build();

        when(userRepository.findUserByEmail(request.email())).thenReturn(Optional.of(databaseUser));
        assertThrows(UserAlreadyExists.class, () -> signupUserUseCase.execute(request));
        verify(userRepository, never()).save(any());
        verify(eventBus, never()).publish(any());
    }

    @Test
    void shouldThrowsOrganizationNotFoundWhenRequestOrganizationIdDoesntExists() {
        SignupUserRequest request = randomRequest();
        when(userRepository.findUserByEmail(request.email())).thenReturn(Optional.empty());
        when(organizationRepository.findOrganizationById(any())).thenReturn(Optional.empty());
        assertThrows(OrganizationNotFound.class, () -> signupUserUseCase.execute(request));
        verify(userRepository, never()).save(any());
        verify(eventBus, never()).publish(any());
    }

    @Test
    void shouldThrowsRoleNotFoundWhenRequestRoleIdDoesntExists() {
        SignupUserRequest request = randomRequest();
        var organization = Organization
                .builder()
                .id(request.organizationId())
                .build();
        when(userRepository.findUserByEmail(request.email())).thenReturn(Optional.empty());
        when(organizationRepository.findOrganizationById(any())).thenReturn(Optional.of(organization));
        when(roleRepository.findRoleById(any(UUID.class))).thenReturn(Optional.empty());
        assertThrows(RoleNotFound.class, () -> signupUserUseCase.execute(request));
        verify(userRepository, never()).save(any());
        verify(eventBus, never()).publish(any());
    }

}