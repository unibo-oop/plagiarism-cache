package it.unibo.isaccoop.graphics;

import it.unibo.isaccoop.model.common.MapElement;

/**
 * GraphicsComponent interface representing the components that need a graphic update.
 * */
public interface GraphicsComponent {
    /**
     * Method to update the graphics of an element.
     *
     * @param obj reference to MapElement
     * @param w reference to Graphics
     */
    void update(MapElement obj, Graphics w);

}
