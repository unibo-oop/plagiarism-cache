package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Bar;
import utility.Direction;

/**
 * @author Simone
 *
 */
public class InputListener implements KeyListener, Input {
    private final Bar bar;
    private final int up;
    private final int down;
    private Direction lastDirection = Direction.STOP;

    /**
     * @param up **the int KeyEvent to go up**
     * @param down **the int KeyEvent to go down**
     * @param bar **the bar to move**
     */
    public InputListener(final int up, final int down, final Bar bar) {
        this.bar = bar;
        this.up = up;
        this.down = down;
    }

    /**
     * it saves last Direction if the right button is typed.
     */
    @Override
    public void keyTyped(final KeyEvent e) { 
        final int key = e.getKeyCode();
        if (key == this.up) {
            this.lastDirection = Direction.UP;
        } else if (key == this.down) {
            this.lastDirection = Direction.DOWN;
        }
    }

    /**
     * it saves last Direction if the right button is pressed.
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        this.keyTyped(e);
    }

    /**
     * it forgets last Direction if the right button is released.
     */
    @Override
    public void keyReleased(final KeyEvent e) {
        final int key = e.getKeyCode();
        if (key == this.up && this.lastDirection == Direction.UP 
                || key == this.down && this.lastDirection == Direction.DOWN) {
            this.lastDirection = Direction.STOP;
        } 
    }

    /**
     * call bar move method using lastDirection.
     */
    @Override
    public void moving() {
        InputListener.this.bar.move(this.lastDirection);

    }

}
