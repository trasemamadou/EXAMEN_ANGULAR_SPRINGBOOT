package com.groupeisi.ms1_gestschool.registration;

import com.groupeisi.ms1_gestschool.classe.ClasseDtoResponse;
import com.groupeisi.ms1_gestschool.student.StudentDtoResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class RegistrationDtoResponse {
    private Long id;
    private String name;
    private String description;
    private boolean archive;
    private ClasseDtoResponse classe;
    private StudentDtoResponse student;
}
