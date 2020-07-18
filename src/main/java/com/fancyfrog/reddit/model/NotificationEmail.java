package com.fancyfrog.reddit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEmail {

    private String subject;
    private String recipient;
    private String body;
}
