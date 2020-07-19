package com.fancyfrog.reddit.mapper;

import com.fancyfrog.reddit.dto.PostRequest;
import com.fancyfrog.reddit.dto.PostResponse;
import com.fancyfrog.reddit.model.Post;
import com.fancyfrog.reddit.model.Subreddit;
import com.fancyfrog.reddit.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "createdDate",expression = "java(java.time.Instant.now())")
    @Mapping(target = "subreddit",source = "subreddit")
    @Mapping(target = "user",source = "user")
    @Mapping(target = "description",source = "postRequest.description")
    Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id",source = "postId")
    @Mapping(target = "subredditName",source = "subreddit.name")
    @Mapping(target = "userName",source = "user.username")
    PostResponse mapToDto(Post post);
}
