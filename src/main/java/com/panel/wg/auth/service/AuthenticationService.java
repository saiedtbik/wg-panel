package com.panel.wg.auth.service;


import com.panel.wg.auth.command.AuthenticateCommand;
import com.panel.wg.auth.dto.AuthUser;
import com.panel.wg.auth.dto.AuthenticationResponse;
import com.panel.wg.common.domain.exceptions.BusinessRuleViolationException;
import com.panel.wg.common.domain.tools.utilities.StringUtility;
import com.panel.wg.user.domain.exceptions.UserError;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public AuthenticationResponse authenticate(AuthenticateCommand authenticateCommand) {
        // Authenticate username and password. If It isn't authenticated throw AuthenticationException
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticateCommand.username(),
                            authenticateCommand.password()
                    )
            );
        }catch (Exception e) {
            throw new BusinessRuleViolationException(UserError.USERNAME_OR_PASS_INCORRECT);
        }

        var user = (AuthUser) userDetailsService.loadUserByUsername(authenticateCommand.username());
        Map<String, Object> extraClaims = new HashMap<>();

        var jwtToken = jwtService.generateToken(extraClaims, user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }


    public AuthenticationResponse refreshToken(String authHeader) {

        final String refreshToken = jwtService.extractJwtToken(authHeader)
                .orElseThrow(() -> new SecurityException());

        final String userName = jwtService.extractUserName(refreshToken);
        if (StringUtility.isNullOrEmpty(userName)) {
            throw new SecurityException();
        }

        AuthUser user;
        try {
            user = (AuthUser) userDetailsService.loadUserByUsername(userName);
        } catch (UsernameNotFoundException e) {
            throw new SecurityException(e);
        }

        AuthenticationResponse authResponse;
        if (jwtService.isTokenValid(refreshToken, user)) {

            var accessToken = jwtService.generateToken(user);
            authResponse = AuthenticationResponse.builder()
                    .refreshToken(refreshToken)
                    .accessToken(accessToken)
                    .build();
        } else {
            throw new SecurityException();
        }

        return authResponse;
    }
}