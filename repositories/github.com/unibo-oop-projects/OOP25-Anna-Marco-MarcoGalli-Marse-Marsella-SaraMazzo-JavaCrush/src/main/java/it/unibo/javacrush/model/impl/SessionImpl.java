package it.unibo.javacrush.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.GameState;
import it.unibo.javacrush.model.api.Goal;
import it.unibo.javacrush.model.api.GoalFactory;
import it.unibo.javacrush.model.api.Session;

/** 
 * Implementation of the {@link Session} interface.
 */
public class SessionImpl implements Session {

    private int movesLeft;
    private final List<Goal> goals = new ArrayList<>();

    /**
     * Constructor for the SessionImpl class.
     * 
     * @param moves the number of moves available for the session
     * @param goalsMap a map containing the goals of the session, where the key is the type of cell and 
     *      the value is the target amount for that type
     * @param factory the factory used to create goals
     */
    public SessionImpl(final int moves, final Map<CellType, Integer> goalsMap, final GoalFactory factory) {
        this.movesLeft = moves;

        goalsMap.entrySet().stream()
            .map(entry -> factory.createGoal(entry.getKey(), entry.getValue()))
            .forEach(goals::add);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMovesLeft() {
        return this.movesLeft;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decreaseMoves() {
        if (this.movesLeft == 0) {
            throw new IllegalStateException("We cannot decrease moves when already at 0");
        }
        this.movesLeft--;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Goal> getGoals() {
        return Collections.unmodifiableList(goals);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGoals(final CellType type, final int count) {
        this.goals.forEach(goal -> {
            if (goal.getTargetType() == type) {
                goal.addProgress(count);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameState getGameState() {
        if (this.isGameWon()) {
            return GameState.WON;
        } else if (this.isGameLost()) {
            return GameState.LOST;
        }
        return GameState.PLAYING;
    }

    /**
     * Check if the game is won by verifying if all goals are reached.
     * 
     * @return true if all goals are reached, false otherwise
     */
    private boolean isGameWon() {
        return this.goals.stream()
            .allMatch(goal -> goal != null && goal.isReached());
    }

    /**
     * Check if the game is lost by verifying if the player has no moves left.
     * 
     * @return true if the player has no moves left, false otherwise
     */
    private boolean isGameLost() {
        return this.movesLeft <= 0;
    }

}
