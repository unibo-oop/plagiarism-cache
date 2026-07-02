package spacesurvival.view.game.utilities.commandlife;

import spacesurvival.model.gameobject.GameObject;

/**
 * Implements the waist size of the gameObject.
 */
public interface CommandLife {
    /**
     * Command for get size life bar.
     * @param gameObject
     * @return size for lifer bar.
     */
    int execute(GameObject gameObject);
}
