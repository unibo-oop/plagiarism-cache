package oop.lit.model.elements;

import java.util.Optional;

import oop.lit.util.Vector2D;
import oop.lit.util.Vector2DImpl;

/**
 * A partial implementation of a BoardElement.
 * Complete implementations need to implement getActions, getMainAction, getImage, canMove, canScale and canRotate methods.
 */
public abstract class AbstractBoardElement extends AbstractGameElement implements BoardElement {
    /**
     * 
     */
    private static final long serialVersionUID = -6639132070988220860L;
    private static final Vector2D DEFAULT_POSITION = new Vector2DImpl(0, 0);
    private static final double DEFAULT_SCALE = 1;
    private static final double DEFAULT_ROTATION = 0;

    private Vector2D position;
    private double currentScale;
    private double rotation;

    /**
     * @param name
     *      the name of this element.
     * @param initialPosition
     *      the initial position of this board element. If the optional is empty a default value will be used.
     * @param initialScale
     *      the initial scale of this board element. If the optional is empty a default value will be used.
     * @param initialRotation
     *      the initial rotation of this board element. If the optional is empty a default value will be used.
     */
    protected AbstractBoardElement(final Optional<String> name, final Optional<Vector2D> initialPosition,
            final Optional<Double> initialScale, final Optional<Double> initialRotation) {
        super(name);
        this.position = initialPosition.orElse(DEFAULT_POSITION);
        this.currentScale = initialScale.orElse(DEFAULT_SCALE);
        this.rotation = initialRotation.orElse(DEFAULT_ROTATION);
    }

    @Override
    public Vector2D getPosition() {
        return this.position;
    }

    @Override
    public double getScale() {
        return this.currentScale;
    }

    @Override
    public double getRotation() {
        return this.rotation;
    }

    @Override
    public void move(final Vector2D delta) {
        this.position = this.position.add(delta);
        this.notifyObservers();
    }

    @Override
    public void scale(final double scalar) {
        if (scalar <= 0) {
            throw new IllegalArgumentException("Scalar must be more than 0");
        }
        this.currentScale *= scalar;
        this.notifyObservers();
    }

    @Override
    public void clampScale(final double minScale, final double maxScale) {
        if (minScale <= 0) {
            throw new IllegalArgumentException("minScale must be more than 0");
        }
        if (maxScale < minScale) {
            throw new IllegalArgumentException("maxScale can't be less than minScale");
        }
        if (this.currentScale < minScale) {
            this.currentScale = minScale;
        } else if (this.currentScale > maxScale) {
            this.currentScale = maxScale;
        }
        this.notifyObservers();
    }

    @Override
    public void rotate(final double angle) {
        this.rotation += angle;
        this.rotation %= 360;
        if (this.rotation < 0) {
            this.rotation += 360;
        }
        this.notifyObservers();
    }
}
