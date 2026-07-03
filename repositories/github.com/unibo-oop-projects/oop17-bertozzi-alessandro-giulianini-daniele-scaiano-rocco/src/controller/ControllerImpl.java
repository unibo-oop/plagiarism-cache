package controller;

import game.Game;
import game.GameImpl;
import game.GameMode;
import game.GameState;
import view.GamePanel;
import view.GameWindow;
import view.HighScoresPanel;
import view.View;
import view.ViewState;

/**
 * This is the controller, it is a filter between the view and the gameLoop. It makes sure the view doesn't access directly
 * to the main thread of the game.
 */
public final class ControllerImpl implements Controller {

    private View view;
    private GameLoop gameLoop;
    private Game game;
    private final HighscoreManager highscore;

    /**
     * 
     */
    public ControllerImpl() {
        this.highscore = new HighscoreManager();
    }

    @Override
    public void setView(final View view) {
        this.view = view;
    }

    /**
     * Starts the gameLoop and the game.
     * @param mode the gamemode chosen
     * @throws IllegalStateException if the view hasn't been created
     */
    private void startGame(final GameMode mode) throws IllegalStateException {
        if (this.view == null) {
            throw new IllegalStateException();
        }
        this.game = new GameImpl(mode);
        final KeyInput input = new KeyInput(game, this);
        this.gameLoop = new GameLoop(this.game, this.view, this.highscore, input);
        this.view.switchWindow(new GameWindow(mode, input), GameWindow.TITLE);
        this.gameLoop.start();
    }

    @Override
    public GameState checkGameState() {
        return this.game.getState();
    }

    @Override
    public void update(final GamePanel panel, final ViewState state) {
        if (state.equals(ViewState.HIGHSCORES) && panel instanceof HighScoresPanel) {
            ((HighScoresPanel) panel).setHighscores(highscore.getHighscores());
        } else if (state.equals(ViewState.RESUME)) {
            this.gameLoop.resume();
        } else if (state.equals(ViewState.ABORT)) {
            this.gameLoop.abort();
        } else if (state.equals(ViewState.START_SINGLEPLAYER)) {
            this.startGame(GameMode.SINGLEPLAYER);
        } else if (state.equals(ViewState.PAUSE)) {
            this.gameLoop.stop();
        } else {
            this.startGame(GameMode.MULTIPLAYER);
        }
    }

}
