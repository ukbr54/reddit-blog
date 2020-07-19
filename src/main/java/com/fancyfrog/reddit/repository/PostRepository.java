package com.fancyfrog.reddit.repository;

import com.fancyfrog.reddit.model.Post;
import com.fancyfrog.reddit.model.Subreddit;
import com.fancyfrog.reddit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);
    List<Post> findByUser(User user);
}
