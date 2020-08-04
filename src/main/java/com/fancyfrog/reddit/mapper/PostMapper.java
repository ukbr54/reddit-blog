package com.fancyfrog.reddit.mapper;

import com.fancyfrog.reddit.dto.PostRequest;
import com.fancyfrog.reddit.dto.PostResponse;
import com.fancyfrog.reddit.model.*;
import com.fancyfrog.reddit.repository.CommentRepository;
import com.fancyfrog.reddit.repository.VoteRepository;
import com.fancyfrog.reddit.service.AuthService;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    private @Autowired CommentRepository commentRepository;
    private @Autowired VoteRepository voteRepository;
    private @Autowired AuthService authService;

    @Mapping(target = "createdDate",expression = "java(java.time.Instant.now())")
    @Mapping(target = "description",source = "postRequest.description")
    @Mapping(target = "subreddit",source = "subreddit")
    @Mapping(target = "user",source = "user")
    public abstract Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id",source = "postId")
    @Mapping(target = "subredditName",source = "subreddit.name")
    @Mapping(target = "userName",source = "user.username")
    @Mapping(target = "commentCount",source = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    @Mapping(target = "upVote", expression = "java(isPostUpVoted(post))")
    @Mapping(target = "downVote", expression = "java(isPostDownVoted(post))")
    public abstract PostResponse mapToDto(Post post);

    Integer commentCount(Post post){
        return commentRepository.findByPost(post).size();
    }

    String getDuration(Post post){
        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }

    boolean isPostUpVoted(Post post) {
        return checkVoteType(post, VoteType.UPVOTE);
    }

    boolean isPostDownVoted(Post post) {
        return checkVoteType(post, VoteType.DOWNVOTE);
    }

    private boolean checkVoteType(Post post, VoteType voteType){
        if(authService.isLoggedIn()){
            final Optional<Vote> voteForPostByUser  =
                    voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
            return voteForPostByUser.filter(vote -> vote.getVoteType().equals(voteType)).isPresent();
        }else{
            return false;
        }
    }
}
