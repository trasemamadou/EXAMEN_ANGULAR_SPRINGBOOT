package com.groupeisi.ms1_gestschool.classe;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

public interface ClasseService {
 Optional<List<ClasseDtoResponse>> getAllClasses();
 Optional<ClasseDtoResponse> getClasseById(Long id);
  Optional<ClasseDtoResponse> addClasse(ClasseDtoRequest classe);
   Optional<ClasseDtoResponse> updateClasse(Long id, ClasseDtoRequest classeDtoRequest);
   Optional<?> deleteClasse(Long id);
}
