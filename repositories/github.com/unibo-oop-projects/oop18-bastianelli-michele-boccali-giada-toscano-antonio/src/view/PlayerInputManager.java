package view;

import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;

import java.util.HashSet;
import java.util.Set;

import common.EventBusConnection;
import common.events.EscEvent;
import common.events.MovementEvent;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import utils.Box2DUtils;

public class PlayerInputManager extends EventBusConnection {

    private final Set<KeyCode> pressedKeys = new HashSet<>();
    private static final KeyCode LEFT_KEY = KeyCode.A;
    private static final KeyCode RIGHT_KEY = KeyCode.D;
    private static final KeyCode ESC_KEY = KeyCode.ESCAPE;

    public PlayerInputManager() {
        super();
    }

    private void keyPressed(final KeyEvent event) {
        if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
            pressedKeys.add(event.getCode());
        } else if (event.getEventType().equals(KeyEvent.KEY_RELEASED)) {
            pressedKeys.clear();
            // pressedKeys.remove(event.getCode());
        }

        if (pressedKeys.contains(ESC_KEY)) {
            this.getBus().post(new EscEvent());
        } else {
            this.getBus().post(new MovementEvent(Box2DUtils.pointToVec(keyToDirection())));
        }
    }

    private Point2D keyToDirection() {
        Point2D direction = Point2D.ZERO;
        if (pressedKeys.contains(RIGHT_KEY) || pressedKeys.contains(RIGHT)) {
            direction = direction.add(1, 0);
            //Log.add("dx");
        }
        if (pressedKeys.contains(LEFT_KEY) || pressedKeys.contains(LEFT)) {
            direction = direction.add(-1, 0);
            //Log.add("sx");
        }
        return direction;
    }

    /**
     * Removes listeners.
     * 
     * @param scene - the game scene
     */
    public void removeListeners(final Scene scene) {
        scene.removeEventHandler(KeyEvent.KEY_PRESSED, this::keyPressed);
        scene.removeEventFilter(KeyEvent.KEY_RELEASED, this::keyPressed);
        pressedKeys.clear();
    }

    /**
     * Adds listeners.
     * 
     * @param scene - the game scene
     */
    public void addListeners(final Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, this::keyPressed);
        scene.addEventHandler(KeyEvent.KEY_RELEASED, this::keyPressed);
        pressedKeys.clear();
    }
}