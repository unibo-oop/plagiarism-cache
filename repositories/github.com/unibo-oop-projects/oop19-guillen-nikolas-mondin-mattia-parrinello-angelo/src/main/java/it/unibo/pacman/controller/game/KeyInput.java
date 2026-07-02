package it.unibo.pacman.controller.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.unibo.pacman.controller.entities.PacmanController;
import it.unibo.pacman.model.utilities.Direction;

/**
 * Used to get the keyboard input for Pacman's movement.
 */
public class KeyInput implements KeyListener {
    private final PacmanController pacman;
    public KeyInput(final PacmanController pacman) {
        this.pacman = pacman;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_W:
        case KeyEvent.VK_UP:
            pacman.setPreferredDirection(Direction.UP);
            break;
        case KeyEvent.VK_S:
        case KeyEvent.VK_DOWN:
            pacman.setPreferredDirection(Direction.DOWN);
            break;
        case KeyEvent.VK_A:
        case KeyEvent.VK_LEFT:
            pacman.setPreferredDirection(Direction.LEFT);
            break;
        case KeyEvent.VK_D:
        case KeyEvent.VK_RIGHT:
            pacman.setPreferredDirection(Direction.RIGHT);
            break;
        default:
            break;
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void keyTyped(final KeyEvent e) {
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void keyReleased(final KeyEvent e) {
    }
}
