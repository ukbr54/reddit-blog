package com.fancyfrog.reddit.service;

import com.fancyfrog.reddit.dto.CommentDto;
import com.fancyfrog.reddit.exception.PostNotFoundException;
import com.fancyfrog.reddit.mapper.CommentMapper;
import com.fancyfrog.reddit.model.Comment;
import com.fancyfrog.reddit.model.NotificationEmail;
import com.fancyfrog.reddit.model.Post;
import com.fancyfrog.reddit.model.User;
import com.fancyfrog.reddit.repository.CommentRepository;
import com.fancyfrog.reddit.repository.PostRepository;
import com.fancyfrog.reddit.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */

@Service
@AllArgsConstructor
public class CommentService {

    private static final String POST_URL = "";
    private final PostRepository postRepository;
    private final CommentMapper commentMapper;
    private final AuthService authService;
    private final CommentRepository commentRepository;
    private final MailService mailService;
    private final MailContentBuilder mailContentBuilder;
    private final UserRepository userRepository;

    public void save(CommentDto commentDto){
        final Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentDto.getPostId().toString()));

        final Comment comment = commentMapper.map(commentDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

        final String message = mailContentBuilder.build(post.getUser().getUsername() + " posted a comment on your post." + POST_URL);
        sendCommentNotification(message,post.getUser());

    }

    private void sendCommentNotification(String message, User user){
        mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post",user.getEmail(),message));
    }

    public List<CommentDto> getAllCommentsForPost(Long postId){
        final Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId.toString()));

        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper :: mapToDto).collect(Collectors.toList());
    }

    public List<CommentDto> getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));

        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
