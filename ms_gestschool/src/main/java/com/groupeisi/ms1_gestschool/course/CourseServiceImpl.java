package com.groupeisi.ms1_gestschool.course;
import com.groupeisi.ms1_gestschool.classe.ClasseRepository;
import com.groupeisi.ms1_gestschool.exception.EntityNotFoundException;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {

    private final   CourseRepository courseRepository;
    private final ClasseRepository classeRepository;
    private  final CourseMapper courseMapper;
    private final MessageSource messageSource;
    private final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Override
    public CourseDtoResponse createCourse(CourseDtoRequest request) {
        CourseEntity course = courseMapper.toCourseEntity(request);
        if (!classeRepository.findById(request.getClasseId()).isPresent()){
            throw new EntityNotFoundException(messageSource.getMessage("classe.notfound", new Object[]{request.getClasseId()}, Locale.getDefault()));
        }
        logger.info("Engregistrement du cours: {}", course.getName());
        course = courseRepository.save(course);
        return courseMapper.toCourseDtoResponse(course);
    }

    @Override
    public List<CourseDtoResponse> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(courseMapper::toCourseDtoResponse)
                .collect(Collectors.toList());
    }
    @Override
    public Optional<CourseDtoResponse> getCourseById(Long id) {
        if (!courseRepository.findById(id).isPresent()) {
            logger.info("L'ID du cours demandé n'existe pas : {}", id);
            throw new EntityNotFoundException(messageSource.getMessage("course.notfound", new Object[]{id}, Locale.getDefault()));
        }
        return courseRepository.findById(id)
                .map(courseMapper::toCourseDtoResponse);
    }

    @Override
    public Optional<CourseDtoResponse> updateCourse(Long id, CourseDtoRequest request) {
        if (!courseRepository.findById(id).isPresent()){
            logger.info("Cours avec l'id {} est introuvable", id);
            throw new EntityNotFoundException(messageSource.getMessage("course.notfound", new Object[]{id}, Locale.getDefault()));
        }
        return courseRepository.findById(id).map(course -> {
            course.setName(request.getName());
            course.setDescription(request.getDescription());
            course.setArchive(request.isArchive());
            CourseEntity updatedCourse = courseRepository.save(course);
            return courseMapper.toCourseDtoResponse(updatedCourse);
        });
    }

    @Override
    public boolean deleteCourse(Long id) {
        if (!courseRepository.findById(id).isPresent()){
            logger.info("Le cours avec l'id {} est introuvable", id);
            throw new EntityNotFoundException(messageSource.getMessage("course.notfound", new Object[]{id}, Locale.getDefault()));
        }
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
