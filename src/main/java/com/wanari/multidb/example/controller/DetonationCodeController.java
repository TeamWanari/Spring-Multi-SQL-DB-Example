package com.wanari.multidb.example.controller;

import com.wanari.multidb.example.service.DetonationCodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/detonation-code")
public class DetonationCodeController extends BaseController {

    private final DetonationCodeService detonationCodeService;

    public DetonationCodeController(DetonationCodeService detonationCodeService) {
        this.detonationCodeService = detonationCodeService;
    }

    @RequestMapping(
        value = "",
        method = RequestMethod.GET)
    public ResponseEntity<?> getAllDetonationCode() {
        return detonationCodeService.findAll().fold(
            this::errorToResponse,
            this::toResponse
        );
    }
}