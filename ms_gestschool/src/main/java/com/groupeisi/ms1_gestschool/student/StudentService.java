package com.groupeisi.ms1_gestschool.student;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public interface StudentService {
public Optional<List<StudentDtoResponse>> getAllStudents();
public Optional<StudentDtoResponse> saveStudent(StudentDtoRequest etudiant);
public Optional<StudentDtoResponse> getStudentById(Long id);
public  Optional<StudentDtoResponse> updateStudent(Long id, StudentDtoRequest etudiant);
public Optional<StudentDtoResponse> deleteStudentById(Long id);
}
