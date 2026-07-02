package it.unibo.javacrush.powerup.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.Match;

/**
 * This abstract class is used as base by all the PowerUps classes of the game.
 */
public abstract class AbstractPowerUp {

    private final Set<Match> matches;

    /**
     * Constructor of {@link AbstractPowerUp}.
     */
    public AbstractPowerUp() {
        this.matches = new HashSet<>();
    }

    /**
     * Check if a PowerUp can be applied on the given position of the given board.
     * 
     * @param board the board that contains the position.
     * @param pos the position on where to apply the PowerUp.
     * @return false if pos isn't present in the board or if the cell in the board is empty.
     */
    public Boolean isAppliable(final Board board, final Position pos) {
        return board.getGrid().containsKey(pos) && board.getCellAt(pos).isPresent();
    }

    /**
     * Get all the Matches obtained by applying the PowerUp.
     * 
     * @return a Set of Matches.
     */
    public Set<Match> getMatches() {
        return Set.copyOf(this.matches);
    }

    /**
     * Add the given match in the private field matches.
     * 
     * @param match the match to add in the Set of Matches.
     */
    public void addMatches(final Match match) {
        this.matches.add(match);
    }

    /**
     * Apply to the specified board the special power.
     * 
     * @param board the board where to apply the power.
     * @param pos the position of the cell where to apply the power.
     * @return false if the power wasn't applied to the board, true otherwise.
     */
    public abstract Boolean applyPowerUp(Board board, Position pos);

}
