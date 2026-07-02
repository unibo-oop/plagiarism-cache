package it.unibo.scat.view.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.unibo.scat.common.Direction;
import it.unibo.scat.control.api.ControlInterface;
import it.unibo.scat.view.game.api.GamePanelInterface;

/**
 * Custom KeyListener for the GamePanel.
 */
public class GameKL implements KeyListener {
    private final ControlInterface controlInterface;
    private final GamePanelInterface gamePanelInterface;

    /**
     * Constructs a new KeyListener to handle user input.
     * 
     * @param controlInterface   the controller responsible for processing game
     *                           commands (e.g., movement, shooting).
     * @param gamePanelInterface the view component associated with this input
     *                           listener.
     */
    public GameKL(final ControlInterface controlInterface, final GamePanelInterface gamePanelInterface) {
        this.controlInterface = controlInterface;
        this.gamePanelInterface = gamePanelInterface;
    }

    /**
     * Action if the key is typed (no action needed).
     * 
     * @param e the event.
     */
    @Override
    public void keyTyped(final KeyEvent e) {

    }

    /**
     * Action if the key is pressed: moves the player, shoots and goes to pause
     * panel if the respective key is pressed.
     * 
     * @param e the key event.
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        final int eventCode = e.getKeyCode();

        switch (eventCode) {
            case KeyEvent.VK_SPACE:
                controlInterface.notifyPlayerShot();
                break;

            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                controlInterface.notifyPlayerMovement(Direction.LEFT);
                break;

            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                controlInterface.notifyPlayerMovement(Direction.RIGHT);
                break;

            case KeyEvent.VK_ESCAPE:
                controlInterface.notifyPauseGame();
                gamePanelInterface.pause();
                break;

            default:
                break;
        }
    }

    /**
     * Action performed when a key is released (no action needed).
     * 
     * @param e the key event.
     */
    @Override
    public void keyReleased(final KeyEvent e) {

    }
}
