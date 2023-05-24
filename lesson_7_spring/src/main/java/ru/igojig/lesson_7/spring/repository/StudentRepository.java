package ru.igojig.lesson_7.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.igojig.lesson_7.spring.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
