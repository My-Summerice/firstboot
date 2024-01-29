package org.example.firstboot.controller;

import org.example.firstboot.core.result.GlobalResult;
import org.example.firstboot.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/captcha/image")
    public GlobalResult captchaImage() throws IOException {

        return GlobalResult.success(authService.image());
    }

    @GetMapping("/captcha/text")
    public GlobalResult captchaText() {

        return GlobalResult.success(authService.text());
    }
}
