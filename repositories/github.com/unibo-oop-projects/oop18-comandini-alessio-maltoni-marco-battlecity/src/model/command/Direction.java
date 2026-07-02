package model.command;

public enum Direction {
    UP(0, -0.3), DOWN(0, 0.3), LEFT(-0.3, 0), RIGHT(0.3, 0);

    private double x;
    private double y;

    Direction(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }
}
