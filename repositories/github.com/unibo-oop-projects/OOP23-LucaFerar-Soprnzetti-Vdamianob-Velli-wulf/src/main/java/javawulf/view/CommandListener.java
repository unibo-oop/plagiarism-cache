package javawulf.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javawulf.controller.PlayerController;

/**
 * CommandListener is a class whose purpose is to communicate
 * to the controller when a key related to the game has been pressed
 * or released.
 */
public final class CommandListener implements KeyListener {

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private boolean attack;
    private final PlayerController controller;
    private boolean isMoving;

    /**
     * Creates a new CommandListener.
     * 
     * @param controller The controller that gets updated by the inputs
     */
    public CommandListener(final PlayerController controller) {
        this.controller = controller;
    }

    @Override
    public void keyTyped(final KeyEvent e) {
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        this.isMoving = true;
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_COMMA) {
            this.attack = true;
        }
        communicateToController();
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        this.isMoving = false;
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_COMMA) {
            this.attack = false;
        }
        communicateToController();
    }

    private void communicateToController() {
        this.controller.updatePlayerStatus(up, down, left, right);
        this.controller.updateSwordStatus(attack);
    }

    /**
     * @return True if the Player is moving
     */
    public boolean isMoving() {
        return this.isMoving;
    }

}
