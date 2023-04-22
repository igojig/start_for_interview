package ru.igojig.custom_linkedlist;

import java.util.Iterator;

public class MyLinkedList<T> implements Iterable<T>{

    //начало списка
    private Node<T> head = null;
    //конец списка
    private Node<T> tail = null;
    //количество элементов
    private int length = 0;

    private static class Node<T> {

        private final T value;
        private Node<T> next;
        private Node<T> prev;
        Node(T value) {
            this.value = value;
        }

    }
   public void add(T value) {

        Node<T> node = new Node<>(value);
        ++length;
        // если список пуст
        if (head == null) {
            head = node;
            tail = node;
            return;
        }

        node.prev = tail;
        node.prev.next = node;
        tail = node;

//        tail.next = node;
//        node.prev = tail;
//        tail = node;

    }

    public T get(int index) {
        if (index < 0 || index >= length) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        Node<T> temp=head;
        int indexCount=0;
        while (indexCount!=index){
            temp=temp.next;
            ++indexCount;
        }
        return temp.value;
    }

   public void add(int index, T value) {
        if (index < 0 || index >= length) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        ++length;
        int i = 0;
        Node<T> newNode = new Node<>(value);
        Node<T> temp = head;
        while (i != index) {
            temp = temp.next;
            i++;
        }
        if (temp.prev != null) {
            temp.prev.next = newNode;
            newNode.prev = temp.prev;
        } else {
            head = newNode;
            newNode.prev = null;
        }

        temp.prev = newNode;
        newNode.next = temp;
    }

    // удаляем ноду и возвращаем значение удаленной ноды
    public T remove(int index) {
        if (index < 0 || index >= length) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        --length;

        int indexCount = 0;
        // указывает на найденную ноду
        Node<T> temp = head;

        // ищем ноду
        while (indexCount != index) {
            temp = temp.next;
            ++indexCount;
        }

        if (length == 0) {
            head = null;
            tail = null;
            return temp.value;
        }

        if (temp.prev != null)
            temp.prev.next = temp.next;
        else {
            head = temp.next;
            temp.next.prev = null;
        }
        if (temp.next != null)
            temp.next.prev = temp.prev;
        else {
            tail = temp.prev;
            temp.prev.next = null;
        }
        return temp.value;
    }
    public void printForward() {
        System.out.print("forward: ");
        Node<T> next = head;
        while (next != null) {
            System.out.print(next.value + " ");
            next = next.next;
        }
        System.out.println();
    }

   public void printBackward() {
        System.out.print("backward: ");
        Node<T> prev = tail;
        while (prev != null) {
            System.out.print(prev.value + " ");
            prev = prev.prev;
        }
        System.out.println();
    }

    public void print(){
        printForward();
        printBackward();
    }

    public int getLength(){
        return length;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> currentNode =head;
            @Override
            public boolean hasNext() {
                return currentNode !=null;
            }

            @Override
            public T next() {
                Node<T> temp= currentNode;
                currentNode = currentNode.next;
                return temp.value;
            }
        };
    }
}



