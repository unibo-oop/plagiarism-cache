package it.unibo.isaccoop.graphics;

import it.unibo.isaccoop.model.common.MapElement;

/**
 * Graphics interface representing the concept of drawing components.
 * */
public interface Graphics {

    /**
     * Method to draw a MapElement as image.
     *
     * @param element map element
     * @param imageName element image name
     * */
    void drawElementAsImage(MapElement element, String imageName);

}
