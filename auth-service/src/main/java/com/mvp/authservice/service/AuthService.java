package com.mvp.authservice.service;

import com.mvp.authservice.Payload.TokenResponse;
import com.mvp.authservice.Payload.UsernamePassword;
import com.mvp.authservice.model.User;

public interface AuthService {
    User register(UsernamePassword req);
    TokenResponse generateToken(UsernamePassword req);
}
