package com.groupeisi.ms_gestschool.ms_gestschool.session;

import com.groupeisi.ms1_gestschool.session.*;
import com.groupeisi.ms1_gestschool.course.CourseEntity;
import com.groupeisi.ms1_gestschool.course.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SessionServiceImplTest {
    @Mock
    private SessionRepository sessionRepository;
    @Mock
    private SessionMapper sessionMapper;
    @Mock
    private CourseRepository courseRepository;
    @InjectMocks
    private SessionServiceImpl sessionService;
    @Mock
    private MessageSource messageSource;
//
//    @Test
//    void createSessionOK() {
//        // Arrange
//        SessionDtoRequest request = this.getSessionDtoRequest();
//        SessionEntity sessionEntity = this.getSessionEntity();
//        SessionDtoResponse expectedResponse = this.getSessionDtoResponse();
//
//        when(sessionMapper.toSessionEntity(any())).thenReturn(sessionEntity);
//        when(sessionRepository.save(any())).thenReturn(sessionEntity);
//        when(sessionMapper.toSessionDtoResponse(any())).thenReturn(expectedResponse);
//
//        // Act
//       // Optional<SessionDtoResponse> sessionDtoResponse = sessionService.createSession(request);
//
//        // Assert
////        assertTrue(sessionDtoResponse.isPresent());
////        assertEquals(expectedResponse.getId(), sessionDtoResponse.get().getId());
//    }


//    @Test
//    void createSessionKO() {
//        SessionDtoRequest request = new SessionDtoRequest();
//        request.setCourseId(null);
//        assertThrows(IllegalArgumentException.class, () -> sessionService.createSession(request));
//    }

    @Test
    void getAllSessions() {
        when(sessionRepository.findAll()).thenReturn(Collections.singletonList(getSessionEntity()));
        when(sessionMapper.toSessionDtoResponse(any())).thenReturn(getSessionDtoResponse());

        List<SessionDtoResponse> sessions = sessionService.getAllSessions();
        assertFalse(sessions.isEmpty());
        assertEquals(1, sessions.size());
    }

    @Test
    void getSessionByIdOK() {
        when(sessionRepository.findById(any())).thenReturn(Optional.of(getSessionEntity()));
        when(sessionMapper.toSessionDtoResponse(any())).thenReturn(getSessionDtoResponse());

        Optional<SessionDtoResponse> response = sessionService.getSessionById(1L);
        assertTrue(response.isPresent());
        assertEquals(1L, response.get().getId());
    }

//    @Test
//    void getSessionByIdKO() {
//        when(sessionRepository.findById(any())).thenReturn(Optional.empty());
//
//        Optional<SessionDtoResponse> response = Optional.of(this.getSessionDtoResponse());
//        assertTrue(response.isPresent());
//    }

    @Test
    void deleteSessionByIdOK() {
        when(sessionRepository.existsById(1L)).thenReturn(true);
        doNothing().when(sessionRepository).deleteById(1L);

        boolean result = sessionService.deleteSession(1L);
        assertTrue(result);
    }


//    @Test
//    void updateSessionByIdOK() {
//        SessionDtoRequest request = getSessionDtoRequest();
//        SessionEntity sessionEntity = getSessionEntity();
//        SessionDtoResponse expectedResponse = getSessionDtoResponse();
//
//        when(courseRepository.getReferenceById(request.getCourseId())).thenReturn(new CourseEntity());
//        when(sessionRepository.findById(1L)).thenReturn(Optional.of(sessionEntity));
//        when(sessionRepository.save(sessionEntity)).thenReturn(sessionEntity);
//        when(sessionMapper.toSessionDtoResponse(sessionEntity)).thenReturn(expectedResponse);
//
//        Optional<SessionDtoResponse> response = sessionService.updateSession(1L, request);
//        assertTrue(response.isPresent());
//        assertEquals(expectedResponse.getId(), response.get().getId());
//    }
//
//    @Test
//    void updateSessionByIdKO() {
//        SessionDtoRequest request = getSessionDtoRequest();
//        when(sessionRepository.findById(1L)).thenReturn(Optional.empty());
//
//        Optional<SessionDtoResponse> response = sessionService.updateSession(1L, request);
//        assertFalse(response.isPresent());
//    }

    private SessionDtoRequest getSessionDtoRequest() {
        SessionDtoRequest session = new SessionDtoRequest();
        session.setId(1L);
        session.setName("Janvier");
        session.setDescription("La description de ma session");
        session.setCourseId(1L);
        return session;
    }

    private SessionEntity getSessionEntity() {
        SessionEntity session = new SessionEntity();
        session.setId(1L);
        session.setName("Janvier");
        session.setDescription("La description de ma session");
        return session;
    }

    private SessionDtoResponse getSessionDtoResponse() {
        SessionDtoResponse session = new SessionDtoResponse();
        session.setId(1L);
        session.setName("Janvier");
        session.setDescription("La description de ma session");
        return session;
    }
}
