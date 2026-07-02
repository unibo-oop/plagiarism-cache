package it.unibo.oop.crossline.io.keyboard;

import com.badlogic.gdx.Input.Keys;

import it.unibo.oop.crossline.debug.Debugger;
import it.unibo.oop.crossline.io.Button;

/**
 * The class of KeyboardButton.
 */
public class KeyboardButton extends Button implements KeyboardObserver {

    /**
     * Constructor of keyboard button.
     * 
     * @param keyCode the keyCode
     * @param name    the name
     * @param action  the action
     */
    public KeyboardButton(final int keyCode, final String name, final Runnable action) {
        super(keyCode, name, action);
    }

    /**
     * This method understand which key is pressed. And execute the main function of
     * is mapped action
     * 
     * @param keyCode the keyCode
     */
    @Override
    public final boolean update(final int keyCode) {
        if (this.getKeyCode() == keyCode) {
            printKeyPressed(keyCode);
            this.getAction().run();
            return true;
        }
        return false;
    }

    /**
     * Called for print the key pressed.
     * 
     * @param keyCode button key code
     */
    private void printKeyPressed(final int keyCode) {
        Debugger.getInstance().printMessage("Key pressed: " + Keys.toString(keyCode) + " (" + keyCode + ")");
    }
}
