package com.groupeisi.ms1_gestschool.course;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDtoRequest {
    private Long id;
    @NotBlank(message = "La description est requise")
    private String description;
    @NotBlank(message = "Le nom est requis")
    private String name;
    private boolean  archive;
    private Long classeId;
}
