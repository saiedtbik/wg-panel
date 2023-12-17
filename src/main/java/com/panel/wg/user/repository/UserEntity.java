package com.panel.wg.user.repository;

import com.panel.wg.client.dataaccess.entities.ClientEntity;
import com.panel.wg.user.domain.entities.Role;
import jakarta.persistence.*;
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
    @Enumerated(EnumType.STRING)
    private Role role;

    private String mobileNum;
    private String email;
    private LocalDateTime createOn;
    private boolean enabled;

}
