package it.unibo.oop.manpac.view.input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

import it.unibo.oop.manpac.model.Directions;
import it.unibo.oop.manpac.view.screens.game.GameView;

/**
 * Class that implements the inputProcessor interface for the management of
 * pressing the buttons from the keyboard.
 */
public final class PacmanInputProcessor implements InputProcessor {

    private final GameView gameView;

    /**
     * Constructor for PacmanInputProcessor.
     * 
     * @param gameView The view of the game
     */
    public PacmanInputProcessor(final GameView gameView) {
        this.gameView = gameView;
    }

    @Override
    public boolean keyDown(final int keycode) {
        switch (keycode) {
        case Keys.W:
        case Keys.UP:
            this.gameView.pacmanChangeDirection(Directions.UP);
            break;
        case Keys.S:
        case Keys.DOWN:
            this.gameView.pacmanChangeDirection(Directions.DOWN);
            break;
        case Keys.D:
        case Keys.RIGHT:
            this.gameView.pacmanChangeDirection(Directions.RIGHT);
            break;
        case Keys.A:
        case Keys.LEFT:
            this.gameView.pacmanChangeDirection(Directions.LEFT);
            break;
        case Keys.ESCAPE:
            this.gameView.setMainMenu();
            break;
        default:
            break;
        }

        return true;
    }

    // This and the methods below are not implemented because they are not useful as
    // input for the manpac game
    @Override
    public boolean keyTyped(final char arg0) {
        return false;
    }

    @Override
    public boolean keyUp(final int arg0) {
        return false;
    }

    @Override
    public boolean mouseMoved(final int arg0, final int arg1) {
        return false;
    }

    @Override
    public boolean scrolled(final int arg0) {
        return false;
    }

    @Override
    public boolean touchDown(final int arg0, final int arg1, final int arg2, final int arg3) {
        return false;
    }

    @Override
    public boolean touchDragged(final int arg0, final int arg1, final int arg2) {
        return false;
    }

    @Override
    public boolean touchUp(final int arg0, final int arg1, final int arg2, final int arg3) {
        return false;
    }

}
