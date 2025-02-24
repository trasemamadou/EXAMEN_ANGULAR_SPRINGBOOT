package com.groupeisi.ms1_gestschool.session;

import com.groupeisi.ms1_gestschool.course.CourseDtoResponse;
import com.groupeisi.ms1_gestschool.course.CourseEntity;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class SessionDtoResponse{
 private  Long id;
 private String name;
 private LocalDateTime beginDate;
 private LocalDateTime endDate;
 private String description;
 private boolean archive;
 private CourseDtoResponse course;
}