package com.fancyfrog.reddit.model;

import com.fancyfrog.reddit.exception.RedditBlogException;

import java.util.Arrays;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */
public enum VoteType {

    UPVOTE(1), DOWNVOTE(-1);

    private int direction;

    VoteType(int direction){ }

    public static VoteType lookup(Integer direction){
        return Arrays.stream(VoteType.values())
                .filter(value -> value.getDirection().equals(direction))
                .findAny()
                .orElseThrow(() -> new RedditBlogException("Vote not found"));
    }

    public Integer getDirection() {
        return direction;
    }
}
