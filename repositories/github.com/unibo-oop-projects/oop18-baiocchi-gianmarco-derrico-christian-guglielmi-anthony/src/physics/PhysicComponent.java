package physics;

import java.awt.geom.Rectangle2D;

import model.GameObject;

/**
 * This interface declares the methods of the physic of all game objects.
 */
public interface PhysicComponent {

    /**
     * Get the entity shape in game.
     * @return the entity shape
     */
    Rectangle2D getBounds();

    /**
     * Object getter.
     * @return the entire object
     */
    GameObject getObject();

}
