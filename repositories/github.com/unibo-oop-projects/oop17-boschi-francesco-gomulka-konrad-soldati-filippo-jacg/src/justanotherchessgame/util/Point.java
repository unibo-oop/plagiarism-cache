package justanotherchessgame.util;

public class Point {
    public int x;
    public int y;

    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    //Creates a copy of a given point
    public Point(final Point point) {
        x = point.getX();
        y = point.getY();
    }

    public final int getX() {
        return x; 
    }

    public final void setX(final int x) {
        this.x = x; 
    }

    public final int getY() {
        return y; 
    }

    public final void setY(final int y) {
        this.y = y; 
    }

    public final boolean onBoard() {
        return (x < 8 && x >= 0 && y < 8 && y >= 0);
    }
    //Basic math operators
    public static Point sum(final Point a, final Point b) {
        return new Point(a.getX() + b.getX(), a.getY() + b.getY());
    }
    public static Point subtract(final Point a, final Point b) {
        return new Point(a.getX() - b.getX(), a.getY() - b.getY());
    }
    public static Point mul(final Point a, final int k) {
        return new Point(a.getX() * k, a.getY() * k);
    }

    @Override
    public final boolean equals(final Object obj) {
        return (obj instanceof Point && this.x == ((Point) obj).x && this.y == ((Point) obj).y);
    }

    @Override
    public final int hashCode() {
        return 0;

    }

    @Override
    public final String toString() {
        return "(" + x + ", " + y + ")";
    }
}
