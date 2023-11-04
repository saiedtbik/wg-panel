package com.panel.wg.usermanagement.repository;

import com.panel.wg.usermanagement.domain.entities.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "t_user")
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String apiKey;
    private String secretKey;
    private String fullName;
    private Role role;
    private LocalDateTime createOn;
    private boolean enabled;
}
