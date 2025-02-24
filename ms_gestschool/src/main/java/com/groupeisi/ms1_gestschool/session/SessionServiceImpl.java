package com.groupeisi.ms1_gestschool.session;

import com.groupeisi.ms1_gestschool.classe.ClasseServiceImpl;
import com.groupeisi.ms1_gestschool.course.CourseEntity;
import com.groupeisi.ms1_gestschool.course.CourseRepository;
import com.groupeisi.ms1_gestschool.exception.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@Slf4j
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final CourseRepository courseRepository;
    private final MessageSource messageSource;
    private final Logger logger = LoggerFactory.getLogger(SessionServiceImpl.class);

    private final  SessionMapper sessionMapper;
    @Override
    public SessionDtoResponse createSession(SessionDtoRequest request) {
        logger.info("Tentative de création de session pour le cours avec l'id : {}", request.getCourseId());
        if (!courseRepository.findById(request.getCourseId()).isPresent()) {
            logger.error("Le cours avec l'id : {} n'a pas été trouvé", request.getCourseId());
            throw new EntityExistsException(messageSource.getMessage("course.notfound", new Object[]{request.getCourseId()}, Locale.getDefault()));
        }
        CourseEntity course = courseRepository.getReferenceById(request.getCourseId());
        logger.info("Cours trouvé avec l'id : {}, création de la session en cours", request.getCourseId());
        SessionEntity session = sessionMapper.toSessionEntity(request);
        session.setCourse(course);
        session.setBeginDate(LocalDateTime.now());
        session = sessionRepository.save(session);
        logger.info("Session créée avec succès pour le cours avec l'id : {}", request.getCourseId());
        return sessionMapper.toSessionDtoResponse(session);
    }


    @Override
    public List<SessionDtoResponse> getAllSessions() {
        return sessionRepository.findAll().stream()
                .map(sessionMapper::toSessionDtoResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<SessionDtoResponse> getSessionsByCourseId(Long id) {
        logger.info("Tentative de récupération des sessions pour le cours avec l'id : {}", id);

        List<SessionEntity> sessions = sessionRepository.getSessionsByCourseId(id);

        if (sessions.isEmpty()) {
            logger.warn("Aucune session trouvée pour le cours avec l'id : {}", id);
        } else {
            logger.info("Nombre de sessions récupérées pour le cours avec l'id {} : {}", id, sessions.size());
        }

        return sessions.stream()
                .map(sessionMapper::toSessionDtoResponse)
                .collect(Collectors.toList());
    }


    @Override
    public Optional<SessionDtoResponse> getSessionById(Long id) {
        logger.info("Tentative de récupération de la session avec l'id : {}", id);

        return sessionRepository.findById(id)
                .map(session -> {
                    logger.info("Session trouvée avec l'id : {}", id);
                    return sessionMapper.toSessionDtoResponse(session);
                })
                .or(() -> {
                    logger.error("Session non trouvée avec l'id : {}", id);
                    throw new com.groupeisi.ms1_gestschool.exception.EntityNotFoundException(
                            messageSource.getMessage("session.notfound", new Object[]{id}, Locale.getDefault())
                    );
                });
    }

    @Override
    public Optional<SessionDtoResponse> updateSession(Long id, SessionDtoRequest request) {
        logger.info("Tentative de mise à jour de la session avec l'id : {}", id);

        return Optional.ofNullable(sessionRepository.findById(id)
                .map(session -> {
                    logger.info("Session trouvée avec l'id : {}", id);
                    session.setName(request.getName());
                    session.setDescription(request.getDescription());
                    session.setArchive(false);

                    SessionEntity updatedSession = sessionRepository.save(session);
                    logger.info("Session mise à jour avec succès : {}", updatedSession.getId());
                    return sessionMapper.toSessionDtoResponse(updatedSession);
                })
                .orElseThrow(() -> {
                    logger.error("Session non trouvée avec l'id : {}", id);
                    return new com.groupeisi.ms1_gestschool.exception.EntityNotFoundException(
                            messageSource.getMessage("session.notfound", new Object[]{id}, Locale.getDefault())
                    );
                }));
    }
    @Override
    public boolean deleteSession(Long id) {
        logger.info("Tentative de suppression de la session avec l'id : {}", id);
        if (!sessionRepository.existsById(id)) {
            logger.error("Session non trouvée pour l'id : {}", id);
            throw new EntityNotFoundException(
                    messageSource.getMessage("session.notfound", new Object[]{id}, Locale.getDefault())
            );
        }
        sessionRepository.deleteById(id);
        logger.info("Session avec l'id : {} supprimée avec succès", id);
        return true;
    }

    @Override
    public SessionDtoResponse endSession(Long id) {
        logger.info("Tentative de fin de la session avec l'id : {}", id);
        return sessionRepository.findById(id).map(session -> {
            logger.info("Session trouvée, mise à jour de la date de fin pour l'id : {}", id);
            session.setEndDate(LocalDateTime.now());
            sessionRepository.save(session);
            logger.info("Session terminée avec succès, id : {}", id);
            return sessionMapper.toSessionDtoResponse(session);
        }).orElseThrow(() -> {
            logger.error("Session non trouvée pour l'id : {}", id);
            return new EntityNotFoundException("La session n'existe pas");
        });
    }

}
