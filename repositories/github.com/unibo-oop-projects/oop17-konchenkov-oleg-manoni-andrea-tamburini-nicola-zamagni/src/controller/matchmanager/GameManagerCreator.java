package controller.matchmanager;

import controller.Controller;
import view.View;

/**
 *
 * @author Oleg
 *
 */
public interface GameManagerCreator {

    /**
     *
     * @param gameModeType
     *            game mode type
     * @param controller
     *            controller
     * @param view
     *            view
     * @return game manager
     */
    GameManager createGameManager(GameModeType gameModeType, Controller controller, View view);
}
