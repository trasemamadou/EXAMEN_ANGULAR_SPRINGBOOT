package com.groupeisi.ms1_gestschool.registration;

import com.groupeisi.ms1_gestschool.classe.ClasseDtoRequest;
import com.groupeisi.ms1_gestschool.classe.ClasseDtoResponse;

import java.util.List;
import java.util.Optional;

public interface RegistrationService {
    Optional<List<RegistrationDtoResponse>> getAllRegistrations();
    Optional<RegistrationDtoResponse> addRegistration(RegistrationDtoRequest registration);
    Optional<RegistrationDtoResponse> updateRegistration(Long id, RegistrationDtoRequest registration);
    Optional<?> deleteRegistration(Long id);
}
