package fargoal.model.interactable.pickupable.on_ground;

import java.util.Optional;

import fargoal.commons.api.Position;
import fargoal.model.commons.Timer;
import fargoal.model.events.impl.PickUpSword;
import fargoal.model.interactable.api.Interactable;
import fargoal.model.manager.api.FloorManager;
import fargoal.view.api.RenderFactory;
import fargoal.view.api.Renderer;

/**
 * This is the class that implements the Sword of Fargoal, the target of the player.
 */
public class SwordOfFargoal implements Interactable {

    private static final long TIME_TO_EXIT = 900_000;

    private Optional<Position> position;
    private final Integer mapLevel;
    private Renderer renderer;
    private final Timer endTimer;
    private boolean hasTimeAlreadyStarted;
    private boolean isPickedUp;

    /**
     * This is the constructor of the class. 
     * @param renderFactory - the factory from which the renderer needed is taken.
     * @param mapLevel - this is the floor level where the sword is.
     */
    public SwordOfFargoal(final RenderFactory renderFactory, final Integer mapLevel) {
        this.position = Optional.empty();
        this.isPickedUp = false;
        this.mapLevel = mapLevel;
        this.endTimer = new Timer();
        this.hasTimeAlreadyStarted = false;
        this.setRender(renderFactory);
    }

    /** {@inheritDoc} */
    @Override
    public final Position getPosition() {
        return this.position.get();
    }

    /**
     * Setter for the position of the sword.
     * @param position - the position of the sword in the level the player is.
     */
    public void setPosition(final Position position) {
        this.position = Optional.of(position);
    }

    /**
     * Getter for the field isPickedUp, which tells if the sword is picked up.
     * @return true if the sword is picked up, false otherwise.
     */
    public final boolean isPickedUp() {
        return this.isPickedUp;
    }

    /** {@inheritDoc} */
    @Override
    public final String getTag() {
        return "SWORD OF FARGOAL";
    }

    /**
     * Getter for the field mapLevel.
     * @return the level the sword can be found.
     */
    public Integer getMapLevel() {
        return this.mapLevel;
    }

    /** {@inheritDoc} */
    @Override
    public final void update(final FloorManager floorManager) {
        if (this.hasTimeAlreadyStarted && this.endTimer.updateTime(floorManager.getTimePassed()) == 0) {
            floorManager.setIsOver(true);
        }
    }

    /** {@inheritDoc} */
    @Override
    public final void render() {
        this.renderer.render();
    }

    private void setRender(final RenderFactory rf) {
        this.renderer = rf.swordRenderer(this);
    }

    /** {@inheritDoc} */
    @Override
    public final Interactable interact(final FloorManager floorManager) {
        floorManager.getPlayer().setHasSword(true);
        this.isPickedUp = true;
        floorManager.getPlayer().addExperiencePoints(floorManager.getPlayer().getExperiencePoints());
        floorManager.notifyFloorEvent(new PickUpSword(this));
        if (!hasTimeAlreadyStarted) {
            this.endTimer.setTime(TIME_TO_EXIT);
            this.hasTimeAlreadyStarted = true;
        }
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public boolean exists() {
        return !this.isPickedUp();
    }

}
