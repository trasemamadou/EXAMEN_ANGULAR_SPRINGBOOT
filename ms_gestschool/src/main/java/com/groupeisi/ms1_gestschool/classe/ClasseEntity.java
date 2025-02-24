package com.groupeisi.ms1_gestschool.classe;

import com.groupeisi.ms1_gestschool.course.CourseEntity;
import com.groupeisi.ms1_gestschool.registration.RegistrationEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "classe")
public class ClasseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean archive;
    @OneToMany(mappedBy = "classe", cascade = CascadeType.ALL)
    List<RegistrationEntity> registrations = new ArrayList<>();
}
