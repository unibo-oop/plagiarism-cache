package clashclass.elements.troops;

import clashclass.ecs.GameObject;

/**
 * Represents an implementation of TroopFactory used for player village (no battle behaviours).
 */
public class PlayerTroopFactoryImpl extends AbstractTroopFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalBarbarianComponents(final GameObject.Builder builder) {
        return builder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected GameObject.Builder createAdditionalArcherComponents(final GameObject.Builder builder) {
        return builder;
    }
}
