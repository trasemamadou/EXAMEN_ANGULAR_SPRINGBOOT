package com.groupeisi.ms1_gestschool.classe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClasseRepository extends JpaRepository<ClasseEntity, Long> {
    Optional<ClasseEntity> findFirstByName(String name);
}
