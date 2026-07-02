package view;

import java.util.List;

import enums.GameScene;
import model.entities.GameEntity;

/**
 * The interface for the view.
 */
public interface View {

    /**
     * Method that initialize the JavFx's Stage.
     */
    void initWindow();

    /**
     * Method that load the specified scene.
     * 
     * @param scene the scene to be loaded.
     */
    void loadScene(GameScene scene);

    /**
     * Method that show the main window.
     */
    void showWindow();

    /**
     * Method that render the current scene.
     * 
     * @param gameEntities entities of the game.
     */
    void renderCurrentScene(List<GameEntity> gameEntities);

}
