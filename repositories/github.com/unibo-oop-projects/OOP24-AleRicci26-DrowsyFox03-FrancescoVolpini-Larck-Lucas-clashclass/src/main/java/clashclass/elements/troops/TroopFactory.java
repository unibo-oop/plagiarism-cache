package clashclass.elements.troops;

import clashclass.commons.Vector2D;
import clashclass.ecs.GameObject;

/**
 * Represents a Factory for troops creation.
 */
public interface TroopFactory {
    /**
     * Creates a barbarian.
     *
     * @param position world position of the barbarian
     *
     * @return the GameObject that represents the barbarian
     */
    GameObject createBarbarian(Vector2D position);

    /**
     * Creates an archer.
     *
     * @param position world position of the archer
     *
     * @return the GameObject that represents the archer
     */
    GameObject createArcher(Vector2D position);
}
