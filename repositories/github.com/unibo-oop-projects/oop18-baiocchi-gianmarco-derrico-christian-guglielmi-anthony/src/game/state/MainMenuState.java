package game.state;

import menu.windows.GameMenuStart;

/**
 * The game state that's run main menu.
 */
public class MainMenuState implements GameState {

    /**
     * {@inheritDoc}
     */
    @Override
    public void runState() {
        new GameMenuStart();
    }
}
