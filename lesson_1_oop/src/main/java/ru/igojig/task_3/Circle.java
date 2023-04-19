package ru.igojig.task_3;

public class Circle extends Figure {

    private final Coordinates coordinates;
    private final int radius;

    public Circle(int x, int y, int radius, Color color, int zOrder) {
        super(color, zOrder, "circle");
        coordinates = new Coordinates(x, y);
        this.radius = radius;
    }

    public Circle(int radius, Color color, int zOrder) {
        super(color, zOrder, "circle");
        coordinates = new Coordinates();
        this.radius = radius;
    }

    @Override
    public float figureSquare() {
        return (float) ((radius * radius) * Math.PI);
    }
}
