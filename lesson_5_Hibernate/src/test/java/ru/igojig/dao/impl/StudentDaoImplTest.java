package ru.igojig.dao.impl;

import org.hibernate.Session;
import org.junit.jupiter.api.*;
import ru.igojig.dao.StudentDao;
import ru.igojig.entity.Student;
import ru.igojig.util.SessionUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentDaoImplTest {
    private final static int ELEMENTS=10;
    private  List<Student> expectedList;



    static StudentDao studentDao;

    @BeforeAll
    public static void initAll(){
        SessionUtil.init();

    }

    @AfterAll
    public static void destroyAll(){
        SessionUtil.close();
    }

    @BeforeEach
    public void init(){
        expectedList =new ArrayList<>();
        for(int i=1;i<=ELEMENTS;i++){
            expectedList.add(new Student((long)i, i+"_", i));
        }


        studentDao = new StudentDaoImpl();
        // заполняем БД
        List<Student> students = new ArrayList<>();
        for (int i = 1; i <= ELEMENTS; i++) {
            students.add(new Student( i + "_", i));
        }
        studentDao.saveBatch(students);
    }

    @AfterEach
    public void end(){
        Session session=SessionUtil.getSession();
        session.beginTransaction();
        session.createNativeQuery("truncate  table students", Student.class).executeUpdate();
        session.getTransaction().commit();
        studentDao=null;

    }

    @Test
    public void getAllTest(){
        List<Student> list=studentDao.findAll();
        assertIterableEquals(expectedList, list);
    }

    @Test
    public void getFirstTest(){
        Student testStudent=new Student(1L, "1_", 1);
        Student actual=studentDao.findById(1L);
        assertEquals(actual, testStudent);
    }

    @Test
    public void getLastTest(){
        Student testStudent=new Student((long)ELEMENTS, ELEMENTS+"_", ELEMENTS);
        Student actual=studentDao.findById((long)ELEMENTS);
        assertEquals(actual, testStudent);
    }

    @Test
    public void updateTest(){
        for(Student s:expectedList){
            s.setMark(s.getMark()+10);
            s.setName("_" + s.getName());
            studentDao.update(s);
        }
        List<Student> actualList=studentDao.findAll();
        assertIterableEquals(expectedList, actualList);
    }

    @Test
    public void deleteByIdTest(){
        studentDao.deleteById(1L);
        studentDao.deleteById(3L);
        studentDao.deleteById(5L);
        studentDao.deleteById(9L);

        expectedList.remove(8);
        expectedList.remove(4);
        expectedList.remove(2);
        expectedList.remove(0);

        List<Student> actual=studentDao.findAll();

        assertIterableEquals(expectedList, actual);

    }

    @Test
    public void deleteAllTest(){
        List<Student> testList= Collections.EMPTY_LIST;
        studentDao.deleteAll();
        List<Student> actual=studentDao.findAll();
        assertIterableEquals(actual, testList);
    }


}