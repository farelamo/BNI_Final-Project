package com.mvp.bni.post.service;

import com.mvp.bni.post.dto.input.CategoryInput;
import com.mvp.bni.post.dto.output.CategoryOutput;

import java.util.List;

public interface CategoryService {
    CategoryOutput getOne(Long id);
    List<CategoryOutput> getAll();
    void addOne(CategoryInput input);
    void update(Long id, CategoryInput input);
    void deleteOne(Long id);
}
