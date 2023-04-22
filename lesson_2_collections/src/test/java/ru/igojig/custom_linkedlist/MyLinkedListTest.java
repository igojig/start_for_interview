package ru.igojig.custom_linkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {

    private MyLinkedList<Integer> myLinkedList;

    @BeforeEach
    public void init() {
        myLinkedList = new MyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            myLinkedList.add(i);
        }
    }

    @Test
    public void initTest() {
        System.out.println("Исходный массив");
        myLinkedList.print();

        assertIterableEquals(myLinkedList, new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)));
        assertEquals(10, myLinkedList.getLength());
    }

    @Test
    public void getAllTest() {
        for (int i = 0; i < 10; i++) {
            assertEquals(i, myLinkedList.get(i));
        }
    }


    @Test
    public void addFirstTest() {
        System.out.println("Add element at index #0 value=-1");
        myLinkedList.add(0, -1);
        assertIterableEquals(myLinkedList, new ArrayList<>(List.of(-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9)));
        assertEquals(11, myLinkedList.getLength());
        myLinkedList.print();

    }

    @Test
    public void addLastTest() {
        System.out.println("Add element at index #9 value=10");
        myLinkedList.add(9, 10);
        assertIterableEquals(myLinkedList, new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 9)));
        assertEquals(11, myLinkedList.getLength());
        myLinkedList.print();

    }

    @Test
    public void throwTest() {
        System.out.println("Add element at index #-1");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> myLinkedList.add(-1, -1));
        myLinkedList.print();

        System.out.println("Add element at index #10");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> myLinkedList.add(10, 10));
        myLinkedList.print();

        System.out.println("Remove element at index #-1");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> myLinkedList.remove(-1));
        myLinkedList.print();

        System.out.println("Remove element at index #10");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> myLinkedList.remove(10));
        myLinkedList.print();

    }

    @Test
    public void addTest() {
        System.out.println("Add element at index #5 value:99");
        myLinkedList.add(5, 99);
        assertIterableEquals(myLinkedList, new ArrayList<>(List.of(0, 1, 2, 3, 4, 99, 5, 6, 7, 8, 9)));
        assertEquals(11, myLinkedList.getLength());
        myLinkedList.print();

        System.out.println("Add element at index #5 value:100");
        myLinkedList.add(5, 100);
        assertIterableEquals(myLinkedList, new ArrayList<>(List.of(0, 1, 2, 3, 4, 100, 99, 5, 6, 7, 8, 9)));
        assertEquals(12, myLinkedList.getLength());
        myLinkedList.print();
    }

    @Test
    public void removeFirstTest() {
        System.out.println("Исходный массив");
        myLinkedList.print();

        System.out.println("Remove element at index #0. Expected value:0");
        assertEquals(0, myLinkedList.remove(0));
        assertEquals(9, myLinkedList.getLength());
        myLinkedList.print();

    }

    @Test
    public void removeLastTest() {
        System.out.println("Исходный массив");
        myLinkedList.print();

        System.out.println("Remove element at index #9. Expected value:9");
        assertEquals(9, myLinkedList.remove(9));
        assertEquals(9, myLinkedList.getLength());
        myLinkedList.print();

    }


    @Test
    public void addAndRemoveTest() {
        System.out.println("Add element at index #5 value:99");
        myLinkedList.add(5, 99);
        assertIterableEquals(myLinkedList, new ArrayList<>(List.of(0, 1, 2, 3, 4, 99, 5, 6, 7, 8, 9)));
        assertEquals(11, myLinkedList.getLength());
        myLinkedList.print();

        System.out.println("Add element at index #5 value:100");
        myLinkedList.add(5, 100);
        assertIterableEquals(myLinkedList, new ArrayList<>(List.of(0, 1, 2, 3, 4, 100, 99, 5, 6, 7, 8, 9)));
        assertEquals(12, myLinkedList.getLength());
        myLinkedList.print();


        System.out.println("Remove element at index#3");
        assertEquals(3, myLinkedList.remove(3));
        assertIterableEquals(myLinkedList, new ArrayList<>(List.of(0, 1, 2, 4, 100, 99, 5, 6, 7, 8, 9)));
        assertEquals(11, myLinkedList.getLength());
        myLinkedList.print();

        System.out.println("Remove element at index#4");
        assertEquals(100, myLinkedList.remove(4));
        assertIterableEquals(myLinkedList, new ArrayList<>(List.of(0, 1, 2, 4, 99, 5, 6, 7, 8, 9)));
        assertEquals(10, myLinkedList.getLength());
        myLinkedList.print();

    }

    @Test
    public void removeAllTestByIndex0() {
        System.out.println("Исходный массив");
        myLinkedList.print();

        List<Integer> expectedList;

        for (int i = 0; i < 10; i++) {
            System.out.println("remove #" + i);
            assertEquals(i, myLinkedList.remove(0));
            expectedList = getExpectedList(10 - i);
            assertIterableEquals(expectedList, myLinkedList);
            assertEquals(10 - i - 1, myLinkedList.getLength());
            myLinkedList.print();
        }

    }

    public List<Integer> getExpectedList(int elementCount) {
        List<Integer> list = new ArrayList<>();
        for (int i = 10 - elementCount + 1; i < 10; i++) {
            list.add(i);
        }
        return list;
    }

    @Test
    public void removeAllByLastIndex() {
        System.out.println("Исходный массив");
        myLinkedList.print();

        List<Integer> expected;
        for (int i = 9; i >= 0; i--) {
            System.out.println("Remove at index #" + i);
            assertEquals(i, myLinkedList.remove(i));
            assertEquals(i, myLinkedList.getLength());
            expected = getExpectedList1(i);
            assertIterableEquals(expected, myLinkedList);
            myLinkedList.print();
        }
    }

    public List<Integer> getExpectedList1(int elementCount) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < elementCount; i++) {
            list.add(i);
        }
        return list;
    }
}