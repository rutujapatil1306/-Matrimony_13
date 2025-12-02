package com.spring.jwt.utils;

import com.spring.jwt.service.security.UserDetailsCustom;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtil {

    private SecurityUtil() {}

    public static Integer getCurrentUserId() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (authentication == null) {
            throw new IllegalStateException("No authenticated user found");
        }

        // Case 1: We stored userId in details
        Object details = authentication.getDetails();
        if (details instanceof Integer id) {
            return id;
        }

        // Case 2 (optional): userId is inside your custom UserDetails
        if (authentication.getPrincipal() instanceof UserDetailsCustom customUser) {
            return customUser.getUserId(); // adjust method name as per your class
        }

        throw new IllegalStateException("User ID not found in security context");
    }
}
