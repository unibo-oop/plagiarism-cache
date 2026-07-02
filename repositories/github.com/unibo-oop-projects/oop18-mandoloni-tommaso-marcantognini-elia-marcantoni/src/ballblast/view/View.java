package ballblast.view;

import ballblast.controller.Controller;
import ballblast.view.scenes.GameScenes;

/**
 * 
 * Interface that represents the view of the game.
 * 
 */

public interface View {

    /**
     * 
     * @param controller the controller
     */
    void launch(Controller controller);

    /**
     * Render method.
     */
    void render();

    /**
     * Load the scene.
     * 
     * @param scene The scene to be loaded.
     */
    void loadScene(GameScenes scene);

    /**
     * Sets gameover.
     * 
     * @param gameover If true the gameover scene is loaded.
     * 
     */
    void setGameOver(boolean gameover);
}
