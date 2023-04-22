package ru.igojig.custom_arraylist;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> arrayList = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(i);
        }

        System.out.println("Печать массива через get");
        for (int i = 0; i < 10; i++) {
            System.out.println(arrayList.get(i));
        }
        System.out.println();

        System.out.println("Печать массива через его метод");
        arrayList.print();
        System.out.println();

        System.out.println("Удаление элемента с индексом 9");
        arrayList.remove(9);
        arrayList.print();
        System.out.println();

        System.out.println("Удаление элемента с индексом 0");
        arrayList.remove(0);
        arrayList.print();
        System.out.println();

        System.out.println("Добавление по индексу 0 значения 10");
        arrayList.add(0, 10);
        arrayList.print();
        System.out.println();

        System.out.println("Добавление элемента - 333");
        arrayList.add(333);
        arrayList.print();
        System.out.println();

        while (arrayList.getLength() > 0) {
            System.out.println("Удаление элемента с индексом 0");
            arrayList.remove(0);
            arrayList.print();
            System.out.println();
        }

        System.out.println("Iterator");
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Iterator<Integer> it= list.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}
