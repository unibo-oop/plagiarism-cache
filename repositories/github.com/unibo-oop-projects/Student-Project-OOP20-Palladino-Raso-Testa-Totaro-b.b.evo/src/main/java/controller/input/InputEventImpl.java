package controller.input;

import controller.game.GameStatus;
import controller.game.GameState;
import controller.game.GameStateImpl;
import javafx.scene.canvas.Canvas;

public class InputEventImpl implements InputEvent {

    private final GameState state;
    private final ControllerInput controller;
    private final Canvas canvas;

    public InputEventImpl(final Canvas canvas, final ControllerInput controller, final GameState state) {
        this.state = state;
        this.canvas = canvas;
        this.controller = controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyEvent() {
        isPressing();
        stoppedPressing();
    }

    /**
     * Change player status and game status based on the key pressed.
     */
    private void isPressing() {
        this.canvas.setOnKeyPressed(e -> {
            switch (e.getCode()) {
            case LEFT:
                this.controller.setMoveLeft(true);
                break;
            case RIGHT:
                this.controller.setMoveRight(true);
                break;
            case ESCAPE:
                this.state.setStatus(GameStatus.MENU);
                if (GameStateImpl.isStandardModeStart()) {
                    GameStateImpl.setStandardModeStart(false);
                }
                break;
            case SPACE:
                if (this.state.getStatus().equals(GameStatus.PAUSE)) {
                    this.state.setStatus(GameStatus.RUNNING);
                }
                break;
            default:
                break;
            }
        });
    }

    /**
     * Change player status based on the key released.
     */
    private void stoppedPressing() {
        this.canvas.setOnKeyReleased(e -> {
            switch (e.getCode()) {
            case LEFT:
                this.controller.setMoveLeft(false);
                break;
            case RIGHT:
                this.controller.setMoveRight(false);
                break;
            default:
                break;
            }
        });
    }
}
