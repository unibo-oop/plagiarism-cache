package it.unibo.javajump.view.graphics;

import java.awt.Font;
import java.awt.image.BufferedImage;

/**
 * The interface that describes Game graphics operations.
 */
public interface GameGraphics {
    /**
     * Gets game font 1.
     *
     * @return the game font 1
     */
    Font getGameFont1();

    /**
     * Gets game font 2.
     *
     * @return the game font 2
     */
    Font getGameFont2();

    /**
     * Gets game font 3.
     *
     * @return the game font 3
     */
    Font getGameFont3();

    /**
     * Gets player sheet.
     *
     * @return the player sheet
     */
    BufferedImage getPlayerSheet();

    /**
     * Gets "background easy" image.
     *
     * @return the image
     */
    BufferedImage getBackgroundEasy();

    /**
     * Gets "clouds easy" image.
     *
     * @return the image
     */
    BufferedImage getCloudsEasy();

    /**
     * Gets "background medium" image.
     *
     * @return the image
     */
    BufferedImage getBackgroundMedium();

    /**
     * Gets "clouds medium" image.
     *
     * @return the image
     */
    BufferedImage getCloudsMedium();

    /**
     * Gets "background difficult" image.
     *
     * @return the image
     */
    BufferedImage getBackgroundDifficult();

    /**
     * Gets "clouds difficult" image.
     *
     * @return the image
     */
    BufferedImage getCloudsDifficult();

    /**
     * Gets coin sheet.
     *
     * @return the coin sheet
     */
    BufferedImage getCoinSheet();

    /**
     * Gets "game over" image.
     *
     * @return the image
     */
    BufferedImage getGameOver();

    /**
     * Gets title image.
     *
     * @return the image
     */
    BufferedImage getTitle();

    /**
     * Gets score container.
     *
     * @return the score container image
     */
    BufferedImage getScoreContainer();
}
