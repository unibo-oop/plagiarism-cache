package it.unibo.michelito.view.gameview.panel.impl;

import it.unibo.michelito.view.gameview.panel.api.InputHandler;

import java.awt.event.KeyEvent;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of {@link InputHandler}.
 * It keeps track of the keys pressed by the user.
 */
public final class InputHandlerImpl implements InputHandler, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Set<Integer> keysPressed = new HashSet<>();

    @Override
    public synchronized Set<Integer> keysPressed() {
        return Set.copyOf(this.keysPressed);
    }

    @Override
    public void keyTyped(final KeyEvent e) {
    }

    @Override
    public synchronized void keyPressed(final KeyEvent e) {
        this.keysPressed.add(e.getKeyCode());
    }

    @Override
    public synchronized void keyReleased(final KeyEvent e) {
        this.keysPressed.remove(e.getKeyCode());
    }
}
