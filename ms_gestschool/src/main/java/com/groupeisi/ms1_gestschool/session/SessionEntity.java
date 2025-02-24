package com.groupeisi.ms1_gestschool.session;

import com.groupeisi.ms1_gestschool.course.CourseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Table(name = "session")
@Entity
@Data
@ToString
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;
    private String description;
    private boolean archive;
    @ManyToOne(optional = false)
   @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity course;
}
