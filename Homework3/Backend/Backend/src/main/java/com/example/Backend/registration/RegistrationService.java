package com.example.Backend.registration;

import com.example.Backend.registration.token.ConfirmationToken;
import com.example.Backend.registration.token.ConfirmationTokenService;
import com.example.Backend.user.User;
import com.example.Backend.user.UserRole;
import com.example.Backend.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService
{
    private final UserService userService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;

    public String register(RegistrationRequest request)
    {
        boolean isValid = emailValidator.test(request.getEmail());
        if(!isValid)
        {
            throw new IllegalStateException("Invalid email");
        }
        return userService.singUpUser(new User(
                request.getUsername(), request.getEmail(), request.getPassword(), UserRole.USER, new String[0]));
    }

    @Transactional
    public String confirmToken(String token)
    {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("Token not found"));

        if (confirmationToken.getTimeConfirmed() != null)
        {
            throw new IllegalStateException("Email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now()))
        {
            throw new IllegalStateException("Token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(confirmationToken.getUser().getEmail());
        return "Confirmed";
    }
}
