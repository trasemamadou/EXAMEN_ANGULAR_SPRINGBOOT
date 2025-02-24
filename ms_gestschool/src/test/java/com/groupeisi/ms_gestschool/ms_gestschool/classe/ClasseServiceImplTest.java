package com.groupeisi.ms_gestschool.ms_gestschool.classe;

import com.groupeisi.ms1_gestschool.classe.*;
import com.groupeisi.ms1_gestschool.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.MessageSource;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.groupeisi.ms1_gestschool.exception.EntityExistsException;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Locale;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ClasseServiceImplTest {

    @Mock
    private ClasseRepository classeRepository;

    @Mock
    private ClasseMapper classeMapper;

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private ClasseServiceImpl classeService;

    @Test
    void createClasseOK() {
        ClasseDtoRequest request = this.getClasseDtoRequest();
        ClasseEntity entity = this.getClasseEntity();
        ClasseDtoResponse response = getClasseDtoResponse();
        when(classeRepository.findFirstByName(request.getName())).thenReturn(Optional.empty());
        when(classeMapper.toClasseEntity(request)).thenReturn(entity);
        when(classeRepository.save(entity)).thenReturn(entity);
        when(classeMapper.toClasseDtoResponse(entity)).thenReturn(response);
        Optional<ClasseDtoResponse> result = classeService.addClasse(request);
        assertTrue(result.isPresent());
        assertEquals(response.getName(), result.get().getName());
    }

    @Test
    void createClasseKO() {
        ClasseDtoRequest request = this.getClasseDtoRequest();
        ClasseEntity entity = this.getClasseEntity();
        when(classeRepository.findFirstByName(request.getName())).thenReturn(Optional.of(entity));
        when(messageSource.getMessage(eq("classeName.exist"), any(), any(Locale.class))).thenReturn("La classe M2GL  existe déja");
        EntityExistsException exception = assertThrows(EntityExistsException.class, () -> classeService.addClasse(request));
        assertEquals("La classe M2GL  existe déja", exception.getMessage());
        assertNotNull(exception);
    }

    @Test
    void getClasseByIdOK() {
        ClasseEntity entity = getClasseEntity();
        ClasseDtoResponse response = getClasseDtoResponse();
        when(classeRepository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(classeMapper.toClasseDtoResponse(entity)).thenReturn(response);

        Optional<ClasseDtoResponse> result = classeService.getClasseById(response.getId());
        assertTrue(result.isPresent());
    }

    @Test
    void getClasseByIdKO() {
        // Arrange
        ClasseDtoRequest request = this.getClasseDtoRequest(); // suppose que request.getName() retourne "M2GL" par exemple
        ClasseEntity entity = this.getClasseEntity();

        // On simule que la classe existe déjà
        when(classeRepository.findFirstByName(request.getName())).thenReturn(Optional.of(entity));
        // On stub le message avec la bonne clé et les bons arguments
        when(messageSource.getMessage(eq("classeName.exist"), any(), any(Locale.class)))
                .thenReturn("La classe non trouvé");

        // Act & Assert
        EntityExistsException exception = assertThrows(EntityExistsException.class, () -> classeService.addClasse(request));
        assertEquals("La classe non trouvé", exception.getMessage());
    }



    @Test
    void updateClasseOK() {
        ClasseDtoRequest request = getClasseDtoRequest();
        ClasseEntity entity = getClasseEntity();
        ClasseDtoResponse response = getClasseDtoResponse();

        when(classeRepository.findById(request.getId())).thenReturn(Optional.of(entity));
        when(classeRepository.findFirstByName(request.getName())).thenReturn(Optional.empty());
        when(classeRepository.save(entity)).thenReturn(entity);
        when(classeMapper.toClasseDtoResponse(entity)).thenReturn(response);

        Optional<ClasseDtoResponse> result = classeService.updateClasse(request.getId(), request);

        assertTrue(result.isPresent());
        assertEquals(response.getName(), result.get().getName());
    }

    @Test
    void updateClasseKO() {
        // Arrange
        ClasseDtoRequest request = this.getClasseDtoRequest();;
        when(classeRepository.findById(request.getId())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> classeService.updateClasse(request.getId(), request));
    }


    @Test
    void deleteClasseOK() {
        doNothing().when(classeRepository).deleteById(1L);
        Optional<?> result = classeService.deleteClasse(1L);

        assertTrue(result.isEmpty());
        verify(classeRepository, times(1)).deleteById(1L);
    }

    private ClasseDtoRequest getClasseDtoRequest() {
        ClasseDtoRequest classeDtoRequest = new ClasseDtoRequest();
        classeDtoRequest.setId(1L);
        classeDtoRequest.setName("M2GL");
        classeDtoRequest.setDescription("LA CLASSE M2GL");
        classeDtoRequest.setArchive(false);
        return classeDtoRequest;
    }

    private ClasseDtoResponse getClasseDtoResponse() {
        ClasseDtoResponse classeDtoResponse = new ClasseDtoResponse();
        classeDtoResponse.setId(1L);
        classeDtoResponse.setName("M2GL");
        classeDtoResponse.setDescription("LA CLASSE M2GL");
        classeDtoResponse.setArchive(false);
        return classeDtoResponse;
    }

    private ClasseEntity getClasseEntity() {
        ClasseEntity classe = new ClasseEntity();
        classe.setId(1L);
        classe.setName("M2GL");
        classe.setDescription("LA CLASSE M2GL");
        classe.setArchive(false);
        return classe;
    }
}
