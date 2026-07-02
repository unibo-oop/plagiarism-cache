package it.unibo.javajump.controller.input;

import it.unibo.javajump.utility.Constants;

import java.awt.event.KeyEvent;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static it.unibo.javajump.utility.Constants.IMAGE_IMPORT_ERROR_TEXT;

/**
 * Implementation of the InputManager interface, this class is used to manage the input of the user.
 */
public class InputManagerImpl implements InputManager {
    /**
     * Flag to check if the user is pressing the left arrow key.
     */
    private boolean pressingLeft;

    /**
     * Flag to check if the user is pressing the right arrow key.
     */
    private boolean pressingRight;

    /**
     * Queue to store the actions of the user.
     */
    private final Queue<GameAction> actionQueue = new ConcurrentLinkedQueue<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> pressingLeft = true;
            case KeyEvent.VK_RIGHT -> pressingRight = true;
            case KeyEvent.VK_ENTER -> {
                if (!actionQueue.offer(GameAction.CONFIRM_SELECTION)) {
                    throw new IllegalStateException(IMAGE_IMPORT_ERROR_TEXT + GameAction.CONFIRM_SELECTION);
                }

            }

            case KeyEvent.VK_UP -> {
                if (!actionQueue.offer(GameAction.MOVE_MENU_UP)) {
                    throw new IllegalStateException(IMAGE_IMPORT_ERROR_TEXT + GameAction.MOVE_MENU_UP);
                }
            }
            case KeyEvent.VK_DOWN -> {
                if (!actionQueue.offer(GameAction.MOVE_MENU_DOWN)) {
                    throw new IllegalStateException(IMAGE_IMPORT_ERROR_TEXT + GameAction.MOVE_MENU_DOWN);
                }
            }
            case KeyEvent.VK_ESCAPE -> {
                if (!actionQueue.offer(GameAction.PAUSE_GAME)) {
                    throw new IllegalStateException(IMAGE_IMPORT_ERROR_TEXT + GameAction.PAUSE_GAME);
                }
            }
            default -> {
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyReleased(final KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> pressingLeft = false;
            case KeyEvent.VK_RIGHT -> pressingRight = false;
            default -> {
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyTyped(final KeyEvent e) {
        // Not used
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHorizontalDirection() {
        if (pressingLeft && pressingRight) {
            return Constants.NULL_DIRECTION;
        } else if (pressingLeft) {
            return Constants.LEFT_DIRECTION;
        } else if (pressingRight) {
            return Constants.RIGHT_DIRECTION;
        }
        return Constants.NULL_DIRECTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameAction getAction() {
        return actionQueue.poll();
    }
}
