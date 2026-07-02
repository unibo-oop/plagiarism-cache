package controller.event;

import view.utility.SceneType;

/**
 * Event created by pressing a key.
 */
public interface KeyEvent extends Event {

    /**
     * @return the scene when the key was pressed.
     */
    SceneType getGameState();

    /**
     * @return if key was pressed or released.
     */
    KeyType getType(); 
}
