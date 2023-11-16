package com.panel.wg.auth.config;


import com.panel.wg.auth.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static com.panel.wg.user.domain.entities.Authority.*;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(c -> c.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(
                                    "/api/v1/auth/**",
                                    "/v2/api-docs",
                                    "/v3/api-docs",
                                    "/v3/api-docs/**",
                                    "/swagger-resources",
                                    "/swagger-resources/**",
                                    "/configuration/ui",
                                    "/configuration/security",
                                    "/swagger-ui/**",
                                    "/webjars/**",
                                    "/swagger-ui.html",
                                    "/swagger-ui/index.html"
                            )
                            .permitAll()

                            /*--------------------- user ----------------------*/
                            .requestMatchers(POST, "/api/v1/user").hasAnyAuthority(CREATE_USER.name())
                            .requestMatchers(GET, "/api/v1/user").hasAnyAuthority(QUERY_USER.name())
                            .requestMatchers(GET, "/api/v1/user/all").hasAnyAuthority(QUERY_ALL_USER.name())
                            .requestMatchers(POST, "/api/v1/user/*/disable-client").hasAnyAuthority(DISABLE_CLIENT.name())
                            .requestMatchers(POST, "/api/v1/user/*/enable-client").hasAnyAuthority(ENABLE_CLIENT.name())


                            /*------------------- creditor ----------------------------*/
//                            .requestMatchers(POST, "/api/v1/creditor/add-sheba-number").hasAnyAuthority(ADD_CREDITOR_SHEBA.name())
//                            .requestMatchers(POST, "/api/v1/creditor/change-state").hasAnyAuthority(CHANGE_CREDITOR_STATE.name())
//                            .requestMatchers(POST, "/api/v1/creditor/change-target-account-number").hasAnyAuthority(CHANGE_CREDITOR_ACCOUNT_NUMBER.name())
//                            .requestMatchers(POST, "/api/v1/creditor/register").hasAnyAuthority(CREDITOR_REGISTER.name())
//                            .requestMatchers(POST, "/api/v1/creditor/remove-sheba-number").hasAnyAuthority(REMOVE_CREDITOR_SHEBA.name())
//                            .requestMatchers(GET, "/api/v1/creditor/detail").hasAnyAuthority(VIEW_CREDITOR_DETAILS.name())

                    ;

                })
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authenticationProvider(authenticationProvider);
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }



}

