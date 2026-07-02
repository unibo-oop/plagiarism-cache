package it.unibo.bmbman.model.utilities;
/**
 * Class to manage the dimension of entities.
 */
public class Dimension {
    private int height;
    private int width;

    /**
     * Create a new dimension.
     * @param height of the entity
     * @param width of the entity
     */
    public Dimension(final int height, final int width) {
        super();
        this.height = height;
        this.width = width;
    }
    /**
     * Method to get the height.
     * @return height
     */
    public int getHeight() {
        return height;
    }
    /**
     * Method to set the height.
     * @param height the new height
     */
    public void setHeight(final int height) {
        this.height = height;
    }
    /**
     * Method to get the width.
     * @return width
     */
    public int getWidth() {
        return width;
    }
    /**
     * Method to set the width.
     * @param width the new width
     */
    public void setWidth(final int width) {
        this.width = width;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Dimension [height=" + this.height + ", width=" + this.width + "]";
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + height;
        result = prime * result + width;
        return result;
    }
    /**
     * {@inheritDoc}
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
        final Dimension other = (Dimension) obj;
        if (height != other.height) {
            return false;
        }
        if (width != other.width) {
            return false;
        }
        return true;
    }

}
