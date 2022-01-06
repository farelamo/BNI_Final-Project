package com.mvp.authservice.controller;

import com.mvp.authservice.Payload.BaseResponse;
import com.mvp.authservice.Payload.TokenResponse;
import com.mvp.authservice.Payload.UsernamePassword;
import com.mvp.authservice.model.User;
import com.mvp.authservice.service.AuthService;
import com.mvp.authservice.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final LogService logService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse<?>> register(@Valid @RequestBody UsernamePassword usernamePassword){
        try {
            authService.register(usernamePassword);
            logService.send("user berhasil register !!");
            return ResponseEntity.ok(new BaseResponse<>("Success register account !!"));
        }catch (DataIntegrityViolationException e) {
            logService.send("user tidak berhasil register !!");
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/token")
    public ResponseEntity<BaseResponse<?>> getToken(@RequestBody UsernamePassword usernamePassword){
        TokenResponse token = authService.generateToken(usernamePassword);
        logService.send("user berhasil login !!");
        return ResponseEntity.ok(new BaseResponse<>(token));
    }
}
