package com.mvp.logservice.service.impl;

import com.mvp.logservice.dto.BaseResponse;
import com.mvp.logservice.dto.input.LogInput;
import com.mvp.logservice.model.Log;
import com.mvp.logservice.repository.LogRepository;
import com.mvp.logservice.service.LogService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final ModelMapper mapper;
    private final LogRepository logRepository;

    @Override
    public Log create(LogInput input){
        Log log = this.mapper.map(input, Log.class);
        log.setDate();
        logRepository.save(log);
        return log;
    }

    @Override
    public ResponseEntity<BaseResponse> getAll(){
        Iterable<Log> logs = logRepository.findAll();
        List<Log> logList = IterableUtils.toList(logs);
        return new ResponseEntity<BaseResponse>(new BaseResponse<>(logList), HttpStatus.OK);
    }
}
