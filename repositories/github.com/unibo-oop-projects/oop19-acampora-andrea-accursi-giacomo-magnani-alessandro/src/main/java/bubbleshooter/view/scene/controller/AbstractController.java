package bubbleshooter.view.scene.controller;

import bubbleshooter.view.View;
import bubbleshooter.view.scene.FXMLPath;
import bubbleshooter.controller.Controller;

/**
 * Abstract class used like a basic controller, the other controller will extends it.
 */
public abstract class AbstractController {

    private Controller controller;
    private View view;
    private FXMLPath nextScene;

    /**
     * Method that initialized the controller and the view.
     * @param controller The controller of the game.
     * @param view       The controller of the view.
     */
    public abstract void init(Controller controller, View view);

    /**
     * @return The {@link Controller}.
     */
    public final Controller getController() {
        return this.controller;
    }

    /**
     * @return The {@link View}.
     */
    public final View getView() {
        return this.view;
    }

    /**
     * Redraws the scene on the screen.
     */
    public void render() {
        // Empty for subclasses.
    }

    /**
     * Method that load the next scene.
     */
    public final void loadNextScene() {
        this.view.loadScene(this.nextScene);
    }

    /**
     * Method that set the next {@link GameScenes}.
     * @param nextScene The scene to load.
     */
    public final void setNextScene(final FXMLPath nextScene) {
        this.nextScene = nextScene;
    }

    /**
     * Method that set the {@link Controller}.
     * @param controller The controller.
     */
    public final void setController(final Controller controller) {
        this.controller = controller;
    }

    /**
     * Method that set the {@link View}.
     * @param view The view.
     */
    public final void setView(final View view) {
        this.view = view;
    }
}
