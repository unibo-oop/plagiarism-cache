package oop.lit.model.simplegame.elements.builders;

import java.util.Objects;
import java.util.Optional;

import oop.lit.model.util.images.WrappedImage;
import oop.lit.util.Vector2D;

/**
 * Partial builder for a BoardElement with an image, used just to hold information.
 */
public class SimpleBuilderCommonData {
    private Optional<String> name;
    private Optional<Vector2D> position;
    private Optional<Double> scale;
    private Optional<Double> rotation;
    private Optional<WrappedImage> image;

    /**
     *
     */
    protected SimpleBuilderCommonData() {
        this.name = Optional.empty();
        this.position = Optional.empty();
        this.scale = Optional.empty();
        this.rotation = Optional.empty();
        this.image = Optional.empty();
        //costruttore protected perché non voglio instanziare il SimpleBuilder di per sè e non ha metodi abstract.
    }

    /**
     * Sets the name of the element that will be created;
     * if this is not set the element will have no name.
     * @param name
     *      the name of the element.
     */
    public void setName(final String name) {
        Objects.requireNonNull(name);
        this.name = Optional.of(name);
    }
    /**
     * Sets the initial position of the element that will be created;
     * if this is not set the element will have a default position.
     * @param position
     *      the initial position of the element.
     */
    public void setPosition(final Vector2D position) {
        Objects.requireNonNull(position);
        this.position = Optional.of(position);
    }
    /**
     * Sets the initial scale of the element that will be created;
     * if this is not set the element will have a default scale.
     * @param scale
     *      the initial scale of the element.
     */
    public void setScale(final double scale) {
        Objects.requireNonNull(scale);
        this.scale = Optional.of(scale);
    }
    /**
     * Sets the initial rotation of the element that will be created;
     * if this is not set the element will have a default rotation.
     * @param rotation
     *      the initial rotation of the element.
     */
    public void setRotation(final Double rotation) {
        Objects.requireNonNull(rotation);
        this.rotation = Optional.of(rotation);
    }
    /**
     * Sets the image of the element that will be created;
     * if this is not set the element will have a default image, chosen by the view.
     * @param image
     *      the image of the element.
     */
    public void setImage(final WrappedImage image) {
        Objects.requireNonNull(image);
        this.image = Optional.of(image);
    }

    /**
     * @return the name
     */
    public Optional<String> getName() {
        return name;
    }
    /**
     * @return the position
     */
    public Optional<Vector2D> getPosition() {
        return position;
    }
    /**
     * @return the size
     */
    public Optional<Double> getScale() {
        return this.scale;
    }
    /**
     * @return the rotation
     */
    public Optional<Double> getRotation() {
        return rotation;
    }
    /**
     * @return the image
     */
    public Optional<WrappedImage> getImage() {
        return image;
    }
}
