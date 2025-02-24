package com.groupeisi.ms_gestschool.ms_gestschool.course;

import com.groupeisi.ms1_gestschool.classe.ClasseEntity;
import com.groupeisi.ms1_gestschool.classe.ClasseRepository;
import com.groupeisi.ms1_gestschool.course.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private CourseMapper courseMapper;
    @Mock
    private ClasseRepository classeRepository;
    @Mock
    private MessageSource messageSource;
    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    void createCourseOK() {
        // Arrange
        CourseDtoRequest request = this.getCourseDtoRequest();
        CourseEntity courseEntity = this.getCourseEntity();
        CourseDtoResponse expectedResponse = this.getCourseDtoResponse();
        when(courseMapper.toCourseEntity(request)).thenReturn(courseEntity);
        when(classeRepository.findById(request.getClasseId())).thenReturn(Optional.of(new ClasseEntity()));
        when(courseRepository.save(courseEntity)).thenReturn(courseEntity);
        when(courseMapper.toCourseDtoResponse(courseEntity)).thenReturn(expectedResponse);
        CourseDtoResponse actualResponse = courseService.createCourse(request);
        verify(classeRepository, times(1)).findById(request.getClasseId());
        verify(courseRepository, times(1)).save(courseEntity);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getAllCourses() {
        List<CourseEntity> entities = List.of(new CourseEntity());
        List<CourseDtoResponse> responses = List.of(new CourseDtoResponse());

        when(courseRepository.findAll()).thenReturn(entities);
        when(courseMapper.toCourseDtoResponse(any())).thenReturn(new CourseDtoResponse());

        List<CourseDtoResponse> result = courseService.getAllCourses();

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void getCourseByIdOK() {
        Long id = 1L;
        CourseEntity entity = new CourseEntity();
        CourseDtoResponse response = new CourseDtoResponse();

        when(courseRepository.findById(id)).thenReturn(Optional.of(entity));
        when(courseMapper.toCourseDtoResponse(entity)).thenReturn(response);

        Optional<CourseDtoResponse> result = courseService.getCourseById(id);

        assertTrue(result.isPresent());
    }

    @Test
    void updateCourseOK() {
        Long id = 1L;
        CourseDtoRequest request = new CourseDtoRequest();
        CourseEntity entity = new CourseEntity();
        CourseDtoResponse response = new CourseDtoResponse();

        when(courseRepository.findById(id)).thenReturn(Optional.of(entity));
        when(courseRepository.save(entity)).thenReturn(entity);
        when(courseMapper.toCourseDtoResponse(entity)).thenReturn(response);

        Optional<CourseDtoResponse> result = courseService.updateCourse(id, request);

        assertTrue(result.isPresent());
    }

    public boolean deleteCourse(Long id) {
        Optional<CourseEntity> course = courseRepository.findById(id);
        if (course.isPresent()) {
            courseRepository.delete(course.get());
            return true;
        }
        return false;
    }

    public CourseDtoRequest getCourseDtoRequest(){
        CourseDtoRequest courseDtoRequest=new CourseDtoRequest();
        courseDtoRequest.setId(1L);
        courseDtoRequest.setName("DEVOPS");
        courseDtoRequest.setArchive(false);
        courseDtoRequest.setClasseId(1L) ;
        return courseDtoRequest;
    }
    public CourseEntity getCourseEntity(){
        CourseEntity courseEntity=new CourseEntity();
        courseEntity.setId(1L);
        courseEntity.setName("DEVOPS");
        courseEntity.setArchive(false);
        courseEntity.setClasse(this.getClasseEntity());
        return courseEntity;
    }
    public ClasseEntity getClasseEntity(){
        ClasseEntity classe= new ClasseEntity();
        classe.setId(1L);
        classe.setName("M2GL");
        classe.setDescription("LA CLASSE M2GL");
        classe.setArchive(false);
        return classe;
    }
    public CourseDtoResponse getCourseDtoResponse(){
        CourseDtoResponse courseDtoResponse=new CourseDtoResponse();
        courseDtoResponse.setId(1L);
        courseDtoResponse.setName("DEVOPS");
        courseDtoResponse.setArchive(false);
        return courseDtoResponse;
    }
}
