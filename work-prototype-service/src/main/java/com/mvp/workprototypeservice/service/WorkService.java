package com.mvp.workprototypeservice.service;

import com.mvp.workprototypeservice.dto.input.WorkInput;
import com.mvp.workprototypeservice.dto.output.WorkOutput;

import java.util.List;

public interface WorkService {
    WorkOutput getOne(Long id);
    List<WorkOutput> getAll();
    void addOne(WorkInput input);
}
