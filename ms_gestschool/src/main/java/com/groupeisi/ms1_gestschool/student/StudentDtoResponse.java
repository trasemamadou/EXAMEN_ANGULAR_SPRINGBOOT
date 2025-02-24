package com.groupeisi.ms1_gestschool.student;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentDtoResponse implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailPro;
    private String emailPerso;
    private String phoneNumber;
    private String address;
    private boolean archive;
    private String registrationNu;
}
