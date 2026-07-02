package it.unibo.bmbman.controller.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import it.unibo.bmbman.model.utilities.Direction;

/**
 * Used to move the hero in his adventure.
 */
public class KeyInput implements KeyListener {
    private final GameController gc;
    /**
     * KeyInput.
     * @param gc {@link GameController}
     */
    public KeyInput(final GameController gc) {
        this.gc = gc;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
            gc.getHero().setDirection(Direction.UP);
            break;
        case KeyEvent.VK_DOWN: 
            gc.getHero().setDirection(Direction.DOWN);
            break;
        case KeyEvent.VK_LEFT: 
            gc.getHero().setDirection(Direction.LEFT);
            break;
        case KeyEvent.VK_RIGHT:
            gc.getHero().setDirection(Direction.RIGHT);
            break;
        case KeyEvent.VK_P:
            gc.pause();
            break;
        case KeyEvent.VK_SPACE:
            gc.addBomb();
            break;
        default:
            gc.getHero().setDirection(Direction.IDLE);
            break;
        }

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void keyReleased(final KeyEvent e) {
        gc.getHero().setDirection(Direction.IDLE);
    }

    @Override
    public void keyTyped(final KeyEvent arg0) {
    }

}
