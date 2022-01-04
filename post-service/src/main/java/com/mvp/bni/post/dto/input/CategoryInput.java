package com.mvp.bni.post.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryInput {

    @JsonProperty("category_name")
    @NotBlank(message = "category name cannot be blank")
    @Size(max = 100, message = "post_name cannot be more than 100 character")
    private String categoryName;
}
