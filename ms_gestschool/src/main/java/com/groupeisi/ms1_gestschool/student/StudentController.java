package com.groupeisi.ms1_gestschool.student;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class StudentController {
    private final StudentServiceImpl studentService;
    @GetMapping
    public ResponseEntity<List<StudentDtoResponse>> getAllStudent(){
             return studentService.getAllStudents().map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentDtoResponse> getStudentById(@PathVariable Long id){
        Optional<StudentDtoResponse> student = studentService.getStudentById(id);
        if (student.isEmpty()){
            ResponseEntity.status(404).body("The student does not exist");
        }
        return student.map(ResponseEntity::ok).orElseThrow(() -> new EntityNotFoundException("Une erreur s'est produit lors de la récuperation de l'étudiant"));
    }
    @PostMapping
    public ResponseEntity<StudentDtoResponse> createStudent(@Valid @RequestBody  StudentDtoRequest student) {
        Optional<StudentDtoResponse> savedStudentOptional = studentService.saveStudent(student);

        return new ResponseEntity<>(savedStudentOptional.get(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDtoResponse> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentDtoRequest student){
        return studentService.updateStudent(id, student)
                .map(ResponseEntity::ok).orElseThrow(() -> new EntityNotFoundException("The student does not exist"));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<StudentDtoResponse> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
      return   ResponseEntity.noContent().build();
    }}
