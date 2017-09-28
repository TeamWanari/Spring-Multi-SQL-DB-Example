package com.wanari.multidb.example.controller;

import com.wanari.multidb.example.service.errors.ErrorResponse;
import org.springframework.http.ResponseEntity;

public class BaseController {

    protected <T> ResponseEntity<T> toResponse(T body) {
        return ResponseEntity
            .ok(body);
    }

    protected <T extends ErrorResponse> ResponseEntity<T> errorToResponse(T error) {
        return ResponseEntity
            .status(error.status)
            .body(error);
    }

}
