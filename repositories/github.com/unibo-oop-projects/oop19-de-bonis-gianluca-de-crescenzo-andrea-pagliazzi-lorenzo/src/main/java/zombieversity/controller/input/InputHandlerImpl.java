package zombieversity.controller.input;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;

/**
 * 
 * The implementation of {@link InputHandler}.
 *
 */
public class InputHandlerImpl implements InputHandler {

    private final Set<KeyCode> keyPressed;
    private Optional<Pair<Point2D, MouseInput>> mouseClicked;
    private Point2D mouseMoved;

    /**
     * Construct a {@link InputHandler}.
     *
     */
    public InputHandlerImpl() {
        this.keyPressed = new HashSet<>();
        this.mouseClicked = Optional.empty();
        this.mouseMoved = Point2D.ZERO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final EventHandler<MouseEvent> mouseMoved() {
        return new EventHandler<MouseEvent>() {

            @Override
            public void handle(final MouseEvent event) {
                mouseMoved = new Point2D(event.getSceneX(), event.getSceneY());
            }
        };

    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public final EventHandler<KeyEvent> keyBoard() {
        return new EventHandler<KeyEvent>() {

            @Override
            public void handle(final KeyEvent event) {
                if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
                    keyPressed.add(event.getCode());
                } else if (event.getEventType().equals(KeyEvent.KEY_RELEASED)) {
                    keyPressed.remove(event.getCode());
                }
            }
        };

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final EventHandler<MouseEvent> mouseClicked() {
        return new EventHandler<MouseEvent>() {

            @Override
            public void handle(final MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    mouseClicked = Optional.of(new Pair<>(new Point2D(event.getSceneX(), event.getSceneY()), MouseInput.PRIMARY));
                } else {
                    mouseClicked = Optional.of(new Pair<>(new Point2D(event.getSceneX(), event.getSceneY()), MouseInput.SECONDARY));
                }
            }
        };

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final EventHandler<MouseEvent> mouseReleased() {
        return new EventHandler<MouseEvent>() {

            @Override
            public void handle(final MouseEvent event) {
                mouseClicked = Optional.empty();
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Set<KeyCode> getKeyPressed() {
        return Set.copyOf(this.keyPressed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Point2D getMousePosition() {
        return this.mouseMoved;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Optional<Pair<Point2D, MouseInput>> getMouseClicked() {
        return this.mouseClicked;
    }
}
