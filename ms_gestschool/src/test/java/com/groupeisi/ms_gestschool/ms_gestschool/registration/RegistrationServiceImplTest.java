
package com.groupeisi.ms_gestschool.ms_gestschool.registration;
import com.groupeisi.ms1_gestschool.registration.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceImplTest {
    @Mock
    private RegistrationRepository registrationRepository;
    @Mock
    private RegistrationMapper registrationMapper;
    @InjectMocks
    private RegistrationServiceImpl registrationService;

    @Test
    void getAllRegistrations() {
        List<RegistrationEntity> entities = List.of(new RegistrationEntity());
        when(registrationRepository.findAll()).thenReturn(entities);
        when(registrationMapper.toListRegistrationDtoResponse(entities)).thenReturn(List.of(new RegistrationDtoResponse()));

        Optional<List<RegistrationDtoResponse>> result = registrationService.getAllRegistrations();

        assertTrue(result.isPresent());
        assertFalse(result.get().isEmpty());
    }

//    @Test
//    void addRegistration() {
//        RegistrationDtoRequest request = new RegistrationDtoRequest();
//        RegistrationEntity entity = new RegistrationEntity();
//        RegistrationDtoResponse response = new RegistrationDtoResponse();
//
//        when(registrationMapper.toRegistrationEntity(request)).thenReturn(entity);
//        when(registrationRepository.save(entity)).thenReturn(entity);
//        when(registrationMapper.toRegistrationDtoResponse(entity)).thenReturn(response);
//
//        Optional<RegistrationDtoResponse> result = registrationService.addRegistration(request);
//
//        assertTrue(result.isPresent());
//    }
//
//    @Test
//    void updateRegistration() {
//        Long id = 1L;
//        RegistrationDtoRequest request = new RegistrationDtoRequest();
//        RegistrationEntity entity = new RegistrationEntity();
//        RegistrationDtoResponse response = new RegistrationDtoResponse();
//
//        when(registrationRepository.findById(id)).thenReturn(Optional.of(entity));
//        when(registrationMapper.toRegistrationEntity(request)).thenReturn(entity);
//        when(registrationRepository.save(entity)).thenReturn(entity);
//        when(registrationMapper.toRegistrationDtoResponse(entity)).thenReturn(response);
//
//        Optional<RegistrationDtoResponse> result = registrationService.updateRegistration(id, request);
//
//        assertTrue(result.isPresent());
//    }
//
//    @Test
//    void deleteRegistration() {
//        Long id = 1L;
//        RegistrationEntity entity = new RegistrationEntity();
//
//        when(registrationRepository.findById(id)).thenReturn(Optional.of(entity));
//        doNothing().when(registrationRepository).delete(entity);
//
//        Optional<?> result = registrationService.deleteRegistration(id);
//        assertEquals(Optional.of("Registration successfully deleted."), result);
//    }
}
