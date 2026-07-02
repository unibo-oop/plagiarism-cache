package view.scene;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import view.controller.ControllerFXML;
import view.utility.ProxyFXMLLoader;
import view.utility.SceneType;

/**
 * Class that represent a generic scene. Some method need to be defined in a
 * specific concrete subclass.
 */
public abstract class AbstractGenericScene implements GenericScene {

    private final SceneType scene;
    private final ControllerFXML controller;
    private final EventHandler<KeyEvent> sceneHandler;

    /**
     * Constructor for a generic scene.
     * 
     * @param s
     *            Scene type.
     */
    public AbstractGenericScene(final SceneType s) {
        scene = s;
        controller = ProxyFXMLLoader.get().getFXMLController(s);
        sceneHandler = e -> {
            if (e.getCode() == KeyCode.ESCAPE && e.getEventType().equals(KeyEvent.KEY_PRESSED)) {
                exitStatus();
            }
            checkSceneHandler(e);
        };
    }

    /**
     * Method called when 'esc' command in pressed. It depends on a type of scene.
     */
    public abstract void exitStatus();

    /**
     * Method used to check key events when handler captures these. It depends on a
     * type of scene.
     * 
     * @param e
     *            Event captured by the handler.
     */
    public abstract void checkSceneHandler(Event e);

    /**
     * Set listener for this scene.
     */
    public abstract void notifyFocusUp();

    /**
     * Remove listener for this scene.
     */
    public abstract void notifyFocusDown();

    /**
     * Get the scene type.
     * 
     * @return The type of scene, represented by a enum type.
     */
    public SceneType getSceneType() {
        return this.scene;
    }

    /**
     * Getter for a event handler.
     * 
     * @return The event handler for the scene.
     */
    public EventHandler<KeyEvent> getEventHandler() {
        return this.sceneHandler;
    }

    /**
     * Get controller FXML.
     * 
     * @return the controller.
     */
    public ControllerFXML getSceneController() {
        return controller;
    }
}
