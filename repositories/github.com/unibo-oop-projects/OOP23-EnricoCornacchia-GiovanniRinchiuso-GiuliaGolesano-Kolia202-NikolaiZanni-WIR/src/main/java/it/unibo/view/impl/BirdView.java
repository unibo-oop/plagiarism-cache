package it.unibo.view.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.api.Entity;

/**
 * Class that implements the view of a bird power up.
 */
public final class BirdView extends BaseBirdCakeView {

    /**
     * Constructor.
     * 
     * @param bird the entity representing the bird
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need the original object")
    public BirdView(final Entity bird) {
        super(bird);
        initializeSprite("birdMovementRight");
    }

    /**
     * Returns the bird entity associated with this view.
     *
     * @return the bird entity
     */
    public Entity getBird() {
        return super.getEntity();
    }
}
