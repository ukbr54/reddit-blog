package com.fancyfrog.reddit.repository;

import com.fancyfrog.reddit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByToken(String token);
}
