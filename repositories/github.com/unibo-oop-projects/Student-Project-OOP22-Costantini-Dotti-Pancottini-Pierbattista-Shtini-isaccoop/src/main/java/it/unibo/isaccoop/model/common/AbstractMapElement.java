package it.unibo.isaccoop.model.common;

import java.util.Objects;

import it.unibo.isaccoop.graphics.Graphics;
import it.unibo.isaccoop.graphics.GraphicsComponent;
import it.unibo.isaccoop.model.boundingbox.BoundingBox;
import it.unibo.isaccoop.model.boundingbox.CircleBoundingBox;
import it.unibo.isaccoop.model.boundingbox.RectBoundingBox;

/**
 * AbstractMapElement abstract class which implements MapElement interface and it is a container
 * for common elements state and behavior.
 */
public abstract class AbstractMapElement implements MapElement {

    private Point2D coords;
    private BoundingBox box;
    private GraphicsComponent graphicComponent;
    private static final double FIXED_INITIAL_POSITION = 10.0;

    /**
     * ElementRadius enum to describe elements radius.
     */
    public enum ElementsRadius {

        /**
         * Deafult radius.
         */
        DEFAULT(0.00),
        /**
         * Standard enemy radius.
         */
        ENEMY(8.00),
        /**
         * Item radius.
         */
        ITEM(5.00),
        /**
         * Player radius.
         */
        PLAYER(8.00),
        /**
         * Boss radius.
         */
        BOSS(20.00),
        /**
         * Bullet radius.
         */
        BULLET(3.00);

        private Double value;

        /**
         * ElementRadius Constructor.
         * @param value
         * set the value.
         * */
        ElementsRadius(final Double value) {
            this.value = value;
        }

        /**
         * Get radius value.
         * @return the value of enum value.
         * */
        public Double getValue() {
            return this.value;
        }
    }

    /**
     * Constructor for {@link AbstractMapElement}.
     *
     * @param coords initial coords
     * @param elemRadius based on the type of the element
     * @param gr the graphic component for this {@link AbstractMapElement}
     */
    public AbstractMapElement(final Point2D coords, final ElementsRadius elemRadius, final GraphicsComponent gr) {
        this.coords = coords;
        this.box = new CircleBoundingBox(elemRadius.getValue());
        this.graphicComponent = gr;
    }

    /**
     * Constructor for {@link AbstractMapElement} with fixed initial position.
     * @param elemRadius based on the type of the element
     */
    public AbstractMapElement(final ElementsRadius elemRadius) {
        this.coords = new Point2D(0.0, 0.0);
        this.box = new CircleBoundingBox(elemRadius.getValue());
    }

    /**
     * Constructor for {@link AbstractMapElement} with fixed initial position.
     * @param elemRadius based on the type of the element
     * @param gr the {@link GraphicsComponent} for this {@link MapElement}
     * */
    public AbstractMapElement(final ElementsRadius elemRadius, final GraphicsComponent gr) {
        this.coords = new Point2D(FIXED_INITIAL_POSITION, FIXED_INITIAL_POSITION);
        this.box = new CircleBoundingBox(elemRadius.getValue());
        this.graphicComponent = gr;
    }

    /**
     * Constructor for {@link AbstractMapElement} with fixed initial position.
     * @param width
     * @param height
     * @param gr graphics component
     */
    public AbstractMapElement(final int width, final int height, final GraphicsComponent gr) {
        this.coords = new Point2D(0.0, 0.0);
        this.box = new RectBoundingBox(width, height);
        this.graphicComponent = gr;
    }

    /**
     * Get the coordinate of this {@link MapElement}.
     * @return the coordinate of this {@link MapElement}, as a {@link Point2D}
     */
    @Override
    public Point2D getCoords() {
        return new Point2D(this.coords.getX(), this.coords.getY());
    }

    /**
     * Set the coordinate of this {@link MapElement}.
     * @param coords the {@link Point2D} to be set as coordinate of this {@link MapElement}
     */
    @Override
    public void setCoords(final Point2D coords) {
        this.coords = new Point2D(coords.getX(), coords.getY());
    }

    /**
     * Set element graphics component.
     * @param gr
     */
    protected void setGraphicsComponents(final GraphicsComponent gr) {
        this.graphicComponent = gr;
    }

    /**
     * Get the collision box of this {@link MapElement}.
     * @return collision box
     */
    @Override
    public BoundingBox getBox() {
        return this.box;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGraphics(final Graphics g) {
        graphicComponent.update(this, g);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(coords);
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
        final AbstractMapElement other = (AbstractMapElement) obj;
        return Double.compare(coords.getX(), other.coords.getX()) == 0
                && Double.compare(coords.getY(), other.coords.getY()) == 0;
    }
}
