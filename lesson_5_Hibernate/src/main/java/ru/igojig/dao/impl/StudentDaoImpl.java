package ru.igojig.dao.impl;

import org.hibernate.Session;
import ru.igojig.dao.StudentDao;
import ru.igojig.entity.Student;
import ru.igojig.util.SessionUtil;

import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public Student findById(Long id) {
        try (Session session = SessionUtil.getSession()) {
            session.beginTransaction();
            Student student = session.get(Student.class, id);
            session.getTransaction().commit();
            return student;
        }
    }

    @Override
    public List<Student> findAll() {
        try (Session session = SessionUtil.getSession()) {
            session.beginTransaction();
            List<Student> students = session.createQuery("Select s from Student s", Student.class)
                    .getResultList();

            session.getTransaction().commit();
            return students;
        }
    }

    @Override
    public Student findByName(String name) {
        try (Session session = SessionUtil.getSession()) {
            session.beginTransaction();
            Student student = session.createQuery("Select s from Student s where s.name=:name", Student.class)
                    .setParameter("name", name)
                    .getSingleResult();
            session.getTransaction().commit();
            return student;
        }
    }

    @Override
    public void saveBatch(List<Student> list) {
        try (Session session = SessionUtil.getSession()) {
            session.beginTransaction();
            for (Student s : list) {
                session.persist(s);
            }
            session.getTransaction().commit();
        }
    }

    @Override
    public void save(Student student) {
        try (Session session = SessionUtil.getSession()) {
            session.beginTransaction();
            session.persist(student);
            session.getTransaction().commit();
        }
    }

    public void deleteAll(){
        try (Session session = SessionUtil.getSession()) {
            session.beginTransaction();
            session.createQuery("Delete from Student s").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = SessionUtil.getSession()) {
            session.beginTransaction();
            Student student = session.find(Student.class, id);
            session.remove(student);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Student another) {
        try (Session session = SessionUtil.getSession()) {
            session.beginTransaction();
            Student s=session.get(Student.class, another.getId());
            s.setMark(another.getMark());
            s.setName(another.getName());
            session.getTransaction().commit();
        }
    }
}
