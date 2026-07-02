package it.unibo.model;

import java.util.HashSet;

import it.unibo.model.interfaces.KeyboardModelInterface;

/**
 * The KeyboardModel class represents the state and behavior of a keyboard.
 * It keeps track of the keys that are currently pressed and provides methods to
 * simulate key presses and releases. 
 */
public class KeyboardModel implements KeyboardModelInterface {
    /**
     * A set of currently pressed keys. Each key is represented by its integer code.
     * A HashSet is used for efficient lookup and removal of keys.
     */
    private final HashSet<Integer> pressedKeys;

    /**
     * Constructor initializing the set of pressed keys to an empty set.
     * This represents the initial state where no keys are pressed.
     */
    public KeyboardModel() {
        this.pressedKeys = new HashSet<>();
    }

    /**
     * Simulates the pressing of a key by adding the key's integer code to the pressedKeys set.
     * 
     * @param key the integer code of the key being pressed.
     */
    @Override
    public void keyDown(Integer key) {
        this.pressedKeys.add(key);
    }

    /**
     * Simulates the release of a key by removing the key's integer code from the pressedKeys set.
     * 
     * @param key the integer code of the key being released.
     */
    @Override
    public void keyUp(Integer key) {
        this.pressedKeys.remove(key);
    }

    /**
     * Checks if a specific key is currently pressed.
     * 
     * @param key the integer code of the key to check.
     * @return true if the key is pressed, false otherwise.
     */
    @Override
    public boolean isKeyPressed(Integer key) {
        return this.pressedKeys.contains(key);
    }
}
