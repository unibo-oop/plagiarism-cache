package it.unibo.arkanoid.controller;

import java.util.function.Supplier;

/**
 * This class contains game statistics, like lives, score and more.
 *
 */
public class GameStats {
    private final Supplier<Integer> lives;
    private final Supplier<Integer> score;
    private final double worldWidth;
    private final double worldHeight;

    /**
     * Constructors of {@link GameStats}.
     * 
     * @param worldWidth
     *            The width of the world.
     * @param worldHeight
     *            The height of the world.
     * @param scoreSupplier
     *            The supplier of the score.
     * @param livesSupplier
     *            The supplier of the lives of the player.
     */
    public GameStats(final double worldWidth, final double worldHeight, final Supplier<Integer> scoreSupplier,
            final Supplier<Integer> livesSupplier) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.lives = livesSupplier;
        this.score = scoreSupplier;
    }

    /**
     * Return the game's score.
     * 
     * @return The score.
     */
    public int getScore() {
        return this.score.get();
    }

    /**
     * Return the lives count.
     * 
     * @return The number of lives.
     */
    public int getLives() {
        return this.lives.get();
    }

    /**
     * Return the world's width.
     * 
     * @return The world's width.
     */
    public double getWorldWidth() {
        return this.worldWidth;
    }

    /**
     * Return the world's height.
     * 
     * @return The world's height.
     */
    public double getWorldHeight() {
        return this.worldHeight;
    }
}
