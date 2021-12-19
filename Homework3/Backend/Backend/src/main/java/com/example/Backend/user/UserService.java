package com.example.Backend.user;

import com.example.Backend.registration.token.ConfirmationToken;
import com.example.Backend.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final static String USER_NOT_FOUND_MSG2 = "User with id %s not found";
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

    public String singUpUser(User user)
    {
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();
        if(userExists)
        {
            throw new IllegalStateException("Email already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(30), user);

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public int enableUser(String email)
    {
        return userRepository.enableUser(email);
    }

    public Boolean setParking(Long id, String parking)
    {
         User user = this.userRepository.findById(id).orElseThrow(()
                 -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG2, id)));
         user.addParking(parking);
         userRepository.save(user);
         return true;
    }
}
