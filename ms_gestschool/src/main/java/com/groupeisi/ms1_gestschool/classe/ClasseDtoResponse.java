package com.groupeisi.ms1_gestschool.classe;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClasseDtoResponse {
    private Long id;
    private String name;
    private String description;
    private boolean archive;
}
