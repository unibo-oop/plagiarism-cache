package powpaw.player.model.impl;

import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import powpaw.player.model.api.Hitbox;

/**
 * Implementation of the Hitbox interface representing the hitbox of a player.
 * 
 * @author Alessia Carfì, Giacomo Grassetti
 */
public final class PlayerHitboxImpl implements Hitbox {

    private final double radius;
    private double offsetX;
    private double offsetY;
    private final double offsetFeet;
    private final Circle hitbox;
    private final Circle feetBox;
    private final Rectangle armHitbox;

    /**
     * Creates a new PlayerHitboxImpl object with the position, width and
     * height of player.
     * 
     * @param playerPosition the position of the player
     * @param width          the width of the player
     * @param height         the height of the player
     */
    public PlayerHitboxImpl(final Point2D playerPosition, final double width, final double height) {
        this.radius = width / 2;
        final double feetRadius = this.radius / 3;
        this.offsetX = width / 2;
        this.offsetY = height / 2;
        this.offsetFeet = height - feetRadius;
        final double x = playerPosition.getX() + this.offsetX;
        final double y = playerPosition.getY() + this.offsetY;
        final double yFeet = playerPosition.getY() + this.offsetFeet;
        final double yArm = playerPosition.getY() + this.offsetY;
        this.hitbox = new Circle(x, y, this.radius);
        this.armHitbox = new Rectangle(x, yArm, this.offsetX, this.offsetY / 2);
        this.feetBox = new Circle(x, yFeet, feetRadius);
    }

    @Override
    public double getRadius() {
        return this.radius;
    }

    @Override
    public Point2D getCenter() {
        return new Point2D(this.hitbox.getCenterX(), this.hitbox.getCenterY());
    }

    @Override
    public Shape getShape() {
        return this.hitbox;
    }

    @Override
    public Shape getFeetShape() {
        return this.feetBox;
    }

    @Override
    public Rectangle getArmShape() {
        return this.armHitbox;
    }

    @Override
    public void setOffsetX(final double width) {
        this.offsetX = width / 2;
    }

    @Override
    public double getOffsetX() {
        return this.offsetX;
    }

    @Override
    public double getOffsetY() {
        return this.offsetY;
    }

    @Override
    public void setOffsetY(final double height) {
        this.offsetY = height / 2;
    }

    @Override
    public void updateCenter(final Point2D position) {
        this.hitbox.setCenterX(position.getX() + offsetX);
        this.hitbox.setCenterY(position.getY() + offsetY);

        this.feetBox.setCenterX(position.getX() + offsetX);
        this.feetBox.setCenterY(position.getY() + offsetFeet);

        this.armHitbox.setX(position.getX() + offsetX);
        this.armHitbox.setY(position.getY() + offsetY);
    }

    @Override
    public boolean checkCollision(final Shape otherHitbox) {
        return this.armHitbox.getBoundsInParent().intersects(otherHitbox.getBoundsInParent());
    }
}
