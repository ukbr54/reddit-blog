package com.fancyfrog.reddit.service;

import com.fancyfrog.reddit.exception.RedditBlogException;
import com.fancyfrog.reddit.model.RefreshToken;
import com.fancyfrog.reddit.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken generateRefreshToken(){
        RefreshToken token  = new RefreshToken();
        token.setToken(UUID.randomUUID().toString())
                .setCreatedDate(Instant.now());

        return refreshTokenRepository.save(token);
    }

    void validateRefreshToken(String token) {
        refreshTokenRepository.findByToken(token).orElseThrow(() -> new RedditBlogException("Invalid refresh Token"));
    }

    public void deleteRefreshToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }
}
