package com.groupeisi.ms1_gestschool.course;

import com.groupeisi.ms1_gestschool.classe.ClasseDtoResponse;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDtoResponse {
    private Long id;
    private String description;
    private String name;
    private boolean  archive;
    private ClasseDtoResponse classe;
}