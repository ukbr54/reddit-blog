package com.fancyfrog.reddit.repository;

import com.fancyfrog.reddit.model.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */
public interface SubredditRepository extends JpaRepository<Subreddit,Long> {
    Optional<Subreddit> findByName(String subredditName);
}
