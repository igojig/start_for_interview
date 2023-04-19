package ru.igojig.task_3;

public abstract class Figure {
    protected final Color color;
    protected final int zOrder;

    protected final String name;

    protected Figure(Color color, int zOrder, String name) {
        this.color = color;
        this.zOrder = zOrder;
        this.name = name;
    }

    public abstract float figureSquare();
}
