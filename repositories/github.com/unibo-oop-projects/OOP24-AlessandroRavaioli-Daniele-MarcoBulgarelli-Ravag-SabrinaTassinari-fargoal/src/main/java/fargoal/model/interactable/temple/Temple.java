package fargoal.model.interactable.temple;

import fargoal.commons.api.Position;
import fargoal.model.events.impl.BlessedEvent;
import fargoal.model.events.impl.WalkOverEvent;
import fargoal.model.interactable.api.Interactable;
import fargoal.model.manager.api.FloorManager;
import fargoal.view.api.RenderFactory;
import fargoal.view.api.Renderer;

/**
 * This class implements the temple of the floor.
 * In the temple the player can not be attacked and he can donate 
 * the gold he has to gain experience (as much experience as the gold he has).
 * If the player has donated more than 2000 gold he is blessed: all his health is 
 * restored.
 */
public class Temple implements Interactable {

    private static final int GOLD_TO_DONATE_FOR_BLESSING = 2000;
    private final Position position;
    private Renderer renderer;
    private Position lastPlayerPosition;

    /**
     * This is the constructor of the class. It set the position of the temple.
     * @param position - the position of the temple.
     * @param renderFactory - - a factory to create the renderer of all the elements of the floor.
     */
    public Temple(final Position position, final RenderFactory renderFactory) {
        this.position = position;
        this.setRender(renderFactory);
    }

    /** {@inheritDoc} */
    @Override
    public Interactable interact(final FloorManager floorManager) {
        floorManager.getPlayer().addExperiencePoints(floorManager.getPlayer().getCurrentGold());
        floorManager.getPlayer().levelUp();
        floorManager.getPlayer().getPlayerGold().setGoldDonated(
                floorManager.getPlayer().getPlayerGold().getGoldDonated() + floorManager.getPlayer().getCurrentGold());
        if (floorManager.getPlayer().getPlayerGold().getGoldDonated() >= GOLD_TO_DONATE_FOR_BLESSING) {
            floorManager.getPlayer().setHealth(floorManager.getPlayer().getMaxHealth());
            floorManager.getPlayer().getPlayerGold().setGoldDonated(0);
            floorManager.notifyFloorEvent(new BlessedEvent());
        }
        floorManager.getPlayer().getPlayerGold().resetGold();
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public Position getPosition() {
        return this.position;
    }

    /** {@inheritDoc} */
    @Override
    public String getTag() {
        return "TEMPLE";
    }

    /** {@inheritDoc} */
    @Override
    public void render() {
        this.renderer.render();
    }

    private void setRender(final RenderFactory rf) {
        this.renderer = rf.templeRenderer(this);
    }

    /** {@inheritDoc} */
    @Override
    public void update(final FloorManager floorManager) {
        if (floorManager.getPlayer().getPosition().equals(this.position)) {
            floorManager.getPlayer().setIsImmune(true);
            if (!floorManager.getPlayer().getPosition().equals(this.lastPlayerPosition)) {
                floorManager.notifyFloorEvent(new WalkOverEvent(this));
            }
            this.interact(floorManager);
        } else {
            floorManager.getPlayer().setIsImmune(false);
        }
        this.lastPlayerPosition = floorManager.getPlayer().getPosition();
    }

    /** {@inheritDoc} */
    @Override
    public boolean exists() {
        return true;
    }

}
