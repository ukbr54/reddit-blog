package com.fancyfrog.reddit.repository;

import com.fancyfrog.reddit.model.Comment;
import com.fancyfrog.reddit.model.Post;
import com.fancyfrog.reddit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
