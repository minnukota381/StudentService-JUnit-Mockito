package com.neptune;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.neptune.repository.StudentRepository;
import com.neptune.repository.model.Student;
import com.neptune.service.StudentService;

import org.junit.jupiter.api.extension.ExtendWith;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTestDemoJUnitMockitoApplicationTests {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    public void testGetStudentById() {
        Student mockStudent = new Student(1, "Helcy");
        Mockito.when(studentRepository.findById(1)).thenReturn(Optional.of(mockStudent));
        Student result = studentService.getStudentById(1);
        assertEquals("Helcy", result.getName());
    }
}
