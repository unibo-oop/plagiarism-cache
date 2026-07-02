package it.unibo.view.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.api.Entity;

/**
 * Class that implements the view of a cake power-up.
 */
public final class CakeView extends BaseBirdCakeView {

    /**
     * Constructor.
     * 
     * @param cake the entity representing the cake
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need the original object")
    public CakeView(final Entity cake) {
        super(cake);
        initializeSprite("cake");
    }

    /**
     * Returns the cake entity associated with this view.
     *
     * @return the cake entity
     */
    public Entity getCake() {
        return super.getEntity();
    }
}
