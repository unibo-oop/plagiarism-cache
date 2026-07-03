package it.oop.project.view;

import javax.swing.JLabel;

/**
 * A factory for 2048 animations.
 *
 */
public interface AnimationFactory {

    /**
     * Creates a thread that applies a spawn animation for the tile provided.
     * 
     * @param tile
     *            the tile where applying the spawn animation.
     * @return a thread that applies a spawn animation for the tile provided.
     */
    Thread createSpawnAnimation(final JLabel tile);

    /**
     * Creates a thread that applies a fusion animation for the tile provided.
     * 
     * @param tile
     *            the tile where applying the fusion animation.
     * @return a thread that applies a fusion animation for the tile provided.
     */
    Thread createFusionAnimation(final JLabel tile);

}
