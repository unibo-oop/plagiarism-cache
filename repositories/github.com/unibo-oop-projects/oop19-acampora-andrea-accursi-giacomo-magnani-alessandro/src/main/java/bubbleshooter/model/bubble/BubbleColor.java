package bubbleshooter.model.bubble;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Enumeration which contains all the {@link BubbleColor} of the {@link Bubble} in the game.
 *
 */
public enum BubbleColor {
    /**
     * The list of possibles colors of the {@link Bubble}.
     */
    RED, YELLOW, GREEN, LIGHT_BLUE, BLUE, PURPLE; 

    private static final List<BubbleColor> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    /**
     * 
     * @return a random Color from the possibles colors in {@link BubbleColor} enumeration.
     */
    public static BubbleColor getRandomColor() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
