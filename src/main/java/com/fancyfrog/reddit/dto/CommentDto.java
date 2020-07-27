package com.fancyfrog.reddit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private Long id;
    private Long postId;
    private Instant createdDate;
    private String text;
    private String userName;
}
