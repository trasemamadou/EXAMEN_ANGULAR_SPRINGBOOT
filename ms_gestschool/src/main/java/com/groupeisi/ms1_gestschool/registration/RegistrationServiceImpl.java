package com.groupeisi.ms1_gestschool.registration;

import com.groupeisi.ms1_gestschool.classe.ClasseEntity;
import com.groupeisi.ms1_gestschool.classe.ClasseRepository;
import com.groupeisi.ms1_gestschool.classe.ClasseServiceImpl;
import com.groupeisi.ms1_gestschool.exception.EntityExistsException;
import com.groupeisi.ms1_gestschool.exception.EntityNotFoundException;
import com.groupeisi.ms1_gestschool.student.StudentEntity;
import com.groupeisi.ms1_gestschool.student.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.bridge.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final StudentRepository studentRepository;
    private final ClasseRepository classeRepository;
    private final RegistrationMapper registrationMapper;
    private final MessageSource messageSource;
    private final Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    @Override
    public Optional<List<RegistrationDtoResponse>> getAllRegistrations() {
        List<RegistrationEntity> registrations = registrationRepository.findAll();
        return Optional.of(registrationMapper.toListRegistrationDtoResponse(registrations));
    }

    @Override
    public Optional<RegistrationDtoResponse> addRegistration(RegistrationDtoRequest registrationDto) {
        if(!classeRepository.findById(registrationDto.getClasseId()).isPresent()){
            logger.info("La classe avec l'id {} est introuvable", registrationDto.getClasseId());
            throw new EntityExistsException(messageSource.getMessage("classe.notfound", new Object[]{registrationDto.getClasseId()}, Locale.getDefault()));
        }
        if(!studentRepository.findById(registrationDto.getStudentId()).isPresent()){
            logger.info("L'étudiant avec l'id {} est introuvable", registrationDto.getStudentId());
            throw new EntityNotFoundException(messageSource.getMessage("student.notfound", new Object[]{registrationDto.getStudentId()}, Locale.getDefault()));
        }
        RegistrationEntity registration = registrationMapper.toRegistrationEntity(registrationDto);
        logger.info("Enregistrement de l'inscription: {}", registration.getName());
        RegistrationEntity savedRegistration = registrationRepository.save(registration);
        return Optional.of(registrationMapper.toRegistrationDtoResponse(savedRegistration));
    }
    @Override
    public Optional<RegistrationDtoResponse> updateRegistration(Long id, RegistrationDtoRequest request) {
        return registrationRepository.findById(id).map(existingRegistration -> {

            existingRegistration.setName(request.getName());
            existingRegistration.setDescription(request.getDescription());
            existingRegistration.setArchive(request.isArchive());
            if (request.getStudentId() != null) {
                StudentEntity student = studentRepository.findById(request.getStudentId())
                        .orElseThrow(() -> {
                            logger.error("L'inscription avec l'id : {} est introuvable", request.getStudentId());
                            return new EntityNotFoundException(
                                    messageSource.getMessage("registration.notfound", new Object[]{request.getStudentId()}, Locale.getDefault()));
                        });
                existingRegistration.setStudent(student);
            }

            if (request.getClasseId() != null) {
                logger.info("Recherche de la classe avec l'id : {}", request.getClasseId());
                ClasseEntity classe = classeRepository.findById(request.getClasseId())
                        .orElseThrow(() -> {
                            logger.error("Classe avec l'id : {} introuvable", request.getClasseId());
                            return new EntityNotFoundException(messageSource.getMessage("classe.notfound", new Object[]{request.getClasseId()}, Locale.getDefault()));
                        });
                logger.info("Classe avec l'id : {} trouvée et assignée", request.getClasseId());
                existingRegistration.setClasse(classe);
            }

            if (request.getStudentId() != null) {
                logger.info("Recherche de l'étudiant avec l'id : {}", request.getStudentId());
                StudentEntity student = studentRepository.findById(request.getStudentId())
                        .orElseThrow(() -> {
                            logger.error("Étudiant avec l'id : {} introuvable", request.getStudentId());
                            return new EntityNotFoundException(messageSource.getMessage("student.notfound", new Object[]{request.getStudentId()}, Locale.getDefault()));
                        });
                logger.info("Étudiant avec l'id : {} trouvé et assigné", request.getStudentId());
            }

            RegistrationEntity savedRegistration = registrationRepository.save(existingRegistration);
            return registrationMapper.toRegistrationDtoResponse(savedRegistration);
        });
    }
    @Override
    public Optional<?> deleteRegistration(Long id) {
        logger.info("Tentative de suppression de l'inscription avec l'id : {}", id);

        RegistrationEntity registration = registrationRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("L'inscription avec l'id : {} n'a pas été trouvée", id);
                    return new EntityNotFoundException(messageSource.getMessage("registration.notfound", new Object[]{id}, Locale.getDefault()));
                });

        logger.info("Inscription avec l'id : {} trouvée, suppression en cours", id);
        registrationRepository.delete(registration);

        logger.info("Inscription avec l'id : {} supprimée avec succès", id);
        return Optional.empty();
    }


}
