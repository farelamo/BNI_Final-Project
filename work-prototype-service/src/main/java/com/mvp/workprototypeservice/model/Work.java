package com.mvp.workprototypeservice.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "work_prototype")
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Work {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "prototype_link", length = 255)
    private String prototypeLink;

    @Column(name = "download_link", length = 255)
    private String downloadLink;

    @Column(name = "inovation_group_id")
    private Long inovationGroupId;
}
