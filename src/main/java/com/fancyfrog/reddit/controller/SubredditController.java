package com.fancyfrog.reddit.controller;

import com.fancyfrog.reddit.dto.SubredditDto;
import com.fancyfrog.reddit.service.SubredditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */

@RestController
@RequestMapping("/api/subreddit")
@Slf4j
@AllArgsConstructor
public class SubredditController {

    private final SubredditService subredditService;

    @PostMapping
    public ResponseEntity<SubredditDto> createSubreddit(@RequestBody SubredditDto subredditDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(subredditService.save(subredditDto));
    }

    @GetMapping
    public ResponseEntity<List<SubredditDto>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body( subredditService.getAll());
    }
}
