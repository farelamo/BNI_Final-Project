package com.mvp.bni.post.service.impl;
import com.mvp.bni.post.dto.output.CategoryOutput;
import com.mvp.bni.post.dto.output.PostOutput;
import com.mvp.bni.post.model.Category;
import com.mvp.bni.post.repository.CategoryRepository;
import org.antlr.stringtemplate.language.Cat;
import org.assertj.core.api.Assert;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestCategoryServiceImpl {
    private static EasyRandom EASY_RANDOM = new EasyRandom();
    private static ModelMapper modelMapper = new ModelMapper();
    private Long RANDOM_ID;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;
    @Spy
    private ModelMapper mapper = new ModelMapper();

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        RANDOM_ID = EASY_RANDOM.nextLong();
    }

    @Test
    public void getAll_WillReturnListProductOutput() {
        Iterable<Category> categories = EASY_RANDOM.objects(Category.class, 2)
                .collect(Collectors.toList());
        when(categoryRepository.findAll()).thenReturn(categories);

        var result = categoryService.getAll();

        List<CategoryOutput> outputs = new ArrayList<>();
        for (Category product: categories) {
            outputs.add(modelMapper.map(product, CategoryOutput.class));
        }
        verify(categoryRepository, times(1)).findAll();
        assertEquals(outputs, result);
    }
}
