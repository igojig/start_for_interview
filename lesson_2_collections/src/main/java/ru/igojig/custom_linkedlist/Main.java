package ru.igojig.custom_linkedlist;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {


        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        print_all(list);

        System.out.println("add at index #4 value: 99");
        list.add(4, 99);
        print_all(list);

        System.out.println("add at index #0 value: 255");
        list.add(0, 255);
        print_all(list);

        System.out.println("remove at index 2");
        System.out.println(list.remove(2));
        print_all(list);

        System.out.println("remove at index 0");
        System.out.println(list.remove(0));
        print_all(list);

        System.out.println("Add element 10 at last");
        list.add(10);
        print_all(list);

        System.out.println("remove at index 2");
        System.out.println(list.remove(2));
        print_all(list);

        System.out.println("remove at index 1");
        System.out.println(list.remove(1));
        print_all(list);

        System.out.println("remove at index 0");
        System.out.println(list.remove(0));
        print_all(list);

        System.out.println("Iterator");

        MyLinkedList<Integer> list1 = new MyLinkedList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);

        Iterator<Integer> it = list1.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

    }

    public static void print_all(MyLinkedList<?> list) {
        list.print_forward();
        list.print_backward();
        System.out.println();
    }
}
