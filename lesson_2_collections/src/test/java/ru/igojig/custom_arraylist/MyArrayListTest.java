package ru.igojig.custom_arraylist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MyArrayListTest {
    private static final int ELEMENT_COUNT=6;

    MyArrayList<Integer> myList;

    @BeforeEach
    public void init(){
        myList=new MyArrayList<>();
        for(int i=0;i<ELEMENT_COUNT;i++){
            myList.add(i);
        }
    }

    @Test
    void initTest(){
        assertIterableEquals(myList, new ArrayList<>(Arrays.asList(0,1,2,3,4,5)));
        assertEquals(6, myList.getLength());

        MyArrayList<Integer> list1=new MyArrayList<>();
        assertEquals(0, list1.getLength());
        assertThrows(ArrayIndexOutOfBoundsException.class, ()->list1.get(0));
    }

    @Test
    void getTest(){
        System.out.println("Print all elements");
        myList.print();

        assertEquals(6, myList.getLength());

        for(int i=0;i<ELEMENT_COUNT;i++){
            assertEquals(i, myList.get(i));
        }
    }

    @Test
    void addAndRemoveTest(){
        System.out.println("Исходный массив");
        myList.print();

        assertIterableEquals(myList, new ArrayList<>(Arrays.asList(0,1,2,3,4,5)));
        assertEquals(6, myList.getLength());

        System.out.println("Удаляем элемент с индексом 0");
        assertEquals(0, myList.remove(0));
        assertIterableEquals(myList, new ArrayList<>(Arrays.asList(1,2,3,4,5)));
        assertEquals(5, myList.getLength());
        myList.print();

        System.out.println("Удаляем элемент с индексом 4");
        assertEquals(5, myList.remove(4));
        assertIterableEquals(myList, new ArrayList<>(Arrays.asList(1,2,3,4)));
        assertEquals(4, myList.getLength());
        myList.print();

        System.out.println("Удаляем элемент с индексом 2");
        assertEquals(3, myList.remove(2));
        assertIterableEquals(myList, new ArrayList<>(Arrays.asList(1,2,4)));
        assertEquals(3, myList.getLength());
        myList.print();

        System.out.println("Добавляем элемент - 777");
        myList.add(777);
        assertIterableEquals(myList, new ArrayList<>(Arrays.asList(1,2,4,777)));
        assertEquals(4, myList.getLength());
        myList.print();

        System.out.println("Удаляем элемент с индексом 0");
        assertEquals(1, myList.remove(0));
        assertIterableEquals(myList, new ArrayList<>(Arrays.asList(2,4,777)));
        assertEquals(3, myList.getLength());
        myList.print();

        System.out.println("Удаляем элемент с индексом 2");
        assertEquals(777, myList.remove(2));
        assertIterableEquals(myList, new ArrayList<>(Arrays.asList(2,4)));
        assertEquals(2, myList.getLength());
        myList.print();

        System.out.println("Удаляем элемент с индексом 0");
        assertEquals(2, myList.remove(0));
        assertIterableEquals(myList, new ArrayList<>(Arrays.asList(4)));
        assertEquals(1, myList.getLength());
        myList.print();

        System.out.println("Удаляем элемент с индексом 0");
        assertEquals(4, myList.remove(0));
        assertIterableEquals(myList, new ArrayList<>(Arrays.asList()));
        assertEquals(0, myList.getLength());
        myList.print();
    }

    @Test
    void addAtIndexTest(){
        System.out.println("Исходный массив");
        myList.print();

        System.out.println("Add at index #0 value:10");
        myList.add(0, 10);
        assertEquals(7, myList.getLength());
        assertIterableEquals(myList, new ArrayList<>(Arrays.asList(10, 0,1,2,3,4,5)));
        myList.print();

        System.out.println("Add at index #6 value:22");
        myList.add(6, 22);
        assertEquals(8, myList.getLength());
        assertIterableEquals(myList, new ArrayList<>(Arrays.asList(10, 0,1,2,3,4,22, 5)));
        myList.print();

        assertThrows(ArrayIndexOutOfBoundsException.class, ()->myList.add(8, 99));

    }
}
