package com.panel.wg.user.domain.entities;

import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private Long id;
    private String apiKey;
    private String secretKey;
    private String fullName;
    private Role role;
    private String mobileNum;
    private String email;
    private LocalDateTime createOn;
    private boolean enabled;

    public User(Long id) {
        this.id = id;
    }

    public Set<Authority> getAuthorities() {
        return role.getAuthorities();
    }


}
