package com.mvp.bni.post.service.impl;

import com.mvp.bni.post.dto.input.PostInput;
import com.mvp.bni.post.dto.output.PostOutput;
import com.mvp.bni.post.model.Category;
import com.mvp.bni.post.model.Post;
import com.mvp.bni.post.repository.CategoryRepository;
import com.mvp.bni.post.repository.PostRepository;
import com.mvp.bni.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper mapper;
    private final CategoryRepository categoryRepository;

    @Override
    public PostOutput getOne(Long id) {
        Optional<Post> post = Optional.ofNullable(postRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("No Content");
        }));
        Post temp = post.get();
        Optional<Category> categoryData = categoryRepository.findById(post.get().getCategory().getId());
        Category category = categoryData.get();

        return PostOutput.builder()
                .id(temp.getId())
                .title(temp.getTitle())
                .description(temp.getDescription())
                .isPublish(temp.getIsPublish())
                .categoryId(Category.builder()
                        .id(category.getId())
                        .categoryName(category.getCategoryName())
                        .createdAt(category.getCreatedAt())
                        .updatedAt(category.getUpdatedAt())
                        .build())
                .userId(temp.getUserId())
                .build();
    }

    @Override
    public List<PostOutput> getAll() {
        Iterable<Post> posts = postRepository.findAll();
        List<Post> postsList = IterableUtils.toList(posts);
        if(postsList.isEmpty()){throw new RuntimeException("No Content");}

        List<PostOutput> postOutputs = new ArrayList<>();
        for (Post post : posts) {
            PostOutput postData = PostOutput.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .description(post.getDescription())
                    .isPublish(post.getIsPublish())
                    .categoryId(Category.builder()
                            .id(post.getCategory().getId())
                            .categoryName(post.getCategory().getCategoryName())
                            .createdAt(post.getCategory().getCreatedAt())
                            .updatedAt(post.getCategory().getUpdatedAt())
                            .build())
                    .userId(post.getUserId())
                    .build();
            postOutputs.add(postData);
        }
        return postOutputs;
    }

    @Override
    public void addOne(PostInput input) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        Optional<Category> category = Optional.ofNullable(
                categoryRepository.findById(
                        input.getCategoryId())
                        .orElseThrow(() -> {
                            throw new RuntimeException("No Content");
                        }));
        Category temp = category.get();
        Post post = mapper.map(input, Post.class);
        post.setId(null);
        post.setCategory(temp);
        postRepository.save(post);
    }

    @Override
    public void update(Long Id, PostInput input){
        Optional<Post> post = Optional.ofNullable(postRepository.findById(Id).orElseThrow(() -> {
            throw new RuntimeException("No Content");
        }));
        Optional<Category> categoryData = categoryRepository.findById(input.getCategoryId());
        Category category = categoryData.get();

        Post postData = Post.builder()
                .id(post.get().getId())
                .title(input.getTitle())
                .description(input.getDescription())
                .isPublish(input.getIsPublish())
                .category(Category.builder()
                        .id(category.getId())
                        .categoryName(category.getCategoryName())
                        .createdAt(category.getCreatedAt())
                        .updatedAt(category.getUpdatedAt())
                        .build())
                .userId(input.getUserId())
                .build();
//        Post post = mapper.map(input, Post.class);
//        post.setId(Id);
        this.postRepository.save(postData);
    }

    @Override
    public void deleteOne(Long id) {
        Optional.ofNullable(postRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("No Content");
        }));
        this.postRepository.deleteById(id);
    }
}
