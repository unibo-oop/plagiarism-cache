package breakout.controller;

import java.util.List;

import breakout.model.physics.GameObject;

/**
 * Handle the audio of the game.
 */
public interface SoundManager {

    /**
     * Play an audio clip for a particular collision.
     * 
     * @param collision
     *            the colliding game object
     */
    void playSound(final GameObject collision);

    /**
     * 
     * @param objects
     *            a list of colliding objects
     */
    default void playAll(final List<GameObject> objects) {
        objects.forEach(object -> this.playSound(object));
    }

}
