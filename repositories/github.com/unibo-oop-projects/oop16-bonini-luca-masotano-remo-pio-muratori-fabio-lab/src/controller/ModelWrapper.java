package controller;

import util.ImageLoaderProxy.ImageID;

/**
 * Represents the wrapper of a model's object and defines some utility methods.
 */
public interface ModelWrapper {

    /**
     * Get the X position of the object.
     * 
     * @return the object's X position
     */
    double getX();

    /**
     * Get the Y position of the object.
     * 
     * @return the object's Y position
     */
    double getY();

    /**
     * Get the image ID of the object.
     * 
     * @return the object's image ID.
     */
    ImageID getImg();

    /**
     * Check if the object is a circle.
     * 
     * @return if the object is a circle returns true otherwise false
     */
    boolean isCircle();

    /**
     * Get the height of the object.
     * 
     * @return the height
     */
    double getHeight();

    /**
     * Get the width of the object.
     * 
     * @return the width
     */
    double getWidth();

}
