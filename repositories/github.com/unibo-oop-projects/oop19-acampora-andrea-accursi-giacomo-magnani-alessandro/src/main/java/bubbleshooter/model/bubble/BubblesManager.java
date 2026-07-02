package bubbleshooter.model.bubble;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class that hold and manages all the {@link Bubble} in the game.
 *
 */
public class BubblesManager {

    private final List<Bubble> bubbles;

    /**
     * Constructor used to initialize bubbles list.
     */
    public BubblesManager() {
        this.bubbles = new LinkedList<>();
    }

    /**
     * Method to update the {@link ShootingBubble} and to remove the destroyed
     * {@link Bubble}.
     * 
     * @param elapsed The time elapsed every {@link GameLoop} cycle.
     */
    public final void update(final double elapsed) {
        this.getShootingBubble().get().update(elapsed);
        this.bubbles.removeAll(this.bubbles.stream().filter(a -> a.isDestroyed()).collect(Collectors.toList()));
    }

    /**
     * @return all the{@link Bubble} currently in the game.
     */
    public final List<Bubble> getAllBubbles() {
        return Collections.unmodifiableList(this.bubbles);
    }

    /**
     * @param bubbles The {@link Bubble} to add at the list.
     */
    public final void addBubbles(final List<Bubble> bubbles) {
        this.bubbles.addAll(bubbles);
    }

    /**
     * @return The {@link ShootingBubble} of the game.
     */
    public final Optional<Bubble> getShootingBubble() {
        return this.bubbles.stream().filter(a -> a.getType().equals(BubbleType.SHOOTING_BUBBLE)).findFirst();
    }

    /**
     * @return The {@link SwitchBubble} of the game.
     */
    public final Optional<Bubble> getSwitchBubble() {
        return this.bubbles.stream().filter(a -> a.getType().equals(BubbleType.SWITCH_BUBBLE)).findFirst();
    }

    /**
     * @return The {@link Bubble} that are in the game's grid.
     */
    public final List<Bubble> getBubbleGrid() {
        return Collections.unmodifiableList(this.bubbles.stream()
                .filter(a -> a.getType().equals(BubbleType.GRID_BUBBLE)).collect(Collectors.toList()));
    }
}
