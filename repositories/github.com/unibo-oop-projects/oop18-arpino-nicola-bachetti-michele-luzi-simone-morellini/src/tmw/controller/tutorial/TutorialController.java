package tmw.controller.tutorial;

import java.util.Optional;

import tmw.common.GameStatesList;
import tmw.controller.world.RoomSwitcherPolicy;
import tmw.controller.world.WorldControllerImpl;
import tmw.model.world.AbstractWorld;
import tmw.model.world.GameWorld;
import tmw.view.level.RoomView;

/**
 * Class to represent the controller of the tutorial.
 */
public class TutorialController extends WorldControllerImpl {

    private final GameWorld world;
    private RoomSwitcherPolicy switcher;

    /**
     * @param world     world reference
     * @param levelView world view reference
     */
    public TutorialController(final AbstractWorld world, final RoomView levelView) {
        super(world, levelView);
        this.world = world;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateEntities(final double dt) {
        super.updateEntities(dt);
        this.getTriggers().forEach(t -> {
            t.intersect(world.getPlayer().get());
        });
    }

    /**
     * In this case tutorial is done when player has activated all triggers and when
     * also all enemies are dead. Switcher is locally implemented, the parameter
     * passed is not used in this case.
     */
    @Override
    public void initializeRoomSwitcher(final Optional<RoomSwitcherPolicy> switcher) {
        if (!switcher.isPresent()) {
            final RoomSwitcherPolicy sw = () -> {
                if (this.allTriggered() && this.getEscapeDoor().isTriggered()
                        && this.getEscapeDoor().intersect(this.getPlayer().getEntity())) {
                    this.setChanged();
                    this.notifyObservers(GameStatesList.TUTORIAL_DONE);
                }
            };
            this.switcher = sw;
            super.initializeRoomSwitcher(Optional.ofNullable(this.switcher));
        } else {
            super.initializeRoomSwitcher(switcher);
        }
    }

    private boolean allTriggered() {
        return this.getTriggers().stream().filter(e -> !e.isTriggered()).count() > 0 ? false : true;
    }
}

