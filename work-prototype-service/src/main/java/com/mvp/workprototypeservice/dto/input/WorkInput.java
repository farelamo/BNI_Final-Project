package com.mvp.workprototypeservice.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WorkInput {

    @NotBlank(message = "tittle cannot be blank")
    private String title;

    @NotBlank(message = "description cannot be blank")
    private String description;

    @JsonProperty("prototype_link")
    @NotBlank(message = "link prototype cannot be blank")
    @Size(max = 255, message = "link prototype cannot be more than 255 character")
    private String prototypeLink;

    @JsonProperty("download_link")
    @NotBlank(message = "link download cannot be blank")
    @Size(max = 255, message = "link download cannot be more than 255 character")
    private String downloadLink;

    @JsonProperty("inovation_group_id")
    private Long inovationGroupId;
}
