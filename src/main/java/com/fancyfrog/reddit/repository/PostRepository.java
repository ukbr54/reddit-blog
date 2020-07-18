package com.fancyfrog.reddit.repository;

import com.fancyfrog.reddit.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */
public interface PostRepository extends JpaRepository<Post,Long> {
}
