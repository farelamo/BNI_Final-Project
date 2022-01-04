package com.mvp.bni.post.service.impl;
import com.mvp.bni.post.dto.output.CategoryOutput;
import com.mvp.bni.post.model.Category;
import com.mvp.bni.post.repository.CategoryRepository;
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

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;
    @Spy
    private ModelMapper mapper = new ModelMapper();

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAll_WillReturnListCategoryOutput() {
        Iterable<Category> categories = EASY_RANDOM.objects(Category.class, 2)
                .collect(Collectors.toList());
        when(categoryRepository.findAll()).thenReturn(categories);

        var result = categoryService.getAll();

        List<CategoryOutput> outputs = new ArrayList<>();
        for (Category product: categories) {
            outputs.add(mapper.map(product, CategoryOutput.class));
        }
        verify(categoryRepository, times(1)).findAll();
        assertEquals(outputs, result);
    }
}
