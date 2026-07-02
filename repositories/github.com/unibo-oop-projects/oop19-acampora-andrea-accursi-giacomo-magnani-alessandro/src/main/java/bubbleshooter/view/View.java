package bubbleshooter.view;

import bubbleshooter.view.scene.FXMLPath;
import bubbleshooter.controller.Controller;

/**
 * Represents the view of the application in MVC pattern.
 */
public interface View {

    /**
     * Sets the controller to be used, loads all images and sets stage size and
     * title.
     * 
     * @param controller The {@link Controller} of the game.
     */
    void launch(Controller controller);

    /**
     * Loads the scene and change the current scene displayed.
     * 
     * @param scene The scene to loads
     */
    void loadScene(FXMLPath scene);

    /**
     * Show game over scene.
     */
    void showGameOver();

    /**
     * Tells to the current scene to update the view.
     */
    void update();
}
