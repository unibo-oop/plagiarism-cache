package models;

/**
 * Point2D is a specific type of class that extends Pair class. This is used
 * to better facilitate interactions between Pairs of type "Integer,Integer" and greatly
 * reduces line of codes necessary in other files
 *
 */
public class Point2D extends Pair<Integer, Integer> {

    /**
     * This is the constructor of the class.
     * 
     * @param x The x element of int type
     * @param y The y element of int type
     */
    public Point2D(final int x, final int y) {
        super(x, y);
    }

    /**
     * This is what we tecnically call a static constructor (since they don't tecnically exist in Java).
     * It creates a new Point2D by summing x and y of two other Point2Ds
     * 
     * @param point1 The first Point2D
     * @param point2 The second Point2D
     * @return A new Point2D that has x as the sum of the x values from input points and y as the sum
     * of the y values from input points
     */
    public static Point2D sum(final Point2D point1, final Point2D point2) {
        return new Point2D(point1.getX() + point2.getX(), point1.getY() + point2.getY());
    }

}
