package com.fancyfrog.reddit.service;

import com.fancyfrog.reddit.model.User;
import com.fancyfrog.reddit.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> isUser = userRepository.findByUsername(username);
        final User user = isUser.orElseThrow(() -> new UsernameNotFoundException("No user Found with username : " + username));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),user.isEnabled(),
                true,true,true,getAuthorities("USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role){
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
