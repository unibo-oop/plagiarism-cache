package projection;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import gamelogic.GameLogic;
import pair.Pair;

/**
 * This class implements the interface {@link Projection}.
 */
public final class ProjectionImpl implements Projection {
    private final GameLogic game;
    private int top;
    private boolean isEnabled;

    /**
     * Constructor of class ProjectionImpl.
     * 
     * @param game is used to get the current piece.
     * 
     */
    public ProjectionImpl(final GameLogic game) {
        this.game = game;
        this.isEnabled = true;
    }

    /*
     * Calculates the effective coordinates of the projection.
     */
    private Set<Pair<Integer, Integer>> calculateEffectiveCoordinates(final int top) {
        // set for new coordinates
        final Set<Pair<Integer, Integer>> newCoordinates = new HashSet<>();
        // calculate the new coordinates with the current left and the new top
        this.game.getCurrent().getCoordinates().forEach(coord -> newCoordinates
                .add(new Pair<>(coord.getX() + top, coord.getY() + this.game.getCurrent().getLeft())));
        return newCoordinates;
    }

    @Override
    public Set<Pair<Integer, Integer>> newProjection() {
        if (this.isEnabled) {
            this.top = this.game.getCurrent().getTop();
            // calculate the right top
            while (this.game.isLegalPosition(this.calculateEffectiveCoordinates(this.top + 1))) {
                this.top = this.top + 1;
            }
            return this.calculateEffectiveCoordinates(this.top);
        } else {
            return new HashSet<>();
        }
    }

    @Override
    public Color getColor() {
        return new Color(this.game.getCurrent().getColor().getRed(), this.game.getCurrent().getColor().getGreen(),
                this.game.getCurrent().getColor().getBlue(), TRANSPARENCY);
    }

    @Override
    public void setEnable(final boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
