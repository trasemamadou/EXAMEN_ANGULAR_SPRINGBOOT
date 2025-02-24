package com.groupeisi.ms1_gestschool.registration;
import com.groupeisi.ms1_gestschool.classe.ClasseDtoRequest;
import com.groupeisi.ms1_gestschool.classe.ClasseDtoResponse;
import com.groupeisi.ms1_gestschool.classe.ClasseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface RegistrationMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "archive", target = "archive")
    @Mapping(source = "classeId", target = "classe.id")
    @Mapping(source = "studentId", target = "student.id")
    public RegistrationEntity toRegistrationEntity(RegistrationDtoRequest registration);
    public RegistrationDtoResponse toRegistrationDtoResponse(RegistrationEntity registration);
    public List<RegistrationDtoResponse> toListRegistrationDtoResponse(List<RegistrationEntity> registrations);
}
