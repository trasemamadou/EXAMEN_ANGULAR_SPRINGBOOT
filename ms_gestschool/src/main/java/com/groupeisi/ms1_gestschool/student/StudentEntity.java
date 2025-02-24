package com.groupeisi.ms1_gestschool.student;

import com.groupeisi.ms1_gestschool.registration.RegistrationEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String emailPro;
    private String emailPerso;
    private String phoneNumber;
    private String address;
    private boolean archive;
    private String registrationNu;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegistrationEntity> registrations= new ArrayList<>();

}
