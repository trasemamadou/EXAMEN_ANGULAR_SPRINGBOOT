package com.groupeisi.ms1_gestschool.classe;

import com.groupeisi.ms1_gestschool.exception.EntityExistsException;
import com.groupeisi.ms1_gestschool.exception.EntityNotFoundException;
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
@Slf4j
@RequiredArgsConstructor
public class ClasseServiceImpl implements ClasseService {
    private final ClasseRepository classeRepository;
    private final ClasseMapper classeMapper;
    private final MessageSource messageSource;
    private final Logger logger = LoggerFactory.getLogger(ClasseServiceImpl.class);
    @Override
    public Optional<List<ClasseDtoResponse>> getAllClasses() {
        return Optional.of(classeMapper.toListClasseDtoResponse(classeRepository.findAll()));
    }

    @Override
    public Optional<ClasseDtoResponse> getClasseById(Long id) {
        return Optional.of(classeRepository.findById(id)).map(((classe)->classeMapper.toClasseDtoResponse(classe.get())));
    }

    @Override
    public Optional<ClasseDtoResponse> addClasse(ClasseDtoRequest classe) {
        if (classeRepository.findFirstByName(classe.getName()).isPresent() ){
            throw new EntityExistsException(messageSource.getMessage("classeName.exist", new Object[]{classe.getName()}, Locale.getDefault()));
        }
        return Optional.of(classeMapper.toClasseDtoResponse(
                classeRepository.save(classeMapper.toClasseEntity(classe)))
        );
    }
    @Override
    public Optional<ClasseDtoResponse> updateClasse(Long id, ClasseDtoRequest request) {
        ClasseEntity classe = classeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("classe.notfound", new Object[]{request.getId()}, Locale.getDefault())));

        Optional<ClasseEntity> existingClasse = classeRepository.findFirstByName(request.getName());
        if (existingClasse.isPresent() && !existingClasse.get().getId().equals(classe.getId())) {
            throw new EntityExistsException(messageSource.getMessage("classeName.exist", new Object[]{request.getName()}, Locale.getDefault()));
        }

        classe.setName(request.getName());
        classe.setDescription(request.getDescription());
        classe.setArchive(request.isArchive());

        classeRepository.save(classe);

        return Optional.of(classeMapper.toClasseDtoResponse(classe));
    }

    @Override
    public Optional<?> deleteClasse(Long id) {
        if (classeRepository.findById(id).isPresent()){
            throw new EntityNotFoundException(messageSource.getMessage("classe.notfound", new Object[]{id}, Locale.getDefault()));
        }
        classeRepository.deleteById(id);

        return  Optional.empty();
    }
}
