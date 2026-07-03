package oop.lit.model.simplegame.elements.builders;

import oop.lit.model.simplegame.elements.GroupSBE;
import oop.lit.model.util.images.WrappedImage;
import oop.lit.util.Vector2D;

/**
 * A GroupSBE builder.
 */
public interface GroupSBEBuilder {

    /**
     * Sets the name of the element that will be created;
     * if this is not set the element will have no name.
     * @param name
     *      the name of the element.
     * @return this
     */
    GroupSBEBuilder setName(String name);

    /**
     * Sets the initial position of the element that will be created;
     * if this is not set the element will have a default position.
     * @param position
     *      the initial position of the element.
     * @return this
     */
    GroupSBEBuilder setPosition(Vector2D position);

    /**
     * Sets the initial scale of the element that will be created;
     * if this is not set the element will have a default scale.
     * @param scale
     *      the initial scale of the element.
     * @return this
     */
    GroupSBEBuilder setScale(double scale);

    /**
     * Sets the initial rotation of the element that will be created;
     * if this is not set the element will have a default rotation.
     * @param rotation
     *      the initial rotation of the element.
     * @return this
     */
    GroupSBEBuilder setRotation(Double rotation);

    /**
     * Sets the image of the element that will be created;
     * if this is not set the element will have a default image, chosen by the view.
     * @param image
     *      the image of the element.
     * @return this
     */
    GroupSBEBuilder setImage(WrappedImage image);

    /**
     * Builds the element using the specified parameters.
     * @return
     *      the element.
     * @throws IllegalStateException
     *      if the builder has been already used.
     */
    GroupSBE build();

}