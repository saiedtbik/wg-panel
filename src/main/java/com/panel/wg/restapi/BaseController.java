package com.panel.wg.restapi;

import org.springframework.http.ResponseEntity;

public class BaseController {
    public ResponseEntity success() {
        return ResponseEntity.ok().build();
    }

    public <T> ResponseEntity success(T body) {
        return ResponseEntity.ok(body);
    }

}
