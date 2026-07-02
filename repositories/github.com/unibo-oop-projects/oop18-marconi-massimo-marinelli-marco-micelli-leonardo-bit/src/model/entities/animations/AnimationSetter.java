package model.entities.animations;

import model.entities.GameEntityClasses;
import model.entities.components.Moveset;

/**
 * Sets movesets with a proper set of animations.
 */
public interface AnimationSetter {

    /**
     * 
     * @param entityClass
     *                          the player entity's in-game class.
     * @param moveset
     *                          a proper moveset for the entityClass.
     */
    void addAnimationsToMoveset(GameEntityClasses entityClass, Moveset moveset);
}
