package model.entities.animations;

import model.entities.components.Moveset;

/**
 * An AnimationSupplier provides animations and is used to initialize movesets.
 */
public interface AnimationSupplier {

    /**
     * 
     * @return a map of all the animations.
     */
    Moveset getAnimations();
}
