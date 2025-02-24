package com.groupeisi.ms_gestschool.ms_gestschool.student;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.groupeisi.ms1_gestschool.exception.EntityExistsException;
import com.groupeisi.ms1_gestschool.exception.EntityNotFoundException;
import com.groupeisi.ms1_gestschool.student.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import java.util.Locale;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;
    @Mock
    private MessageSource messageSource;
    @InjectMocks
    private StudentServiceImpl studentService;
    @Test
    void createStudentOK() {
        StudentDtoRequest request = this.getStudentDtoRequest();
        StudentEntity entity = this.getStudentEntity();
        StudentDtoResponse response = this.getStudentDtoResponse();
        when(studentRepository.findByEmailPro(request.getEmailPro())).thenReturn(Optional.empty());
        when(studentRepository.findByEmailPerso(request.getEmailPerso())).thenReturn(Optional.empty());
        when(studentRepository.findByPhoneNumber(request.getPhoneNumber())).thenReturn(Optional.empty());
        when(studentRepository.findByRegistrationNu(request.getRegistrationNu())).thenReturn(Optional.empty());
        when(studentMapper.toStudentEntity(request)).thenReturn(entity);
        when(studentRepository.save(entity)).thenReturn(entity);
        when(studentMapper.toStudentDtoResponse(entity)).thenReturn(response);
        Optional<StudentDtoResponse> result = studentService.saveStudent(request);
        assertTrue(result.isPresent());
        assertEquals(response.getEmailPro(), result.get().getEmailPro());
    }

    @Test
    void createStudentKO() {
        StudentDtoRequest request = getStudentDtoRequest();
        when(studentRepository.findByEmailPro(request.getEmailPro()))
                .thenReturn(Optional.of(getStudentEntity()));
        when(messageSource.getMessage(eq("emailPerso.exist"), any(), any(Locale.class)))
                .thenReturn(String.format("Le mail professionnel : %s existe déjà", request.getEmailPro()));
        EntityExistsException exception = assertThrows(EntityExistsException.class,
                () -> studentService.saveStudent(request));
        assertNotNull(exception);
        assertEquals(String.format("Le mail professionnel : %s existe déjà", request.getEmailPro()),
                exception.getMessage());
    }



    @Test
    void getStudentByIdOK() {
        StudentEntity entity = this.getStudentEntity();
        StudentDtoRequest request= this.getStudentDtoRequest();
        StudentDtoResponse response = this.getStudentDtoResponse();

        when(studentRepository.findById(request.getId())).thenReturn(Optional.of(entity));
        when(studentMapper.toStudentDtoResponse(entity)).thenReturn(response);

        Optional<StudentDtoResponse> result = studentService.getStudentById(request.getId());
        assertTrue(result.isPresent());
    }

    @Test
    void getStudentByIdKO() {
        StudentDtoRequest request = this.getStudentDtoRequest();
        when(studentRepository.findById(request.getId())).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("student.notfound"), any(), any(Locale.class)))
                .thenReturn(String.format("Student with id %s not found", request.getId()));
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> studentService.getStudentById(request.getId()));
        assertNotNull(exception);
        assertEquals(String.format("Student with id %s not found", request.getId()), exception.getMessage());
    }

    @Test
    void updateStudentOK() {
        StudentDtoRequest request = getStudentDtoRequest();
        StudentEntity entity = getStudentEntity();
        StudentDtoResponse response = getStudentDtoResponse();
        when(studentRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(studentRepository.findByEmailPro(request.getEmailPro())).thenReturn(Optional.empty());
        when(studentRepository.save(entity)).thenReturn(entity);
        when(studentMapper.toStudentDtoResponse(entity)).thenReturn(response);

        Optional<StudentDtoResponse> result = studentService.updateStudent(1L, request);

        assertTrue(result.isPresent());
        assertEquals(response.getEmailPro(), result.get().getEmailPro());
    }

    @Test
    void updateStudentKO() {
        StudentDtoRequest request = this.getStudentDtoRequest();
        when(studentRepository.findById(request.getId())).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("student.notfound"), any(), any(Locale.class)))
                .thenReturn("Student with id 1 not found");
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> studentService.updateStudent(1L, request));
        assertNotNull(exception);
        assertEquals("Student with id 1 not found", exception.getMessage());
    }


    @Test
    void deleteStudentOK() {
        StudentEntity entity = this.getStudentEntity();
        StudentDtoRequest request = this.getStudentDtoRequest();
        StudentDtoResponse response = this.getStudentDtoResponse();
        when(studentRepository.findById(request.getId())).thenReturn(Optional.of(entity));
        doNothing().when(studentRepository).deleteById(request.getId());
        when(studentMapper.toStudentDtoResponse(entity)).thenReturn(response);
        Optional<StudentDtoResponse> result = studentService.deleteStudentById(request.getId());
        assertTrue(result.isPresent());
        assertEquals(response, result.get());
        verify(studentRepository, times(1)).deleteById(request.getId());
    }


    private StudentDtoRequest getStudentDtoRequest() {
        StudentDtoRequest request = new StudentDtoRequest();
        request.setId(1L);
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setEmailPro("john.doe@school.com");
        request.setEmailPerso("john.doe@gmail.com");
        request.setPhoneNumber("123456789");
        request.setRegistrationNu("REG123");
        return request;
    }

    private StudentDtoResponse getStudentDtoResponse() {
        StudentDtoResponse response = new StudentDtoResponse();
        response.setId(1L);
        response.setFirstName("John");
        response.setLastName("Doe");
        response.setEmailPro("john.doe@school.com");
        response.setEmailPerso("john.doe@gmail.com");
        response.setPhoneNumber("123456789");
        response.setRegistrationNu("REG123");
        return response;
    }

    private StudentEntity getStudentEntity() {
        StudentEntity entity = new StudentEntity();
        entity.setId(1L);
        entity.setFirstName("John");
        entity.setLastName("Doe");
        entity.setEmailPro("john.doe@school.com");
        entity.setEmailPerso("john.doe@gmail.com");
        entity.setPhoneNumber("123456789");
        entity.setRegistrationNu("REG123");
        return entity;
    }
}
