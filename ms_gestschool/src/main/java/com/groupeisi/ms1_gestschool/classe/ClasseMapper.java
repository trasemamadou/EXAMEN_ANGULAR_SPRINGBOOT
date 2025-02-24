package com.groupeisi.ms1_gestschool.classe;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ClasseMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "archive", target = "archive")
    public ClasseEntity toClasseEntity(ClasseDtoRequest classe);
    public ClasseDtoResponse toClasseDtoResponse(ClasseEntity classe);
    public List<ClasseDtoResponse> toListClasseDtoResponse(List<ClasseEntity> classes);
}
