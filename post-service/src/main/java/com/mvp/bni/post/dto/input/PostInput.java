package com.mvp.bni.post.dto.input;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostInput {

    @NotBlank(message = "post name cannot be blank")
    @Size(max = 100, message = "title cannot be more than 100 character")
    private String title;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @JsonProperty("is_publish")
    @NotNull(message = "publish cannot be null")
    private Boolean isPublish;

    @JsonProperty("category_id")
    @NotNull(message = "category_id cannot be null")
    private Long categoryId;

    @JsonProperty("user_id")
    @NotNull(message = "user_id cannot be null")
    private Integer userId;
}
