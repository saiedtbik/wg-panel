package com.panel.wg.auth.token;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    private String token;
    private TokenType tokenType;
    private boolean expired;
    private UUID creditorId;
}
