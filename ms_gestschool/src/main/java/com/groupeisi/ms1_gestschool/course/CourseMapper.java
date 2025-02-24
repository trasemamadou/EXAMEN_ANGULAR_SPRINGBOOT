package com.groupeisi.ms1_gestschool.course;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface CourseMapper {
     @Mapping(source = "id", target = "id")
     @Mapping(source = "name", target = "name")
     @Mapping(source = "description", target = "description")
     @Mapping(source = "archive", target = "archive")
     @Mapping(source = "classeId", target = "classe.id")
     CourseEntity toCourseEntity(CourseDtoRequest course);
     CourseDtoResponse toCourseDtoResponse(CourseEntity course);
     List<CourseDtoResponse> toCourseDtoResponseList(List<CourseEntity> listeCourses);
}
