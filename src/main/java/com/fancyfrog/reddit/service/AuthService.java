package com.fancyfrog.reddit.service;

import com.fancyfrog.reddit.dto.RegisterRequest;
import com.fancyfrog.reddit.exception.RedditBlogException;
import com.fancyfrog.reddit.model.NotificationEmail;
import com.fancyfrog.reddit.model.User;
import com.fancyfrog.reddit.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final MailService mailService;

    @Transactional
    public void signup(RegisterRequest registerRequest){
        User user = User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail()).build();

        user = userRepository.save(user);

        final String token = generateVerificationToken(user);
        user.setToken(token);

        mailService.sendMail(NotificationEmail.builder()
                .subject("Please Activate your Account")
                .recipient(user.getEmail())
                .body("Thank you for signing up to reddit, please click on the below url to activate your account: " +
                       "http://localhost:8080/api/auth/account-verification/" + token)
                .build());
    }

    private String generateVerificationToken(User user) {
        return UUID.randomUUID().toString();
    }

    @Transactional
    public void verifyAccount(String token){
        final Optional<User> isUser = userRepository.findByToken(token);
        final User user = isUser.orElseThrow(() -> new RedditBlogException("Invalid Token"));
        user.setEnabled(Boolean.TRUE);
        userRepository.save(user);
    }
}
