package model.environment;

/**
 * An immutable point in a two-dimensional space composed of pairs of integers.
 */
public interface PointInterface {

    /**
     * @return the X coordinate
     */
    int getX();

    /**
     * Changes the current abscissa, incrementing or decrementing its value
     * depending on the argument passed.
     * 
     * @param x the value that will be added to the current abscissa
     * @return a new immutable Point2D with modified abscissa
     */
    Point translateX(int x);

    /**
     * @return the Y coordinate
     */
    int getY();

    /**
     * Changes the current ordinate, incrementing or decrementing its value
     * depending on the argument passed.
     * 
     * @param y the value that will be added to the current ordinate
     * @return a new immutable Point2D with modified ordinate
     */
    Point translateY(int y);

    /**
     * @return the point.
     */
    Point getPosition();

    /**
     * Changes the current coordinate, incrementing or decrementing its value
     * depending on the arguments passed.
     * 
     * @param x the value that will be added to the current abscissa
     * @param y the value that will be added to the current ordinate
     * @return a new immutable Point2D
     */
    Point translatePosition(int x, int y);
}
