package ru.igojig.task_3;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Figure circle = new Circle(12, 34, 12, Color.BLACK, 2);
        Figure triangle = new Triangle(12, 4, 34, 17, 3, 6, Color.PINK, 5);
        Figure square = new Square(6, 8, 9, Color.BLUE, -1);

        Figure[] figures = {circle, triangle, square};

        for (Figure figure : figures) {
            System.out.printf("Square of %s is %f%n", figure.name, figure.figureSquare());
        }
        System.out.println();

        // отсортируем по zOrder
        Arrays.sort(figures, Comparator.comparingInt(o -> o.zOrder));

        for (Figure figure : figures) {
            System.out.printf("ZOrder of figure %s is %d%n", figure.name, figure.zOrder);
        }

    }
}
