package view;

import javafx.stage.Stage;

/**
*
*/
public interface WindowManager {

    /**
     * Add the game map to the stage and set its scene.
     *
     * @param gameMap current game map
     */

    void addGameMap(GameMap gameMap);

    /**
     * @return actual Stage.
     */
    Stage getStage();
}
