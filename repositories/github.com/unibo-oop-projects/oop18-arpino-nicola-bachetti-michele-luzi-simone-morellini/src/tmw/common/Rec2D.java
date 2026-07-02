package tmw.common;

/**
 * This class represents a generic 2D rectangle, is used for the boundary of the
 * objects in the game.
 */
public class Rec2D {

    private final P2d upperLeftPoint;
    private final P2d lowerRightPoint;
    private final P2d upperRightPoint;
    private final P2d lowerLeftPoint;
    private final Dim2D dimension;

    /**
     * Construct a new rectangle.
     * 
     * @param upperLeftPoint  - the upper left point of the rectangle
     * @param lowerRightPoint - the lower right point of the rectangle
     */
    public Rec2D(final P2d upperLeftPoint, final P2d lowerRightPoint) {
        this.upperLeftPoint = upperLeftPoint;
        this.lowerRightPoint = lowerRightPoint;
        this.dimension = new Dim2D(Math.abs(this.lowerRightPoint.getX() - this.upperLeftPoint.getX()),
                Math.abs(this.lowerRightPoint.getY() - this.upperLeftPoint.getY()));
        this.upperRightPoint = new P2d(this.lowerRightPoint.getX(), this.upperLeftPoint.getY());
        this.lowerLeftPoint = new P2d(this.upperLeftPoint.getX(), this.lowerRightPoint.getY());
    }

    /**
     * Construct a new rectangle.
     * 
     * @param upperLeftPoint - the upper left point of the rectangle
     * @param width          - the width of the rectangle
     * @param height         - the height of the rectangle
     */
    public Rec2D(P2d upperLeftPoint, double width, double height) {
        this(upperLeftPoint, new P2d(upperLeftPoint.getX() + width, upperLeftPoint.getY() + height));
    }

    /**
     * Return the x coordinate of the upper left point.
     * 
     * @return the x coordinate of the upper left point
     */
    public double getMinX() {
        return this.upperLeftPoint.getX();
    }

    /**
     * Return the y coordinate of the upper left point.
     * 
     * @return the y coordinate of the upper left point
     */
    public double getMinY() {
        return this.upperLeftPoint.getY();
    }

    /**
     * Return the x coordinate of the lower right point.
     * 
     * @return the x coordinate of the lower right point
     */
    public double getMaxX() {
        return this.lowerRightPoint.getX();
    }

    /**
     * Return the y coordinate of the lower right point.
     * 
     * @return the y coordinate of the lower right point
     */
    public double getMaxY() {
        return this.lowerRightPoint.getY();
    }

    /**
     * Getter for the upper left point of the rectangle.
     * 
     * @return the upper left point of the rectangle
     */
    public P2d getUpperLeftPoint() {
        return upperLeftPoint;
    }

    /**
     * Getter for the lower right point of the rectangle.
     * 
     * @return the lower right point of the rectangle
     */
    public P2d getLowerRightPoint() {
        return lowerRightPoint;
    }

    /**
     * Getter for the upper right point of the rectangle.
     * 
     * @return the upper right point of the rectangle
     */
    public P2d getUpperRightPoint() {
        return upperRightPoint;
    }

    /**
     * Getter for the lower left point of the rectangle.
     * 
     * @return the lower left point of the rectangle
     */
    public P2d getLowerLeftPoint() {
        return lowerLeftPoint;
    }

    /**
     * Return the width of this rectangle.
     * 
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.dimension.getWidth();
    }

    /**
     * Return the height of this rectangle.
     * 
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.dimension.getHeight();
    }

    /**
     * This method is used to check if a pint is inside the rectangle.
     * 
     * @param point - the point to check
     * @return true if point is inside the rectangle, false otherwise
     */
    public boolean isPointIn(final P2d point) {
        return (point.getX() >= upperLeftPoint.getX() && point.getX() <= upperRightPoint.getX()
                && point.getY() >= upperLeftPoint.getY() && point.getY() <= lowerLeftPoint.getY());
    }

    /**
     * This method is used to check if a rectangle collides with this one.
     * 
     * @param rectangle - the rectangle to check
     * @return true if the rectangle is colliding with this one, false otherwise
     */
    public boolean intersects(final Rec2D rectangle) {

        return (this.isPointIn(rectangle.getUpperLeftPoint()) || this.isPointIn(rectangle.getUpperRightPoint())
                || this.isPointIn(rectangle.getLowerRightPoint()) || this.isPointIn(rectangle.getLowerLeftPoint()));
    }

    /**
     * Hash code for the rectangle. 
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(getWidth());
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((lowerLeftPoint == null) ? 0 : lowerLeftPoint.hashCode());
        result = prime * result + ((lowerRightPoint == null) ? 0 : lowerRightPoint.hashCode());
        result = prime * result + ((upperLeftPoint == null) ? 0 : upperLeftPoint.hashCode());
        result = prime * result + ((upperRightPoint == null) ? 0 : upperRightPoint.hashCode());
        temp = Double.doubleToLongBits(getHeight());
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Check if an object and this rectangle are the same.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rec2D other = (Rec2D) obj;
        return (this.upperLeftPoint.equals(other.upperLeftPoint) && this.upperRightPoint.equals(other.upperRightPoint)
                && this.lowerLeftPoint.equals(other.lowerLeftPoint)
                && this.lowerRightPoint.equals(other.lowerRightPoint));
        // return (this.getWidth() == other.getWidth() && this.getHeight() ==
        // other.getHeight());
    }

}
