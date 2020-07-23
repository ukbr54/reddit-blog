package com.fancyfrog.reddit.repository;

import com.fancyfrog.reddit.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);
}
