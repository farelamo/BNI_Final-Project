package com.mvp.bni.post.service.impl;

import com.mvp.bni.post.dto.input.CategoryInput;
import com.mvp.bni.post.dto.output.CategoryOutput;
import com.mvp.bni.post.model.Category;
import com.mvp.bni.post.repository.CategoryRepository;
import com.mvp.bni.post.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    private final ModelMapper mapper;
    private final LogService logService;

    @Override
    public CategoryOutput getOne(Long id) {
        Optional<Category> category = Optional.ofNullable(repository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("No Content");
        }));
        Category temp = category.get();
        return mapper.map(temp, CategoryOutput.class);
    }

    @Override
    public List<CategoryOutput> getAll() {
        Iterable<Category> categories = repository.findAll();
        List<Category> categoryList = IterableUtils.toList(categories);
        if(categoryList.isEmpty()){throw new RuntimeException("No Content");}

        List<CategoryOutput> outputs = new ArrayList<>();
        for (Category category : categoryList) {
            outputs.add(mapper.map(category, CategoryOutput.class));
        }
        return outputs;
    }

    @Override
    public void addOne(CategoryInput input) {
        Category category = mapper.map(input, Category.class);
        category.setId(null);
        logService.send(input.toString());
        this.repository.save(category);
    }

    @Override
    public void update(Long Id, CategoryInput input){
        Optional<Category> update = Optional.ofNullable(repository.findById(Id).orElseThrow(() -> {
            throw new RuntimeException("No Content");
        }));
        Category category = mapper.map(input, Category.class);
        category.setId(Id);
        this.repository.save(category);
    }

    @Override
    public void deleteOne(Long id) {
        Optional.ofNullable(repository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("No Content");
        }));
        this.repository.deleteById(id);
    }
}
