package com.mvp.bni.post.dto.output;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mvp.bni.post.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostOutput {
    private Long id;
    private String title;
    private String description;
    private Boolean isPublish;
    private Category categoryId;
    private Integer userId;
}
