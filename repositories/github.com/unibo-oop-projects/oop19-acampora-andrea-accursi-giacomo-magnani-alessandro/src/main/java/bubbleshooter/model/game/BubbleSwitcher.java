package bubbleshooter.model.game;

import java.util.List;
import bubbleshooter.model.bubble.Bubble;
import bubbleshooter.model.bubble.BubbleColor;
import bubbleshooter.model.bubble.BubbleType;

/**
 * Class that switches the {@link SwitchBubble} with the {@link ShootingBubble}.
 * Used by {@link SwitcherController}.
 */
public class BubbleSwitcher {

    private final List<Bubble> bubbles;

    /**
     * Constructor for a new BubbleSwitcher.
     * 
     * @param bubbles , the list of all {@link Bubble}s.
     */
    public BubbleSwitcher(final List<Bubble> bubbles) {
        this.bubbles = bubbles;
    }

    /**
     * Method that switches the {@link SwitchBubble} with the {@link ShootingBubble}.
     */
    public final void switchBall() {
        final BubbleColor bubbleColor = bubbles.stream()
                                               .filter(a -> a.getType().equals(BubbleType.SHOOTING_BUBBLE))
                                               .findFirst().get().getColor();
        this.bubbles.stream()
                    .filter(a -> a.getType().equals(BubbleType.SHOOTING_BUBBLE))
                    .findFirst().get()
                    .setColor(bubbles.stream()
                    .filter(a -> a.getType().equals(BubbleType.SWITCH_BUBBLE))
                    .findFirst().get().getColor());
        this.bubbles.stream()
                    .filter(a -> a.getType().equals(BubbleType.SWITCH_BUBBLE))
                    .findFirst().get()
                    .setColor(bubbleColor);
    }
}

