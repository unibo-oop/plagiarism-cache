package game.state;

import menu.windows.GameMenuGameOver;

/***
 * The game state that's run the end of the game.
 */
public class GameOverState implements GameState {
    /**
     * {@inheritDoc}
     */
    @Override
    public void runState() {
        new GameMenuGameOver();
    }

}
