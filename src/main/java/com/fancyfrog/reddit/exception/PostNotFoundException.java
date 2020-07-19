package com.fancyfrog.reddit.exception;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */
public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String message) {
        super(message);
    }
}
