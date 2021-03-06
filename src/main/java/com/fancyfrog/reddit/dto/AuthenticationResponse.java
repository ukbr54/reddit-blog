package com.fancyfrog.reddit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {

    private String authenticationToken;
    private String refreshToken;
    private Instant expireAt;
    private String username;
}
