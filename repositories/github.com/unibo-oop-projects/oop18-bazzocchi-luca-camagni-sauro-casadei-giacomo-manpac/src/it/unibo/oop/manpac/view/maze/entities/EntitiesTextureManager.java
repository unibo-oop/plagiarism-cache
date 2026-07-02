package it.unibo.oop.manpac.view.maze.entities;

import com.badlogic.gdx.graphics.Texture;

import it.unibo.oop.manpac.utils.Pair;

/**
 * Interface for texture management.
 *
 * @param <X> first argument of pair
 * @param <Y> second argument of pair
 */
public interface EntitiesTextureManager<X, Y> {

    /**
     * Method for obtaining a texture with a pair of two generic.
     * 
     * @param pair The requested pair
     * @return The corresponding texture
     */
    Texture getEntityTexture(Pair<X, Y> pair);

    /**
     * Method for obtaining the texture of a frightened phantom.
     * 
     * @return The corresponding texture
     */
    Texture getFrightenedPhantomTexture();

    /**
     * Method to get the texture of the end of a frightened ghost.
     * 
     * @return The corresponding texture
     */
    Texture getFrightenedEndPhantomTexture();
}
