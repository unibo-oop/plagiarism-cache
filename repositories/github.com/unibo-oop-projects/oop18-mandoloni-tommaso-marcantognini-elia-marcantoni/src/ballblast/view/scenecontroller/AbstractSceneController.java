package ballblast.view.scenecontroller;

import ballblast.controller.Controller;
import ballblast.view.View;
import ballblast.view.scenes.GameScenes;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;

/**
 * 
 * A simple scene controller.
 */
public abstract class AbstractSceneController {

    private Controller controller;
    private View view;

    /**
     * Method that initialized the controller and the view.
     * 
     * @param controller the controller of the game.
     * @param view       the controller of the view.
     */
    public void init(final Controller controller, final View view) {
        this.controller = controller;
        this.view = view;
    }

    /**
     * 
     * @return The {@link Controller}.
     */
    public Controller getController() {
        return this.controller;
    }

    /**
     * 
     * @return The {@link View}.
     */
    protected View getView() {
        return this.view;
    }

    /**
     * Redraws the scene on the screen.
     */
    public void render() {
        // Empty for subclasses.
    }

    /**
     * Loads the next scene.
     */
    @FXML
    public void nextScene() {
        this.view.loadScene(this.getNextScene());
    }

    /**
     * 
     * @return The next {@link GameScenes}.
     */
    public abstract GameScenes getNextScene();

    /**
     * Go to the previous scene.
     */
    @FXML
    public void backScene() {
        this.view.loadScene(this.getPreviousScene());
    }

    /**
     * 
     * @return The previous {@link GameScenes}.
     */
    protected abstract GameScenes getPreviousScene();

    /**
     * Event handler.
     * 
     * @param event the information about the event.
     */
    @FXML
    public void onKeyPressed(final KeyEvent event) {
        // Empty for subclasses.
    }

    /**
     * 
     * @param gameover true if the player lose.
     */
    public void setGameover(final boolean gameover) {
        // Empty for subclasses.
    }
}
