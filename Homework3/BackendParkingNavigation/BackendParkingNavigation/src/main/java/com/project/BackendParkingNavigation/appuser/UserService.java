package com.project.BackendParkingNavigation.appuser;

import com.project.BackendParkingNavigation.registration.token.ConfirmationToken;
import com.project.BackendParkingNavigation.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService
{
    private final static String USER_NOT_FOUND_MSG1 = "User with email %s not found";
    private final static String USER_NOT_FOUND_MSG2 = "User %s is not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG1, email)));
    }

    public String singUpUser(AppUser appUser)
    {
        boolean userExists = userRepository.findByEmail(appUser.getEmail()).isPresent();
        if(userExists)
        {
            throw new IllegalStateException("Email already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);

        userRepository.save(appUser);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(30), appUser);

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public int enableUser(String email)
    {
        return userRepository.enableUser(email);
    }

    public Boolean setParking(String username, String parking)
    {
        AppUser appUser = this.userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG2, username)));
        appUser.addParking(parking);
        userRepository.save(appUser);
        return true;
    }

    public Boolean changePassword(String username, String password)
    {
        AppUser appUser = this.userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG2, username)));
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        appUser.setPassword(encodedPassword);

        userRepository.save(appUser);
        return true;
    }
}
