package com.asset.foundation.configuration;

import com.asset.foundation.subAdmin.SubAdmin;
import com.asset.foundation.subAdmin.SubAdminRepository;
import com.asset.foundation.user.Status;
import com.asset.foundation.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityUtility {

    @Autowired
    SubAdminRepository subAdminRepository;


    // public User getCurrentUser() {
    // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    // User user = (User) auth.getPrincipal();
    // return user;
    // }

    public User getSecurity() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        if (principal instanceof CustomUserDetails) {
            return ((CustomUserDetails) principal).getUser();
        }
        return null;
    }

    public SubAdmin getCurrentPlayer() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        if (principal instanceof CustomUserDetails) {
            User user=((CustomUserDetails) principal).getUser();
            SubAdmin subAdmin = subAdminRepository.findByUserAndStatus(user, Status.ACTIVE);
            return subAdmin;
        }
        return null;
    }

}
