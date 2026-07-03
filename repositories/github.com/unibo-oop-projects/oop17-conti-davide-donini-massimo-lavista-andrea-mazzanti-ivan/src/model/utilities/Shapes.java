package model.utilities;

import java.util.Optional;

import javafx.scene.shape.Shape;
import model.entities.Entity;
import model.entities.properties.Position;
import model.entities.properties.PositionImpl;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * Utilities methods for shapes.
 */
public final class Shapes {

    private Shapes() {
    }

    /**
     * 
     * @param shape
     *            shape whose we want to know position
     * @return the position of the shape
     */
    public static Position getPositionbyShape(final Shape shape) {
        Optional<Position> position = Optional.empty();
        if (shape instanceof Rectangle) {
            final Rectangle rectange = (Rectangle) shape;
            position = Optional.of(new PositionImpl(rectange.getX(), rectange.getY()));
        } else if (shape instanceof Circle) {
            final Circle circle = (Circle) shape;
            position = Optional.of(new PositionImpl(circle.getCenterX(), circle.getCenterY()));
        }
        return position.orElseThrow(() -> new IllegalStateException());
    }

    /**
     * 
     * @param shape
     *          entity's shape.
     * @param position
     *          the new entity's position.
     */
    public static void setShapePosition(final Shape shape, final Position position) {
        if (shape instanceof Rectangle) {
            final Rectangle rectangle = (Rectangle) shape;
            rectangle.setX(position.getX());
            rectangle.setY(position.getY());
        } else if (shape instanceof Circle) {
            final Circle circle = (Circle) shape;
            circle.setCenterX(position.getX());
            circle.setCenterY(position.getY());
        }
    }

    /**
     * 
     * @param shape
     *            shape
     * @return copy of the shape received
     */
    public static Shape getShapeCopy(final Shape shape) {
        final Shape copy;
        if (shape instanceof Rectangle) {
            final Rectangle r = (Rectangle) shape;
            copy = new Rectangle(r.getX(), r.getY(), r.getWidth(), r.getHeight());
        } else {
            final Circle c = (Circle) shape;
            copy = new Circle(c.getCenterX(), c.getCenterY(), c.getRadius());
        }
        return copy;
    }

    /**
     * Provides the center of an entity according to its shape.
     * 
     * @param entity
     *            a game entity.
     * @return the position of the center of the entity.
     */
    public static Position getEntityCenter(final Entity entity) {
        if (entity.getShape() instanceof Rectangle) {
            final Rectangle rectangle = ((Rectangle) entity.getShape());

            return new PositionImpl(entity.getPosition().getX() + rectangle.getWidth() / 2,
                    entity.getPosition().getY() + rectangle.getHeight() / 2);
        } else {
            return new PositionImpl(entity.getPosition().getX(), entity.getPosition().getY());
        }
    }

}
