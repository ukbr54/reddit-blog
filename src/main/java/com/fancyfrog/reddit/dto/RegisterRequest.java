package com.fancyfrog.reddit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String email;
    private String username;
    private String password;
}
