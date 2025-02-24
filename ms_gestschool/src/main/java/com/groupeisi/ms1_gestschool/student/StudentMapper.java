package com.groupeisi.ms1_gestschool.student;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "emailPro", target = "emailPro")
    @Mapping(source = "emailPerso", target = "emailPerso")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "archive", target = "archive")
    @Mapping(source = "registrationNu", target = "registrationNu")


    public StudentEntity toStudentEntity(StudentDtoRequest etudiant);
    public StudentDtoResponse toStudentDtoResponse(StudentEntity etudiant);
    public List<StudentDtoResponse> toStudentDtoResponseList(List<StudentEntity> listeEtudiants);
}
