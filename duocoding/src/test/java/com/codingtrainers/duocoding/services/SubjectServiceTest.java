package com.codingtrainers.duocoding.services;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import com.codingtrainers.duocoding.dto.input.SubjectRequestDTO;
import com.codingtrainers.duocoding.dto.output.SubjectResponseDTO;
import com.codingtrainers.duocoding.entities.Subject;
import com.codingtrainers.duocoding.repositories.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SubjectServiceTest {

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private SubjectService subjectService;

    private Subject sampleSubject;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        sampleSubject = new Subject();
        sampleSubject.setId(1L);
        sampleSubject.setName("Java");
        sampleSubject.setDescription("Java Subject");
        sampleSubject.setActive(true);
    }

    @Test
    public void testGetAll() {
        List<Subject> subjects = List.of(sampleSubject);

        when(subjectRepository.findAllByActiveTrue()).thenReturn(subjects);

        List<SubjectResponseDTO> result = subjectService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Java", result.get(0).getName());
    }

    @Test
    public void testGetByIdFound() {
        when(subjectRepository.findByIdAndActiveTrue(1L)).thenReturn(Optional.of(sampleSubject));

        Optional<SubjectResponseDTO> result = subjectService.getById(1L);

        assertTrue(result.isPresent());
        assertEquals("Java", result.get().getName());
    }

    @Test
    public void testGetByIdNotFound() {
        when(subjectRepository.findByIdAndActiveTrue(2L)).thenReturn(Optional.empty());

        Optional<SubjectResponseDTO> result = subjectService.getById(2L);

        assertFalse(result.isPresent());
    }

    @Test
    public void testCreateSubject_Success() {
        SubjectRequestDTO dto = new SubjectRequestDTO();
        dto.setName("DevOps");
        dto.setDescription("DevOps Subject");

        when(subjectRepository.save(any(Subject.class))).thenAnswer(invocation -> invocation.getArgument(0));

        assertDoesNotThrow(() -> subjectService.createSubject(dto));

        verify(subjectRepository, times(1)).save(any(Subject.class));
    }

    @Test
    public void testCreateSubject_MissingName() {
        SubjectRequestDTO dto = new SubjectRequestDTO();
        dto.setName(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            subjectService.createSubject(dto);
        });

        assertEquals("Subject name is required", exception.getMessage());
    }

    @Test
    public void testUpdateSubject_Success() {
        SubjectRequestDTO dto = new SubjectRequestDTO();
        dto.setName("Java");
        dto.setDescription("Java Subject");

        when(subjectRepository.findById(1L)).thenReturn(Optional.of(sampleSubject));
        when(subjectRepository.save(any(Subject.class))).thenAnswer(invocation -> invocation.getArgument(0));

        assertDoesNotThrow(() -> subjectService.updateSubject(1L, dto));

        verify(subjectRepository, times(1)).save(any(Subject.class));
    }

    @Test
    public void testUpdateSubject_NotFound() {
        SubjectRequestDTO dto = new SubjectRequestDTO();
        dto.setName("Angular");

        when(subjectRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            subjectService.updateSubject(1L, dto);
        });

        assertEquals("Subject not found", exception.getMessage());
    }

    @Test
    public void testDeleteSubject_Success() {
        when(subjectRepository.findById(1L)).thenReturn(Optional.of(sampleSubject));
        when(subjectRepository.save(any(Subject.class))).thenAnswer(invocation -> invocation.getArgument(0));

        assertDoesNotThrow(() -> subjectService.deleteSubject(1L));

        verify(subjectRepository, times(1)).save(argThat(subject -> !subject.getActive()));
    }

    @Test
    public void testDeleteSubject_NotFound() {
        when(subjectRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            subjectService.deleteSubject(1L);
        });

        assertEquals("Subject not found", exception.getMessage());
    }

    @Test
    public void testActivateSubject_Success() {
        sampleSubject.setActive(false);
        when(subjectRepository.findById(1L)).thenReturn(Optional.of(sampleSubject));
        when(subjectRepository.save(any(Subject.class))).thenAnswer(invocation -> invocation.getArgument(0));

        assertDoesNotThrow(() -> subjectService.activateSubject(1L));

        verify(subjectRepository, times(1)).save(argThat(subject -> subject.getActive()));
    }

    @Test
    public void testActivateSubject_NotFound() {
        when(subjectRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            subjectService.activateSubject(1L);
        });

        assertEquals("Subject not found", exception.getMessage());
    }
}