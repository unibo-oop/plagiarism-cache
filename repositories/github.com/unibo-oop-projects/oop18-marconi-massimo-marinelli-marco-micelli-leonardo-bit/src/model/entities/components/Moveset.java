package model.entities.components;

import java.util.Map;

import com.almasb.fxgl.texture.AnimationChannel;

/**
 * Models the set of animations the game entity can perform.
 */
public interface Moveset {

    /**
     * Adds the animation to the Moveset.
     * @param name
     *                  the animation name.
     * @param animation
     *                  a new animation.
     */
    void addAnimation(String name, AnimationChannel animation);

    /**
     * @return
     *          all the animation the entity can perform.
     */
    Map<String, AnimationChannel> getMoveset();

    /**
     * Adds an entire Moveset to this moveset.
     * 
     * @param moveset
     *                  the moveset to be added to this moveset.
     */
    void addMoveset(Moveset moveset);

    /**
     * @param name
     *                  the animation name.
     * @return
     *                  a specific animation.
     */
    AnimationChannel getAnimation(String name);
}
