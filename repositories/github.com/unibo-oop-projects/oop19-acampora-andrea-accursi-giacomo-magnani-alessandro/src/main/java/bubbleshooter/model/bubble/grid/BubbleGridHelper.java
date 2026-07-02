package bubbleshooter.model.bubble.grid;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import bubbleshooter.model.bubble.Bubble;
import bubbleshooter.model.bubble.BubbleColor;
import bubbleshooter.model.bubble.BubbleType;
import bubbleshooter.model.bubble.BubblesManager;
import javafx.geometry.Point2D;

/**
 * Class used to manage the grid of {@link Bubble} in the Game. It's used also
 * by the {@link GridCollisionHandler} class to get the info about grid.
 */
public class BubbleGridHelper {

    private final BubblesManager bubblesManager;
    private final double diagonalDistance = Bubble.WIDTH * 1.20;

    /**
     * @param bubblesManager The manager of the {@link Bubble} in the Game.
     */
    public BubbleGridHelper(final BubblesManager bubblesManager) {
        this.bubblesManager = bubblesManager;
    }

    /**
     * @param bubble The {@link Bubble} to calculate the neighbors.
     * @return The list of neighbors of the {@link Bubble}.
     */
    public final List<Bubble> getBubbleNeighbours(final Bubble bubble) {
        return this.getBubbleGrid().stream().filter(a -> this.isNear(a, bubble)).collect(Collectors.toList());
    }

    /**
     * @return The current grid of {@link Bubble} in the game.
     */
    public final List<Bubble> getBubbleGrid() {
        return this.bubblesManager.getBubbleGrid();
    }

    /**
     * 
     * @param bubbleAt The first {@link Bubble}.
     * @param bubbleTo The second {@link Bubble}.
     * @return the distance from the 2 bubbles in input.
     */
    public final double getDistanceBetweenBubbles(final Bubble bubbleAt, final Bubble bubbleTo) {
        final Point2D bubbleAtPos = bubbleAt.getPosition();
        final Point2D bubbleToPos = bubbleTo.getPosition();
        return Math.sqrt(Math.pow(bubbleAtPos.getX() - bubbleToPos.getX(), 2)
                + Math.pow(bubbleAtPos.getY() - bubbleToPos.getY(), 2));
    }

    /**
     * @param bubbleAt The first bubble in the method.
     * @param bubbleTo The second bubble in the method.
     * @return True if the 2 bubbles given are near in the game's grid.
     */
    public final boolean isNear(final Bubble bubbleAt, final Bubble bubbleTo) {
        return this.getDistanceBetweenBubbles(bubbleAt, bubbleTo) <= this.diagonalDistance
                && this.getDistanceBetweenBubbles(bubbleAt, bubbleTo) > 0;
    }

    /**
     * @param bubbleAt The first bubble in the method.
     * @param bubbleTo The second bubble in the method.
     * @return True if the 2 bubbles are of the same {@link BubbleColor}.
     */
    public final boolean areEquals(final Bubble bubbleAt, final Bubble bubbleTo) {
        return bubbleAt.getColor().equals(bubbleTo.getColor());
    }

    /**
     * @param starting
     * @param linkedBubbles
     * @return Recursive method to return the list of {@link Bubble} linked to the
     *         startingBubble.
     */
    private List<Bubble> getLinkedBubbles(final Bubble starting, final List<Bubble> linkedBubbles) {
        this.getBubbleNeighbours(starting).stream()
                .filter(a -> !linkedBubbles.contains(a) && !a.isDestroyed() && linkedBubbles.add(a))
                .forEach(a -> this.getLinkedBubbles(a, linkedBubbles));
        return linkedBubbles;
    }

    /**
     * @return The Set of {@link Bubble} which there are no more linked to the
     *         game's grid.
     */
    public final Set<Bubble> getIsolatedBubbles() {
        final Set<Bubble> firstLineBubbles = this.getBubbleGrid().stream()
                .filter(a -> a.getPosition().getY() == Bubble.WIDTH / 2 && !a.isDestroyed())
                .collect(Collectors.toSet());
        final Set<Bubble> linkedBubbles = new HashSet<>();
        linkedBubbles.addAll(firstLineBubbles);
        for (final Bubble bubble : firstLineBubbles) {
            linkedBubbles.addAll(this.getLinkedBubbles(bubble, new LinkedList<Bubble>()));
        }
        return this.getBubbleGrid().stream().filter(a -> !linkedBubbles.contains(a)).collect(Collectors.toSet());
    }

    /**
     * 
     * @param bubble The bubble to calculate the neighbors positions.
     * @return The Set of position which a {@link Bubble} can snap on.
     */
    public final Set<Point2D> getNeighbourPosition(final Bubble bubble) {
        final Point2D bubblePos = bubble.getPosition();
        return Set.of(new Point2D(bubblePos.getX() - Bubble.WIDTH, bubblePos.getY()),
                new Point2D(bubblePos.getX() + Bubble.WIDTH, bubblePos.getY()),
                new Point2D(bubblePos.getX() - Bubble.WIDTH / 2, bubblePos.getY() - Bubble.WIDTH),
                new Point2D(bubblePos.getX() + Bubble.WIDTH / 2, bubblePos.getY() - Bubble.WIDTH),
                new Point2D(bubblePos.getX() - Bubble.WIDTH / 2, bubblePos.getY() + Bubble.WIDTH),
                new Point2D(bubblePos.getX() + Bubble.WIDTH / 2, bubblePos.getY() + Bubble.WIDTH));
    }

    /**
     * @return The currents {@link BubbleColor} in the game. Useful to not generate
     *         some other colors in the end of the game.
     */
    public final List<BubbleColor> getRemainingColors() {
        return this.getBubbleGrid().stream().filter(b -> b.getType().equals(BubbleType.GRID_BUBBLE))
                .map(b -> b.getColor()).distinct().collect(Collectors.toList());
    }
}
