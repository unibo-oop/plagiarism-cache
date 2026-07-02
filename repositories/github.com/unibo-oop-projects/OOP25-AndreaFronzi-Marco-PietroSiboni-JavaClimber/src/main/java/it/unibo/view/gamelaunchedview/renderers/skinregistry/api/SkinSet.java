package it.unibo.view.gamelaunchedview.renderers.skinregistry.api;

import it.unibo.view.utilities.SpriteEnum;

/**
 * Interface representing left and right sprites faces for a character's skin.
 */
public interface SkinSet {

    /**
     * Gets the sprite for the left-facing character.
     * 
     * @return the left-facing sprite
     */
    SpriteEnum left();

    /**
     * Gets the sprite for the right-facing character.
     * 
     * @return the right-facing sprite
     */
    SpriteEnum right();
}
