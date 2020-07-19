package com.fancyfrog.reddit.service;

import com.fancyfrog.reddit.dto.SubredditDto;
import com.fancyfrog.reddit.exception.RedditBlogException;
import com.fancyfrog.reddit.mapper.SubredditMapper;
import com.fancyfrog.reddit.model.Subreddit;
import com.fancyfrog.reddit.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class SubredditService {

    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto){
        final Subreddit subreddit = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
        subredditDto.setId(subreddit.getId());
        return subredditDto;
    }

    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll().stream().map(subredditMapper :: mapSubredditToDto).collect(Collectors.toList());
    }

    public SubredditDto getSubreddit(Long id) {
        final Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new RedditBlogException("No Subreddit Found with id: " + id));
        return subredditMapper.mapSubredditToDto(subreddit);
    }
}
