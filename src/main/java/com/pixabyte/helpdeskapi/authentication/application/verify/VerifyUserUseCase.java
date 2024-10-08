package com.pixabyte.helpdeskapi.authentication.application.verify;

import com.pixabyte.helpdeskapi.authentication.domain.exceptions.UserNotFound;
import com.pixabyte.helpdeskapi.authentication.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class VerifyUserUseCase {
    private final UserRepository userRepository;

    public VerifyUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(VerifyUserCommand command) {
        var userOpt = userRepository.findById(command.userId());
        if (userOpt.isEmpty()) {
            throw new UserNotFound();
        }
        var user = userOpt.get();
        user = user.toBuilder()
                .verifiedAt(LocalDateTime.now(ZoneId.of("UTC")))
                .build();
        userRepository.save(user);
    }

}
