package it.unibo.oop.utilities;

public enum Direction {

    UP(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    DOWN(0, 1),
    NONE(0, 0),
    RIGHTUP(0.85, -0.85),
    LEFTUP(-0.85, -0.85),
    RIGHTDOWN(0.85, 0.85),
    LEFTDOWN(-0.85, 0.85);

    // private static final double TRIGONOMETRIC_CONSTANT = sin(PI/4);

    private double x;
    private double y;

    private Direction(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 getVector2() {
        return new Vector2(this.x, this.y);
    }
}