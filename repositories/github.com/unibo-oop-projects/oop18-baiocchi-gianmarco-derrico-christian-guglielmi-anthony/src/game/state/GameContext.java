package game.state;

import java.util.Optional;

/**
 * This is a game context, it's going to delegate the behavior to the state
 * implementation. In other words, all incoming requests will be handled by the
 * concrete implementation of the state. Moreover its task is to check that the
 * new state to be implemented is not the same as the one in progress.
 *
 */
public class GameContext {

    private Optional<GameState> currentState;

    /**
     * 
     */
    public GameContext() {
        this.currentState = Optional.empty();
    }

    /**
     * A getter to get the current game state.
     * 
     * @return the current game state
     */
    public GameState getCurrentState() {
        return currentState.get();
    }

    /**
     * A setter to set the new game state.
     * 
     * @param newState the new game state to set
     */
    public void setNewState(final GameState newState) {
        final String currentStateName = this.currentState.getClass().getSimpleName();
        final String newStateName = newState.getClass().getSimpleName();

        if (!currentStateName.equals(newStateName)) {
            changeState(newState);
        }
    }

    private void changeState(final GameState newState) {
        this.currentState = Optional.of(newState);
        newState.runState();
    }
}
