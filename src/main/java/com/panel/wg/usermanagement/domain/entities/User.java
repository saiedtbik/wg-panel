package com.panel.wg.usermanagement.domain.entities;

import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

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
    private LocalDateTime createOn;
    private boolean enabled;

    public Set<Authority> getAuthorities() {
        return role.getAuthorities();
    }


}
