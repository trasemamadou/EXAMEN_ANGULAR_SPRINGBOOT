package com.groupeisi.ms1_gestschool.course;


import com.groupeisi.ms1_gestschool.classe.ClasseEntity;
import com.groupeisi.ms1_gestschool.session.SessionEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name="course")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
    @Column(nullable = false)
private String description;
    @Column(nullable = false)
private String name;
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
private boolean  archive;
    @ManyToOne
    @JoinColumn(name = "classe_id", nullable = false)
    private ClasseEntity classe;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SessionEntity> sessions;
}
