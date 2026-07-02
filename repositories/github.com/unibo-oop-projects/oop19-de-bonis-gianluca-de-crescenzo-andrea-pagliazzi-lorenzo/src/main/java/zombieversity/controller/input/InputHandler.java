package zombieversity.controller.input;

import java.util.Optional;
import java.util.Set;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;

/**
 * The interface to manage input from keyBoard and mouse.
 */
public interface InputHandler {

    /**
     * Enum to recognize the type of mouse input, independently from the view.
     */
    enum MouseInput {
        /**
         * Left click.
         */
        PRIMARY,
        /**
         * Rigth click.
         */
        SECONDARY
    }

    /**
     * 
     * @return handler for mouse event.
     */
    EventHandler<MouseEvent> mouseMoved();

    /**
     * 
     * @return handler fot key event.
     */
    EventHandler<KeyEvent> keyBoard();

    /**
     * 
     * @return handler for mouse event.
     */
    EventHandler<MouseEvent> mouseClicked();

    /**
     * 
     * @return handler for mouse event.
     */
    EventHandler<MouseEvent> mouseReleased();

    /**
     * 
     * @return the set of key pressed.
     */
    Set<KeyCode> getKeyPressed();

    /**
     * 
     * @return the mouse position.
     */
    Point2D getMousePosition();

    /**
     * 
     * @return where the mouse clicked.
     */
    Optional<Pair<Point2D, MouseInput>> getMouseClicked();

}
