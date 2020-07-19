package com.fancyfrog.reddit.exception;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */
public class SubredditNotFoundException extends RuntimeException{

    public SubredditNotFoundException(String message){
        super(message);
    }
}
