package ru.igojig.task_3;

public class Triangle extends Figure {

    private final Coordinates[] coordinates = new Coordinates[3];

    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3, Color color, int zOrder) {
        super(color, zOrder, "triangle");
        coordinates[0] = new Coordinates(x1, y1);
        coordinates[1] = new Coordinates(x2, y2);
        coordinates[2] = new Coordinates(x3, y3);
    }

    public Triangle(Color color, int zOrder) {
        super(color, zOrder, "triangle");
        coordinates[0] = new Coordinates();
        coordinates[1] = new Coordinates(20, 20);
        coordinates[2] = new Coordinates(30, 30);
    }

    // насчет правильности формулы не уверен
    @Override
    public float figureSquare() {
        float part = (coordinates[1].getX() - coordinates[0].getX()) * (coordinates[2].getY() - coordinates[0].getY()) -
                (coordinates[2].getX() - coordinates[0].getX()) * (coordinates[1].getY() - coordinates[0].getY());
        return Math.abs(part) / 2;
    }

}
