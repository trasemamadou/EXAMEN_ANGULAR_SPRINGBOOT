package com.groupeisi.ms1_gestschool.session;

import com.groupeisi.ms1_gestschool.course.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface SessionMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "archive", target = "archive")
    @Mapping(source = "courseId", target = "course.id" )
    SessionEntity toSessionEntity(SessionDtoRequest session);
    SessionDtoResponse toSessionDtoResponse(SessionEntity session);
    List<SessionDtoResponse> toSessionDtoResponseList(List<SessionEntity> listeSessions);
}