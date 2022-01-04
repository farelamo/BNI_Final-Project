package com.mvp.logservice.service;

import com.mvp.logservice.dto.BaseResponse;
import com.mvp.logservice.dto.input.LogInput;
import com.mvp.logservice.model.Log;
import org.springframework.http.ResponseEntity;

public interface LogService {
    Log create(LogInput input);
    ResponseEntity<BaseResponse> getAll();
}
