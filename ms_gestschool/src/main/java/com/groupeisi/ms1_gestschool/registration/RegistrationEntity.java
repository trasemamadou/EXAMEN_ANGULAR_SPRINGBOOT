package com.groupeisi.ms1_gestschool.registration;
import com.groupeisi.ms1_gestschool.classe.ClasseEntity;
import com.groupeisi.ms1_gestschool.student.StudentEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "registration")
public class RegistrationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean archive;
    @ManyToOne
    @JoinColumn(name = "classe_id", nullable = true)
    private ClasseEntity classe;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;
}
