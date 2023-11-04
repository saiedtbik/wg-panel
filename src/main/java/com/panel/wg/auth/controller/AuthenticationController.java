package com.panel.wg.auth.controller;


import com.panel.wg.auth.command.AuthenticateCommand;
import com.panel.wg.auth.dto.AuthenticationResponse;
import com.panel.wg.auth.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth APIs")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticateCommand authenticateCommand) {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(authenticateCommand);
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        AuthenticationResponse authenticationResponse = authenticationService.refreshToken(authHeader);
        return ResponseEntity.ok(authenticationResponse);
    }

}
