package com.mvp.workprototypeservice.service.impl;

import com.mvp.workprototypeservice.dto.input.WorkInput;
import com.mvp.workprototypeservice.dto.output.WorkOutput;
import com.mvp.workprototypeservice.model.Work;
import com.mvp.workprototypeservice.repository.WorkRepository;
import com.mvp.workprototypeservice.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkServiceImpl implements WorkService {
    private final WorkRepository repository;
    private final ModelMapper mapper;

    @Override
    public WorkOutput getOne(Long id) {
        Optional<Work> category = Optional.ofNullable(repository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("No Content");
        }));
        Work temp = category.get();
        return mapper.map(temp, WorkOutput.class);
    }

    @Override
    public List<WorkOutput> getAll() {
        Iterable<Work> categories = repository.findAll();
        List<Work> categoryList = IterableUtils.toList(categories);
        if(categoryList.isEmpty()){throw new RuntimeException("No Content");}

        List<WorkOutput> outputs = new ArrayList<>();
        for (Work category : categoryList) {
            outputs.add(mapper.map(category, WorkOutput.class));
        }
        return outputs;
    }

    @Override
    public void addOne(WorkInput input) {
        Work work = mapper.map(input, Work.class);
        work.setId(null);
        this.repository.save(work);
    }
}
