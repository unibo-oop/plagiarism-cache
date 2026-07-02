package bzzbomber.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import bzzbomber.model.utilities.Input;

/**
 * This class is responsible for manage the inputs of the user. This class use
 * the pattern SINGLETON, for create only one instance of this class.
 * 
 */

public final class KeyManager implements KeyListener {

    private static final KeyManager KEYMANAGER_SINGELTON = new KeyManager();

    private boolean up, down, left, right;
    private boolean space;
    private final List<Input> inputList;

    private KeyManager() {
        this.inputList = new LinkedList<>();
    }

    /**
     * Getter of the singleton.
     * 
     * @return The singleton instance of this class.
     */
    public static KeyManager getKeyManager() {
        return KeyManager.KEYMANAGER_SINGELTON;
    }

    /**
     * Update the list of input, that used to move the @Bomberman .
     * 
     */
    public void update() {
        this.inputList.clear();
        if (this.up) {
            this.inputList.add(Input.UP);
        }
        if (this.down) {
            this.inputList.add(Input.DOWN);
        }
        if (this.left) {
            this.inputList.add(Input.LEFT);
        }
        if (this.right) {
            this.inputList.add(Input.RIGHT);
        }
        if (this.space) {
            this.inputList.add(Input.SPACE);
        }
    }

    /**
     * Method not used.
     */
    @Override
    public void keyTyped(final KeyEvent e) {
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        this.process(e, true);
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        this.process(e, false);
    }

    /**
     * Getter of the current inputs. This method must be called every frame.
     * 
     * @return a defensive copy of the current input's list.
     * 
     */
    public List<Input> getInputList() {
        return Collections.unmodifiableList(this.inputList);
    }

    /**
     * Private method. It can change the status of a key.
     * 
     * @param code
     *            The KeyCode of the key pressed by the user.
     * @param action
     *            The current status of the key
     */
    private void process(final KeyEvent code, final boolean action) {
        if (code.getKeyCode() == KeyEvent.VK_UP || code.getKeyCode() == KeyEvent.VK_W) {
            this.up = action;
        } else if (code.getKeyCode() == KeyEvent.VK_LEFT || code.getKeyCode() == KeyEvent.VK_A) {
            this.left = action;
        } else if (code.getKeyCode() == KeyEvent.VK_DOWN || code.getKeyCode() == KeyEvent.VK_S) {
            this.down = action;
        } else if (code.getKeyCode() == KeyEvent.VK_RIGHT || code.getKeyCode() == KeyEvent.VK_D) {
            this.right = action;
        } else if (code.getKeyCode() == KeyEvent.VK_SPACE) {
            this.space = action;
        }
    }

    /**
     * Method to clear each variable and the input list.
     */
    public void clearAll() {
        this.down = false;
        this.left = false;
        this.right = false;
        this.up = false;
        this.space = false;
        this.inputList.clear();
    }
}
