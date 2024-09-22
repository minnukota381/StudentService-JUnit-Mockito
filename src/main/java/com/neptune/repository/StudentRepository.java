package com.neptune.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.neptune.repository.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
