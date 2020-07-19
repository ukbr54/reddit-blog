package com.fancyfrog.reddit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {

    private Long postId;
    private String subredditName;
    private String postName;
    private String url;
    private String description;
}
