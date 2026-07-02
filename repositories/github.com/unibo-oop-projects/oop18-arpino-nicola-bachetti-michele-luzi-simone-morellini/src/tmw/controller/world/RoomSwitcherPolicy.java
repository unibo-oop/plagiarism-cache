package tmw.controller.world;

/**
 * This interface provides a method to be programmed whit a specific behavior.
 * Typically should be programmed with a strategy that specifies when player can
 * change room.
 * 
 * For example a simple strategy could be: when all enemies are dead spawn a
 * door, collision with this door switches to another room.
 * This is a {@link FunctionalInterface}
 */
@FunctionalInterface
public interface RoomSwitcherPolicy {

    /**
     * Just execute programmed behavior.
     */
    void executeSwitch();
}

