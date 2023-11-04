package com.panel.wg.auth.util;

import com.panel.wg.usermanagement.domain.entities.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


public class SecurityUtils {
    public static boolean userNotAuthenticatedYet() {
        return Objects.isNull(SecurityContextHolder.getContext().getAuthentication());
    }

    public static Set<SimpleGrantedAuthority> toAuthorities(Role role) {
       return role.getAuthorities()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
    }
}
