package frogger.common.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import frogger.controller.GameController;

/**
 * Handles keyboard input for the game, mapping key events to game commands.
 */
public class KeyInput implements KeyListener {

    /** Reference to the main game controller. */
    private final GameController controller;

    /**
     * Constructs a new KeyInput handler with the specified game controller.
     * The passed GameController reference is stored and may be modified externally.
     *
     * @param controller the game controller to notify of input commands
     */
    @SuppressFBWarnings(value = "EI2", justification = "GameController is managed externally and this is intentional")
    public KeyInput(final GameController controller) {
        this.controller = controller;
    }

    /**
     * {@inheritDoc}
     * Not used in this implementation.
     */
    @Override
    public void keyTyped(final KeyEvent e) { }

    /**
     * {@inheritDoc}
     * Handles key press events and notifies the input controller of the corresponding command,
     * if the player is not dead.
     *
     * @param e the key event
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        if (!this.controller.getGame().getPlayer().isDead()) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP -> this.controller.getInputController().notifyCommand(new MoveUp());
                case KeyEvent.VK_DOWN -> this.controller.getInputController().notifyCommand(new MoveDown());
                case KeyEvent.VK_RIGHT -> this.controller.getInputController().notifyCommand(new MoveRight());
                case KeyEvent.VK_LEFT -> this.controller.getInputController().notifyCommand(new MoveLeft());
                case KeyEvent.VK_ESCAPE -> this.controller.getInputController().notifyCommand(new Pause());
                default -> {
                    // Handle other keys if needed
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     * Not used in this implementation.
     */
    @Override
    public void keyReleased(final KeyEvent e) { }
}
