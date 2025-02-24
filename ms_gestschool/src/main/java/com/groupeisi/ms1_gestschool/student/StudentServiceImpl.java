package com.groupeisi.ms1_gestschool.student;

import com.groupeisi.ms1_gestschool.exception.EntityExistsException;
import com.groupeisi.ms1_gestschool.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {
    private   final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final MessageSource messageSource;
    private final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    @Override
    public Optional<List<StudentDtoResponse>> getAllStudents() {
        List<StudentEntity> students = studentRepository.findAll();
        return Optional.of(studentMapper.toStudentDtoResponseList(students));
    }
    @Override
    public Optional<StudentDtoResponse> saveStudent(StudentDtoRequest student) {

        if (studentRepository.findByEmailPro(student.getEmailPro()).isPresent()) {
            logger.error("Le mail professionnel : {} existe déjà", student.getEmailPro());
            throw new EntityExistsException(messageSource.getMessage("emailPerso.exist", new Object[]{student.getEmailPro()}, Locale.getDefault()));
        }
        if (studentRepository.findByEmailPerso(student.getEmailPerso()).isPresent()) {
            logger.error("Le mail personnel : {} existe déjà", student.getEmailPerso());
            throw new EntityExistsException(messageSource.getMessage("emailPro.exist", new Object[]{student.getEmailPro()}, Locale.getDefault()));
        }
        if (studentRepository.findByPhoneNumber(student.getPhoneNumber()).isPresent()) {
            logger.error("Le numéro de téléphone : {} existe déjà", student.getPhoneNumber());
            throw new EntityExistsException(messageSource.getMessage("phoneNumber.exist", new Object[]{student.getPhoneNumber()}, Locale.getDefault()));
        }
        if (studentRepository.findByRegistrationNu(student.getRegistrationNu()).isPresent()) {
            logger.error("Le numéro d'inscription : {} existe déjà", student.getRegistrationNu());
            throw new EntityExistsException(messageSource.getMessage("registrationNu.exist", new Object[]{student.getRegistrationNu()}, Locale.getDefault()));
        }

        logger.info("Enregistrement du nouvel étudiant avec le numéro d'inscription : {}", student.getRegistrationNu());
        var student1 = studentRepository.save(studentMapper.toStudentEntity(student));

        logger.info("Nouvel étudiant enregistré avec succès : {}", student1.getId());
        return Optional.of(studentMapper.toStudentDtoResponse(student1));
    }
    @Override
    public Optional<StudentDtoResponse> getStudentById(Long id) {
        return Optional.of(studentRepository.findById(id)
                .map(studentMapper::toStudentDtoResponse)
                .orElseThrow(() -> {
                    logger.error("L'étudiant avec l'ID : {} n'existe pas", id);
                    throw  new EntityNotFoundException(messageSource.getMessage("student.notfound", new Object[]{id}, Locale.getDefault()));
                }));
    }
    @Override
    public Optional<StudentDtoResponse> updateStudent(Long id, StudentDtoRequest student) {

        if (!studentRepository.findById(id).isPresent()) {
            logger.error("L'étudiant avec l'ID : {} n'a pas été trouvé", id);
            throw new EntityNotFoundException(messageSource.getMessage("student.notfound", new Object[]{id}, Locale.getDefault()));
        }
        if (studentRepository.findByEmailPro(student.getEmailPro()).isPresent()) {
            logger.error("Le mail professionnel : {} existe déjà", student.getEmailPro());
            throw new EntityExistsException(messageSource.getMessage("emailPerso.exist", new Object[]{student.getEmailPro()}, Locale.getDefault()));
        }
        if (studentRepository.findByEmailPerso(student.getEmailPerso()).isPresent()) {
            logger.error("Le mail personnel : {} existe déjà", student.getEmailPerso());
            throw new EntityExistsException(messageSource.getMessage("emailPro.exist", new Object[]{student.getEmailPro()}, Locale.getDefault()));
        }
        if (studentRepository.findByPhoneNumber(student.getPhoneNumber()).isPresent()) {
            logger.error("Le numéro de téléphone : {} existe déjà", student.getPhoneNumber());
            throw new EntityExistsException(messageSource.getMessage("phoneNumber.exist", new Object[]{student.getPhoneNumber()}, Locale.getDefault()));
        }
        if (studentRepository.findByRegistrationNu(student.getRegistrationNu()).isPresent()) {
            logger.error("Le numéro d'inscription : {} existe déjà", student.getRegistrationNu());
            throw new EntityExistsException(messageSource.getMessage("registrationNu.exist", new Object[]{student.getRegistrationNu()}, Locale.getDefault()));
        }
        StudentEntity student1 = studentRepository.findById(id).orElseThrow(() -> {
            logger.error("L'étudiant avec l'ID : {} n'a pas été trouvé pour la mise à jour", id);
            return new EntityNotFoundException("L'étudiant n'existe pas");
        });

        logger.info("Mise à jour des informations de l'étudiant avec l'ID : {}", id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setAddress(student.getAddress());
        student1.setEmailPerso(student.getEmailPerso());
        student1.setEmailPro(student.getEmailPro());
        student1.setPhoneNumber(student.getPhoneNumber());
        StudentEntity savedStudent = studentRepository.save(student1);
        logger.info("Les informations de l'étudiant avec l'ID : {} ont été mises à jour avec succès", id);

        return Optional.of(studentMapper.toStudentDtoResponse(savedStudent));
    }
    @Override
    public Optional<StudentDtoResponse> deleteStudentById(Long id) {
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("L'étudiant avec l'ID : {} n'a pas été trouvé", id);
                    return new EntityNotFoundException(
                            messageSource.getMessage("student.notfound", new Object[]{id}, Locale.getDefault())
                    );
                });
        logger.info("Suppression de l'étudiant avec l'ID : {}", id);
        studentRepository.deleteById(student.getId());
        logger.info("L'étudiant avec l'ID : {} a été supprimé avec succès", id);
        return Optional.of(studentMapper.toStudentDtoResponse(student));
    }
}
