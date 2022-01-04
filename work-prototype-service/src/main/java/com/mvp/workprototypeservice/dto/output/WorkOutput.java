package com.mvp.workprototypeservice.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkOutput {
    private Long id;
    private String title;
    private String description;
    private String prototypeLink;
    private String downloadLink;
    private Long inovationGroupId;
}
