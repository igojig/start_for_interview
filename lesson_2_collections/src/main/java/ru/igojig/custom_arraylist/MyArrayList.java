package ru.igojig.custom_arraylist;

import java.util.Iterator;

public class MyArrayList<T> implements Iterable<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final float LOAD_FACTOR = 1.5f;
    private Object[] array;

    // количество элементов в массиве
    private int length = 0;

    public MyArrayList() {
        array = new Object[INITIAL_CAPACITY];
    }

    public void add(T element) {
        if (length == array.length) {
            resize();
        }
        array[length++] = element;
    }

    public void add(int index, T element) {
        if (index >= length || index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        // проверяем capacity хранилища
        if (length == array.length) {
            resize();
        }
        Object[] temp = new Object[length - index];
        System.arraycopy(array, index, temp, 0, length - index);
        array[index] = element;
        System.arraycopy(temp, 0, array, index + 1, length - index);
        length++;
    }

    private void resize() {
        Object[] newArray = new Object[(int) (length * LOAD_FACTOR)];
        System.arraycopy(array, 0, newArray, 0, length);
        array = newArray;
    }

    // получение элемента только по индексу
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= length || index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return (T) array[index];
    }

    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index >= length || index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        // если это последний элемент
        if (index == length - 1) {
            T temp = (T) array[index];
            array[index] = null;
            --length;
            return temp;
        }

        // если в "середине"
        T temp = (T) array[index];
        Object[] newArray = new Object[length - 1];
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index + 1, newArray, index, length - index - 1);
        array = newArray;
        --length;
        return temp;
    }

    public void print() {
        System.out.println("Length=" + length);
        if (length == 0) {
            System.out.println("[]");
            System.out.println();
            return;
        }

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < length; i++) {
            sb.append(array[i]).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        System.out.println(sb);
        System.out.println();
    }

    public int getLength() {
        return length;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = -1;

            @Override
            public boolean hasNext() {
                return currentIndex != length - 1;
            }

            @Override
            public T next() {
                return (T) array[++currentIndex];
            }
        };
    }
}
