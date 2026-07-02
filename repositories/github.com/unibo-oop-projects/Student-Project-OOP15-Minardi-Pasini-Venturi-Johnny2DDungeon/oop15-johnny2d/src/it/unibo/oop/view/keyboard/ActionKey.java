package it.unibo.oop.view.keyboard;

import java.awt.event.KeyEvent;
import it.unibo.oop.utilities.Action;

/**
 * Interface for action keys i.e. not for hero movement.
 */
public enum ActionKey implements Key {

    /**
     * Shooting action of main character.
     */
    SHOOT(KeyEvent.VK_SPACE, Action.SHOOT),

    /**
     * To pause the game.
     */
    PAUSE(KeyEvent.VK_ESCAPE, Action.PAUSE),

    /**
     * When no actions have been performed.
     */
    NONE(KeyEvent.VK_UNDEFINED, Action.NONE);

    private final int vkCode;
    private final Action action;

    ActionKey(final int vkCode, final Action action) {
        this.vkCode = vkCode;
        this.action = action;
    }

    @Override
    public int getVkCode() {
        return this.vkCode;
    }

    /**
     * @return action.
     */
    public Action getAction() {
        return this.action;
    }
}