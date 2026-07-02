package model.entities.animations;

import model.entities.components.Moveset;
import model.entities.components.MovesetImpl;

/**
 * Implements a generic AnimationSupplier.
 */
public class AnimationSupplierImpl implements AnimationSupplier {

    private final Moveset animations = new MovesetImpl();

    /**
     * {@inheritDoc}
     */
    @Override
    public Moveset getAnimations() {
        return animations;
    }
}
