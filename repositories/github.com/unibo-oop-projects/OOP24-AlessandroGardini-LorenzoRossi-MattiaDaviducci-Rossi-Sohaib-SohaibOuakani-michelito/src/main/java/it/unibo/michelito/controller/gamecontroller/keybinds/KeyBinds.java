package it.unibo.michelito.controller.gamecontroller.keybinds;

import java.util.Optional;

import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_SPACE;
import static java.awt.event.KeyEvent.VK_UP;

/**
 * Enum for key binds of the game.
 */
public enum KeyBinds {
    /**
     * Keybind for moving up.
     */
    UP(VK_UP),

    /**
     * Keybind for moving down.
     */
    DOWN(VK_DOWN),

    /**
     * Keybind for moving left.
     */
    LEFT(VK_LEFT),

    /**
     * Keybind for moving right.
     */
    RIGHT(VK_RIGHT),

    /**
     * Keybind for placing a bomb.
     */
    PLACE_BOMB(VK_SPACE);

    private final int keyCode;

    KeyBinds(final int keyCode) {
        this.keyCode = keyCode;
    }

    int getKeyCode() {
        return keyCode;
    }

    /**
     * Method that returns the {@link KeyBinds} associated with the given keyCode.
     * @param keyCode the keyCode.
     * @return the {@link KeyBinds} associated with the given keyCode.
     */
    public static Optional<KeyBinds> getKeyBinds(final int keyCode) {
        for (final KeyBinds keyBinds : values()) {
            if (keyBinds.getKeyCode() == keyCode) {
                return Optional.of(keyBinds);
            }
        }
        return Optional.empty();
    }
}
