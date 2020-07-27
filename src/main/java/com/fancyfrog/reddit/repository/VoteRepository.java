package com.fancyfrog.reddit.repository;

import com.fancyfrog.reddit.model.Post;
import com.fancyfrog.reddit.model.User;
import com.fancyfrog.reddit.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */
public interface VoteRepository extends JpaRepository<Vote,Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
