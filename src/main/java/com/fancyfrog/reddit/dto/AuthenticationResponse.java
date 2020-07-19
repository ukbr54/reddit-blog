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
public class AuthenticationResponse {

    private String authenticationToken;
    private String username;
}
