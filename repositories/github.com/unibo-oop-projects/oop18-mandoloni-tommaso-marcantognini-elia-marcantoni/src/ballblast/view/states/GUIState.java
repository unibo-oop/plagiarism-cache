package ballblast.view.states;

import ballblast.controller.Controller;
import ballblast.view.scenecontroller.GUISceneController;
import javafx.scene.input.KeyEvent;

/**
 * 
 * States of the GUI scene.
 */
public abstract class GUIState {

    private final GUISceneController gui;
    private final Controller controller;

    /**
     * Initializes this state with the given {@link GUISceneController}.
     * 
     * @param gui        the {@link GUISceneController}.
     * @param controller the {@link Controller} of the game.
     */
    public GUIState(final GUISceneController gui, final Controller controller) {
        this.gui = gui;
        this.controller = controller;
    }

    /**
     * Method to be called when this state becomes the active state.
     */
    public abstract void onStateEntry();

    /**
     * Method to be called when this state becomes inactive.
     */
    public abstract void onStateExit();

    /**
     * Handler for the event.
     * 
     * @param event Info about key pressed.
     */
    public abstract void onKeyPressed(KeyEvent event);

    /**
     * Handler for the event.
     * 
     * @param event Info about key released.
     */
    public abstract void onKeyReleased(KeyEvent event);

    /**
     * Getter for the GUI at this state.
     * 
     * @return the {@link GUISceneController}.
     */
    protected GUISceneController getGUI() {
        return this.gui;
    }

    /**
     * Getter for the game {@link Controller}.
     * 
     * @return the {@link Controller}.
     */
    protected Controller getController() {
        return this.controller;
    }
}
