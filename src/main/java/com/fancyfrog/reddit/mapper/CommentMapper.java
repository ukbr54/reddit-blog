package com.fancyfrog.reddit.mapper;

import com.fancyfrog.reddit.dto.CommentDto;
import com.fancyfrog.reddit.model.Comment;
import com.fancyfrog.reddit.model.Post;
import com.fancyfrog.reddit.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "text",source = "commentDto.text")
    @Mapping(target = "createdDate",expression = "java(java.time.Instant.now())")
    @Mapping(target = "post",source = "post")
    @Mapping(target = "user",source = "user")
    Comment map(CommentDto commentDto, Post post, User user);

    @Mapping(target = "postId",expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "userName",expression = "java(comment.getUser().getUsername())")
    CommentDto mapToDto(Comment comment);
}
