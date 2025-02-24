package com.groupeisi.ms1_gestschool.session;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Long> {
  List<SessionEntity> getSessionsByCourseId(Long id);
}
