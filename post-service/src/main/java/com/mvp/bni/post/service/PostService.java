package com.mvp.bni.post.service;

import com.mvp.bni.post.dto.input.PostInput;
import com.mvp.bni.post.dto.output.PostOutput;

import java.util.List;

public interface PostService {
    PostOutput getOne(Long id);
    List<PostOutput> getAll();
    void addOne(PostInput input);
    void update(Long id, PostInput input);
    void deleteOne(Long id);
}
