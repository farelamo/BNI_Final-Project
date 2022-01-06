package com.mvp.logservice.controller;

import com.mvp.logservice.dto.BaseResponse;
import com.mvp.logservice.dto.input.LogInput;
import com.mvp.logservice.model.Log;
import com.mvp.logservice.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody String input){
        Log logCreated = logService.create(input);
        return ResponseEntity.ok(logCreated);
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getAll() {
        return logService.getAll();
    }
}
