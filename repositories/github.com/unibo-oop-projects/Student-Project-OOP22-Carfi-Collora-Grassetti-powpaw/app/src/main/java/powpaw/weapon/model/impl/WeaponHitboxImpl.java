package powpaw.weapon.model.impl;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import powpaw.weapon.model.api.WeaponHitbox;

/**
 * Class that implements the WeaponHitbox interface and provides methods for
 * creating
 * and updating a rectangular hitbox for a weapon in a game, as well as checking
 * for collisions with
 * other hitboxes.
 * 
 * @author Giacomo Grassetti
 */
public final class WeaponHitboxImpl implements WeaponHitbox {

    private final Rectangle shape;

    /**
     * Constructor for the WeaponHitboxImpl that creates a new Rectangle object with
     * the given position, width and height.
     * 
     * @param pos
     * @param width
     * @param height
     */
    public WeaponHitboxImpl(final Point2D pos, final double width, final double height) {
        this.shape = new Rectangle(pos.getX(), pos.getY(), width, height);
    }

    @Override
    public Shape getShape() {
        return this.shape;
    }

    @Override
    public void updateCenter(final Point2D position) {
        this.shape.setX(position.getX());
        this.shape.setY(position.getY());
    }

    @Override
    public boolean checkCollision(final Shape otherHitbox) {
        return this.shape.getBoundsInParent().intersects(otherHitbox.getBoundsInParent());
    }

}
