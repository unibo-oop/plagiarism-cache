package fargoal.model.interactable.stair.api;

import fargoal.commons.api.Position;
import fargoal.model.events.impl.WalkOverEvent;
import fargoal.model.manager.api.FloorManager;

/**
 * An abstract class to keep everything that is in common for every type of stair.
 */
public abstract class AbstractStairs implements Stairs {

    private final Position position;
    private Position lastPlayerPosition;

    /**
     * Constructor that assignes the position to its field {@link #position}.
     * @param position - the position of the stairs
     */
    protected AbstractStairs(final Position position) {
        this.position = position;
    }

    /** {@inheritDoc} */
    @Override
    public Position getPosition() {
        return this.position;
    }

    /** {@inheritDoc} */
    @Override
    public void update(final FloorManager floorManager) {
        if (!floorManager.getPlayer().getPosition().equals(this.lastPlayerPosition)
                && floorManager.getPlayer().getPosition().equals(this.position)) {
            floorManager.notifyFloorEvent(new WalkOverEvent(this));
        }
        this.lastPlayerPosition = floorManager.getPlayer().getPosition();
    }

    /** {@inheritDoc} */
    @Override
    public final boolean exists() {
        return true;
    }
}
