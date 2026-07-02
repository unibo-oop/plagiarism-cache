package bubbleshooter.model.collision;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.geometry.Point2D;
import bubbleshooter.model.Model;
import bubbleshooter.model.bubble.Bubble;
import bubbleshooter.model.game.level.Level;

/**
 * The Handler implementation of the {@link CollisionHandler} which manage the bubbles after a {@link Collision}.
 *
 */
public class GridCollisionHandler implements CollisionHandler {

    private Bubble shootingBubble;
    private final Bubble basicBubble;
    private final Level level;

    /**
     * @param collision The {@link Collision} with the grid to know which {@link Bubble} have collided.
     * @param level The current level of the game to work with {@link BubbleGridManager} and {@link BubbleGridHelper}.
     */
    public GridCollisionHandler(final Collision collision, final Level level) {
        this.shootingBubble = collision.getShootingBubble();
        this.basicBubble = collision.getCollided();
        this.level = level;
       }

    /**
     * The method which handle the {@link Collision}.
     * It also notify the {@link GameData} to update the score.
     */
    @Override
    public final void handle() {
        this.linkToGrid();
        if (this.canExplode()) {
            this.explode();
          } else {
                this.level.getGameData().addWrongShoot();
            }
    }

    /**
     * The method used to manage the snap of a {@link Bubble} with the grid.
     * It calls the {@link BubbleGridManager} of the level to add the {@link Bubble} to the grid.
     */
    private void linkToGrid() {
        this.shootingBubble = this.level.getGridManager().addToGrid(this.shootingBubble, this.getPositionToLink());
    }

    /**
     * The method used to manage the explosion of {@link Bubble} in the grid.
     * It calculates the Bubbles to explode with the private recursive method and after it checks 
     * for some isolated bubbles in the grid.
     */ 
    private void explode() {
        final Set<Bubble> toExplode = this.getBubblesToExplode(this.shootingBubble, new HashSet<>());
        toExplode.forEach(a -> a.destroy());
        final Set<Bubble> isolatedBubbles = this.level.getGridHelper().getIsolatedBubbles();
        isolatedBubbles.forEach(a -> a.destroy());
        toExplode.addAll(isolatedBubbles);
        toExplode.forEach(a -> this.level.getGameData().addDestroyedBubble());
    }

    /**
     * 
     * @return True if 3 {@link Bubble} are of the same {@link BubbleColor}.
     */
    private boolean canExplode() {
        return this.getBubblesToExplode(this.shootingBubble, new HashSet<Bubble>()).size() > 2;
    }

    /**
     * 
     * @param bubble The {@link Bubble} to calculate the neighbors to explode.
     * @return the List of the neighbor of the same color.
     */
    private List<Bubble> getNeighboursToExplode(final Bubble bubble) {
        return this.level.getGridHelper().getBubbleNeighbours(bubble).stream()
                                                           .filter(a -> this.level.getGridHelper().areEquals(a, bubble))
                                                           .collect(Collectors.toList());
     }

    /**
     * Recursive method to calculate all the {@link Bubble} to explode. It starts from the collided {@link Bubble} 
     *  and it's called for every neighbor to explode for it.
     * @param bubble The bubble to calculate the neighbors.
     * @param bubblesToPop  the Set of {@link Bubble} to explode.
     * @return the list of all {@link Bubble} to explode.
     */
    private Set<Bubble> getBubblesToExplode(final Bubble bubble, final Set<Bubble> bubblesToPop) {
        this.getNeighboursToExplode(bubble).stream()
                                           .filter(a -> !bubblesToPop.contains(a) && bubblesToPop.add(a))
                                           .forEach(a -> getBubblesToExplode(a, bubblesToPop));
        return bubblesToPop;
       }

    /**
     * @return the Set of points which a {@link Bubble} can snap on.
     */
    private Set<Point2D> getFreePlacesToLink() {
        return this.level.getGridHelper().getNeighbourPosition(this.basicBubble).stream()
                                                                      .filter(a -> !this.level.getGridHelper().getBubbleNeighbours(this.basicBubble)
                                                                      .stream()
                                                                      .anyMatch(b -> b.getPosition().equals(a)))
                                                                      .filter(a -> a.getX() >= Bubble.RADIUS && a.getX() 
                                                                      <= Model.WORLD_WIDTH - Bubble.RADIUS)
                                                                      .collect(Collectors.toSet());
     }

    /**
     * @return the nearest free position to snap on. 
     */
    private Point2D getPositionToLink() {
        if (this.getFreePlacesToLink().size() > 1) {
            return this.getFreePlacesToLink().stream()
                                             .min((a, b) -> Math.abs(shootingBubble.getPosition().getX() - a.getX()) 
                                                          + Math.abs(shootingBubble.getPosition().getY() - a.getY())
                                                          < Math.abs(shootingBubble.getPosition().getX() - b.getX())
                                                          + Math.abs(shootingBubble.getPosition().getY() - b.getY()) ? -1 : 1).get();
       } else {
             return this.getFreePlacesToLink().iterator().next();
        }
    }
}
