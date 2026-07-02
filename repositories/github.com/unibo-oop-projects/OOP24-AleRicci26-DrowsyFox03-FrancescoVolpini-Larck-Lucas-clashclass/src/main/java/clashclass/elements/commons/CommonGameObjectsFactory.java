package clashclass.elements.commons;

import clashclass.commons.VectorInt2D;
import clashclass.ecs.GameObject;

/**
 * Represents a factory for common game objects.
 */
public interface CommonGameObjectsFactory {
    /**
     * Creates a village ground tile.
     *
     * @param position the position of the ground tile
     *
     * @return the ground tile game object
     */
    GameObject createVillageGroundTile(VectorInt2D position);

    /**
     * Creates a UI element.
     *
     * @return the UI element game object
     */
    GameObject createUIElement();
}
