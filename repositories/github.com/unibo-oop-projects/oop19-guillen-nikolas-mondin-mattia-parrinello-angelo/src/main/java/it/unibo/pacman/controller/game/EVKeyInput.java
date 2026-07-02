package it.unibo.pacman.controller.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;

/**
 * Used to get "Enter" input for saving player score in leaderboard file.
 *
 *
 */
public class EVKeyInput implements KeyListener {
    private final JButton bt;

    public EVKeyInput(final JButton bt) {
        this.bt = bt;
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
    public void keyPressed(final KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            bt.doClick();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyReleased(final KeyEvent e) {
    }
}
