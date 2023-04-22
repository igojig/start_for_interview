package ru.igojig.custom_arraylist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManyElementsTest {

    private static final int ELEMENT_COUNT=10_000;

    MyArrayList<Integer> myList;

    @BeforeEach
    public void init(){
        myList=new MyArrayList<>();
        for(int i=0;i<ELEMENT_COUNT;i++){
            myList.add(i);
        }
    }

    @Test
    void getTest() {
        assertEquals(0, myList.get(0));
        assertEquals(9_999, myList.get(9_999));
        assertEquals(ELEMENT_COUNT, myList.getLength());

        for(int i=0;i<ELEMENT_COUNT;i++){
            assertEquals(i, myList.get(i));
        }

        List<Integer> expected=getExpectedList();
        assertIterableEquals(expected, myList);
    }

    @Test
    void throwTest(){
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> myList.get(ELEMENT_COUNT));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> myList.get(-1));
    }

    public List<Integer> getExpectedList(){
        List<Integer> expected=new ArrayList<>();
        for(int i=0;i<ELEMENT_COUNT;i++){
            expected.add(i);
        }
        return expected;
    }
}