package com.groupeisi.ms1_gestschool.session;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface SessionService {
    SessionDtoResponse createSession(SessionDtoRequest request);
    List<SessionDtoResponse> getAllSessions();
    List<SessionDtoResponse> getSessionsByCourseId(Long id);
    Optional<SessionDtoResponse> getSessionById(Long id);
    Optional<SessionDtoResponse> updateSession(Long id, SessionDtoRequest request);
    boolean deleteSession(Long id);
  SessionDtoResponse endSession(Long id);
}
