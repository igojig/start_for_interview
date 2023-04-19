package ru.igojig.task_3;

public class Coordinates {
    private final int x;
    private final int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // вот такие у нас условия, предположим
    public Coordinates() {
        this.x = 10;
        this.y = 20;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
