package com.asset.foundation.configuration;

import com.asset.foundation.user.UserService;
import com.asset.foundation.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserSecurityService implements UserDetailsService {

    private final UserService userService;

    public UserSecurityService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserName(username);

        if (null == user) {
            throw new UsernameNotFoundException("Username not found");
        }

        return new CustomUserDetails(user, user.getAuthorities());
    }


}