package it.unibo.scat.view.game.api;

import java.awt.Image;

import it.unibo.scat.common.EntityType;

/**
 * Interface for the sprite manager. It returns an image based on parameters.
 */
@FunctionalInterface
public interface SpriteManager {

    /**
     * @param type  the type of the entity.
     * @param frame the frame of the entity.
     * @return the correct image.
     * 
     */
    Image getSprite(EntityType type, int frame);
}
