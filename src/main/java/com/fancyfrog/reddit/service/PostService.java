package com.fancyfrog.reddit.service;

import com.fancyfrog.reddit.dto.PostRequest;
import com.fancyfrog.reddit.dto.PostResponse;
import com.fancyfrog.reddit.exception.PostNotFoundException;
import com.fancyfrog.reddit.exception.SubredditNotFoundException;
import com.fancyfrog.reddit.mapper.PostMapper;
import com.fancyfrog.reddit.model.Post;
import com.fancyfrog.reddit.model.Subreddit;
import com.fancyfrog.reddit.model.User;
import com.fancyfrog.reddit.repository.PostRepository;
import com.fancyfrog.reddit.repository.SubredditRepository;
import com.fancyfrog.reddit.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final SubredditRepository subredditRepository;
    private final AuthService authService;
    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void save(PostRequest postRequest) {
        final Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));

        final User currentUser = authService.getCurrentUser();
        postRepository.save(postMapper.map(postRequest,subreddit,currentUser));
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id){
        final Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts(){
        return postRepository.findAll().stream().map(postMapper::mapToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subredditId){
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SubredditNotFoundException(subredditId.toString()));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user).stream().map(postMapper::mapToDto).collect(Collectors.toList());
    }
}
