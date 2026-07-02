package playcontroller;

import controller.Controller;
import controls.TetrisKeyListener;
import gamegraphics.ViewGame;
import gamelogic.GameLogic;
import gamemanagement.GameManagement;
import movements.Movements;
import projection.Projection;
import sound.Sound;
import speed.Speed;

/**
 * This class has main methods to start, stop, restart the play. It also permits
 * to get its private fields.
 */
public interface PlayController extends Controller {

    /**
     * Stops the game.
     */
    void stop();

    /**
     * Resumes the game.
     */
    void resume();

    /**
     * Restarts the game.
     */
    void restart();

    /**
     * @return sound.
     */
    Sound getSound();

    /**
     * @return game.
     */
    GameLogic getGame();

    /**
     * @return movements.
     */
    Movements getMovements();

    /**
     * @return speed.
     */
    Speed getSpeed();

    /**
     * @return timerManager.
     */
    GameManagement getGameManagement();

    /**
     * @return projection.
     */
    Projection getProjection();

    /**
     * @return controls.
     */
    TetrisKeyListener getControls();

    /**
     * @return view.
     */
    ViewGame getView();
}
