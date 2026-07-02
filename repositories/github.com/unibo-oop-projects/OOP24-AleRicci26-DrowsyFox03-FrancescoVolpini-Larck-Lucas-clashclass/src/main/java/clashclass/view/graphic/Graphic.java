package clashclass.view.graphic;

import clashclass.commons.Rect2D;
import clashclass.ecs.GameObject;
import clashclass.view.graphic.components.AbstractGraphicComponent;

import java.util.Set;

/**
 * Interface to draw GameObjects.
 */
public interface Graphic {
    /**
     * Clears the screen.
     */
    void clearRect();

    /**
     * Renders all the graphics components in the screen.
     *
     * @param graphicComponents the set of graphics components
     */
    void render(Set<AbstractGraphicComponent> graphicComponents);

    /**
     * Method to draw a sprite.
     *
     * @param gameObject the GameObject to draw
     * @param spriteName the key to identify the sprite
     */
    void drawSprites(GameObject gameObject, String spriteName);

    /**
     * Method to draw a rectangle.
     *
     * @param gameObject the GameObject to draw
     * @param colorEx the color
     * @param rect the rect
     */
    void drawRectangle(GameObject gameObject, String colorEx, Rect2D rect);
}
