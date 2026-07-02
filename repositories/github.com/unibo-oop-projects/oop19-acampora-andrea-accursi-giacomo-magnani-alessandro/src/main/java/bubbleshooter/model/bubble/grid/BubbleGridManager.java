package bubbleshooter.model.bubble.grid;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import bubbleshooter.model.bubble.Bubble;
import bubbleshooter.model.bubble.BubbleColor;
import bubbleshooter.model.game.level.Level;
import javafx.geometry.Point2D;

/**
 * Class used to add, remove bubble from bubble grid and create new row of
 * bubbles.
 *
 */
public class BubbleGridManager {

    private int createdRows;
    private boolean offsetRow;
    private final Level level;

    /**
     * Constructor of {@link BubbleGridManager} used to initialize the {@link Level}.
     * @param level the {@link Level} of the game.
     */
    public BubbleGridManager(final Level level) {
        this.level = level;
        this.offsetRow = false;
        this.createdRows = 0;
    }

    /**
     * Creates new Row on the top of the grid.
     * 
     * @param bubblesPerRow The number of {@link Bubble} in the row.
     * @return new row
     */
    public final List<Bubble> createNewRow(final int bubblesPerRow) {
        final List<Bubble> newRow = new LinkedList<>();
        final double offset = this.offsetRow ? Bubble.WIDTH : Bubble.RADIUS;
        this.moveDownBubbles();
        for (int x = 0; x < bubblesPerRow; x++) {
            newRow.add(this.level.getBubbleFactory().createGridBubble(
                    new Point2D(x * Bubble.WIDTH + offset, Bubble.RADIUS), BubbleColor.getRandomColor()));
        }
        this.createdRows++;
        this.offsetRow = !offsetRow;
        return newRow;
    }

    /**
     * Creates a new bubble and tells the {@link BubblesManager} to add it to the
     * game.
     * 
     * @param bubble   to add
     * @param position of new bubble
     * @return the created {@link Bubble}.
     */
    public final Bubble addToGrid(final Bubble bubble, final Point2D position) {
        final Bubble bubbleToAdd = this.level.getBubbleFactory().createGridBubble(position, bubble.getColor());
        this.level.getBubblesManager().addBubbles(Collections.singletonList(bubbleToAdd));
        this.level.loadShootingBubble();
        this.level.loadSwitchBubble();
        return bubbleToAdd;
    }

    /**
     * Moves all the balls one row down to create a new one.
     */
    private void moveDownBubbles() {
        this.getBubbleGrid().stream().forEach(
                b -> b.setPosition(new Point2D(b.getPosition().getX(), b.getPosition().getY() + Bubble.WIDTH)));
    }

    /**
     * Gets the number of all created row in the game.
     * 
     * @return the created rows
     */
    public final int getCreatedRows() {
        return this.createdRows;
    }

    /**
     * Return true if the next row is an offset row, false otherwise.
     * 
     * @return offsetRow.
     */
    public final boolean isOffsetRow() {
        return this.offsetRow;
    }

    /**
     * Gets all bubbles in the grid.
     * 
     * @return a {@link List} of all {@link GridBubble}s.
     */
    public final List<Bubble> getBubbleGrid() {
        return this.level.getBubblesManager().getBubbleGrid();
    }

}
