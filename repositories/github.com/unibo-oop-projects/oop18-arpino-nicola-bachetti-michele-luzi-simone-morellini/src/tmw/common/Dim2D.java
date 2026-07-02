package tmw.common;

/**
 * This class represents a generic 2D dimension.
 */
public class Dim2D {

    private double width;
    private double height;

    /**
     * Construct a new dimension.
     * 
     * @param width  - the width of the dimension
     * @param height - the height of the dimension
     */
    public Dim2D(final double width, final double height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Getter for the width parameter.
     * 
     * @return the width of this dimension
     */
    public double getWidth() {
        return width;
    }

    /**
     * Setter for the width parameter.
     * 
     * @param width the new width for the dimension
     */
    public void setWidth(final double width) {
        this.width = width;
    }

    /**
     * Getter for the height parameter.
     * 
     * @return the height of this dimension
     */
    public double getHeight() {
        return height;
    }

    /**
     * Setter for the height parameter.
     * 
     * @param height the new height for the dimension
     */
    public void setHeight(final double height) {
        this.height = height;
    }

    /**
     * Return the hash code of this dimension.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(height);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(width);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Check if this dimension and the Object passed are the same.
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
        final Dim2D other = (Dim2D) obj;
        return this.width == other.getHeight() && this.height == other.getHeight();
    }

}
