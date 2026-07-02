package it.unibo.plantsfarm.model.animation.api;

import java.awt.image.BufferedImage;

/**
 * This class is used for define the generic Animation and its base methods.
 */
public interface Animation {
    /**
     * this method return the current frame of the player animation.
     *
     * @param now CurrentTime, is used for calculate the next frame based on begin time
     *
     * @return CurrentImage
     */
    BufferedImage getCurrentFrame(long now);

    /**
     * Start animation from time nowNs.
     *
     * @param nowNs time begin
     */
    void start(long nowNs);

    /**
     * Returns the current the state of the animation.
     *
     * @return {@code true} if the player is in action. Return {@code false} if the player is not in action.
     *
     */
    boolean isPlaying();
}
