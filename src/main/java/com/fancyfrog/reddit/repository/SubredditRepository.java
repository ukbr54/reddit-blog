package com.fancyfrog.reddit.repository;

import com.fancyfrog.reddit.model.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */
public interface SubredditRepository extends JpaRepository<Subreddit,Long> {
}
