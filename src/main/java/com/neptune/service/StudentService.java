package com.neptune.service;


import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.neptune.repository.StudentRepository;
import com.neptune.repository.model.Student;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(null);
    }
}
