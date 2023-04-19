package ru.igojig.task_3;

public class Square extends Figure {

    private final Coordinates coordinates;

    private final int length;

    public Square(int x, int y, int length, Color color, int zOrder) {
        super(color, zOrder, "square");
        coordinates = new Coordinates(x, y);
        this.length = length;
    }

    public Square(int length, Color color, int zOrder) {
        super(color, zOrder, "square");
        coordinates = new Coordinates();
        this.length = length;
    }

    @Override
    public float figureSquare() {
        return length * length;
    }
}
