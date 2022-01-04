package com.mvp.authservice.Payload;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class UsernamePassword {

    @Size(min = 5, max = 30, message = "maximum username reach is 50 character and minimum 20 character")
    private String username;

    @Size(min = 5, max = 8, message = "maximum password reach is 8 character and minimum 5 character")
    private String password;
}
