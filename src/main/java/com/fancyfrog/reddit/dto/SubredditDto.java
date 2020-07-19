package com.fancyfrog.reddit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubredditDto {

    private Long id;
    private String name;
    private String description;
    private Integer numberOfPosts;
}
