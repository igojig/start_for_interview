package ru.igojig.dao;

import ru.igojig.entity.Student;

import java.util.List;

public interface StudentDao {
    Student findById(Long id);

    List<Student> findAll();

    void save(Student student);

    void saveBatch(List<Student> list);

    Student findByName(String name);


    void update(Student another);

    void deleteAll();

    void deleteById(Long id);


}
