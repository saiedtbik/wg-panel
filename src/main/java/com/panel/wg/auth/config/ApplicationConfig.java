package com.panel.wg.auth.config;


import com.panel.wg.auth.dto.AuthUser;
import com.panel.wg.user.applicationservice.UserApplicationServiceImpl;
import com.panel.wg.user.domain.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserApplicationServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public UserDetailsService userDetailsService() {

        return username -> {
            User user = userService.findUserByUserName(username);

            AuthUser authUser = AuthUser.builder()
                    .userId(user.getId())
                    .username(user.getApiKey())
                    .password(user.getSecretKey())
                    .role(user.getRole())
                    .credentialNotExpired(true)
                    .accountNotExpired(true)
                    .accountNotLocked(true)
                    .enabled(user.isEnabled())
                    .build();
            return authUser;
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}


