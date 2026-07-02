package entity;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

/**
 * Class that represent a integer version of CollisionBox.
 */
public class CollisionBoxInt implements CollisionBox<Integer> {

    /**
     * represent the bounds of the box.
     */
    private Bounds box;

    /**
     * Generate a new CollisionBox from another one.
     * @param collisionbox 
     */
    public CollisionBoxInt(final CollisionBox<Integer> collisionbox) {
        this(collisionbox.getX(), collisionbox.getY(), collisionbox.getWidth(), collisionbox.getHeight());
    }

    /**
     * Generate a new CollisionBox from another one and coordinates.
     * @param xvalue
     * @param yvalue
     * @param collisionbox
     */
    public CollisionBoxInt(final int xvalue, final int yvalue, final CollisionBox<Integer> collisionbox) {
        this(xvalue, yvalue, collisionbox.getWidth(), collisionbox.getHeight());
    }

    /**
     * Generate a new CollisionBox from Dimensions and coordinates.
     * @param xvalue
     * @param yvalue
     * @param width
     * @param height
     */
    public CollisionBoxInt(final int xvalue, final int yvalue, final int width, final int height) {
        box = new BoundingBox(xvalue, yvalue, width, height);
    }

    @Override
    public final Integer getWidth() {
        return (int) box.getWidth();
    }

    @Override
    public final Integer getHeight() {
        return (int) box.getHeight();
    }

    @Override
    public final Integer getX() {
        return (int) box.getMinX();
    }

    @Override
    public final Integer getY() {
        return (int) box.getMinY();
    }

    @Override
    public final boolean checkCollision(final CollisionBox<Integer> box) {
        return this.box.intersects(new CollisionBoxInt(box).getBounds());
    }

    /**
     * Return A Bounds object that represent the bounds of the box.
     * @return The Bounds
     */
    public final Bounds getBounds() {
        return box;
    }
    
    @Override
    public String toString() {
    	return String.format("HB: |X:%d\t|Y:%d\t|  %d- %d|", getX(), getY(), getWidth(), getHeight());
    }
    
}
