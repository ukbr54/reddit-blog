package com.fancyfrog.reddit.service;

import com.fancyfrog.reddit.dto.AuthenticationResponse;
import com.fancyfrog.reddit.dto.LoginRequest;
import com.fancyfrog.reddit.dto.RegisterRequest;
import com.fancyfrog.reddit.exception.RedditBlogException;
import com.fancyfrog.reddit.model.NotificationEmail;
import com.fancyfrog.reddit.model.User;
import com.fancyfrog.reddit.repository.UserRepository;
import com.fancyfrog.reddit.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Transactional
    public void signup(RegisterRequest registerRequest){
        User user = User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail()).build();

        final String token = generateVerificationToken(user);
        user.setToken(token);

        user = userRepository.save(user);

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

    public AuthenticationResponse login(LoginRequest loginRequest) {
        final Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        final String token = jwtProvider.generateToken(authenticate);

        return new AuthenticationResponse(token,loginRequest.getUsername());
    }
}
