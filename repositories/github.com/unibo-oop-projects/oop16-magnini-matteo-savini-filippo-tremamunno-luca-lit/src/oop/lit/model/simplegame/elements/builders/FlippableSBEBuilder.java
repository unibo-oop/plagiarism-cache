package oop.lit.model.simplegame.elements.builders;

import oop.lit.model.simplegame.elements.FlippableSBE;
import oop.lit.model.util.images.WrappedImage;
import oop.lit.util.Vector2D;

/**
 * A FlippableSBE builder.
 */
public interface FlippableSBEBuilder {

    /**
     * Sets the name of the element that will be created;
     * if this is not set the element will have no name.
     * @param name
     *      the name of the element.
     * @return this
     */
    FlippableSBEBuilder setName(String name);

    /**
     * Sets the initial position of the element that will be created;
     * if this is not set the element will have a default position.
     * @param position
     *      the initial position of the element.
     * @return this
     */
    FlippableSBEBuilder setPosition(Vector2D position);

    /**
     * Sets the initial scale of the element that will be created;
     * if this is not set the element will have a default scale.
     * @param scale
     *      the initial scale of the element.
     * @return this
     */
    FlippableSBEBuilder setSize(double scale);

    /**
     * Sets the initial rotation of the element that will be created;
     * if this is not set the element will have a default rotation.
     * @param rotation
     *      the initial rotation of the element.
     * @return this
     */
    FlippableSBEBuilder setRotation(Double rotation);

    /**
     * Sets the image of the front face of the element that will be created;
     * if this is not set the element will have a default image, chosen by the view.
     * @param image
     *      the image of the element's front face.
     * @return this
     */
    FlippableSBEBuilder setFrontImage(WrappedImage image);

    /**
     * Sets the image of the back face of the element that will be created;
     * if this is not set the element will have a default image, chosen by the view.
     * @param image
     *      the image of the element's back face.
     * @return this
     */
    FlippableSBEBuilder setBackImage(WrappedImage image);

    /**
     * Builds the element using the specified parameters.
     * @return
     *      the element.
     * @throws IllegalStateException
     *      if the builder has been already used.
     */
    FlippableSBE build();

}