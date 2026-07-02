package clashclass.elements;

import clashclass.commons.Vector2D;
import clashclass.ecs.Component;

/**
 * Represents a Factory for the creation of components.
 */
public interface ComponentFactory {
    /**
     * Creates a Transform2D component.
     *
     * @param position the position of the transform
     *
     * @return the created component
     */
    Component createTransform2D(Vector2D position);

    /**
     * Creates a HealthComponent.
     *
     * @param maxValue the max value of the health
     *
     * @return the created component
     */
    Component createHealth(int maxValue);

    /**
     * Creates a GraphicComponent with specified dimensions.
     *
     * @return the created component
     */
    Component createGraphic();

    /**
     * Creates a ProgressBar component.
     *
     * @param colorEx the color of the progress bar
     *
     * @return the created component
     */
    Component createProgressBar(String colorEx);
}
