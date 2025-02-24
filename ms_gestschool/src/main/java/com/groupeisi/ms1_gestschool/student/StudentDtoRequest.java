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
public class StudentDtoRequest  implements Serializable {
    private Long id;
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotBlank(message = "Professional email is required")
    private String emailPro;
    @NotBlank(message = "Personal email is required")
    private String emailPerso;
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
    @NotBlank(message = "Address is required")
    private String address;
    private boolean archive;
    @NotBlank(message = "Registration number is required")
    private String registrationNu;


}
