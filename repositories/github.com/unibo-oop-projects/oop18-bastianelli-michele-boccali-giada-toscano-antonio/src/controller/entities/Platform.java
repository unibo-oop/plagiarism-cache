package controller.entities;

/**
 * The controller for a platform where the player jumps on
 */

import common.events.CollisionEvent;
import model.entities.EntityModel;
import view.entities.EntityView;

public class Platform extends AbstractEntity {

    private boolean busy;

    /**
     * Entity platform.
     * 
     * @param model the platform model
     * @param view  the platform view
     */
    public Platform(final EntityModel model, final EntityView view) {
        super(model, view);
    }

    @Override
    protected void handleCollisionEvent(final CollisionEvent collisionEvent) {
        // play sound
    }

    /**
     * Check if the platform is busy by another entity.
     * 
     * @return true if the platform is already busy by another entity
     */
    public boolean isBusy() {
        return busy;
    }

    /**
     * Set the platform busy.
     * 
     * @param busy
     */
    public void setBusy(final boolean busy) {
        this.busy = busy;
    }

}
