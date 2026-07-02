package it.unibo.javajump.view.graphics;

import javax.imageio.ImageIO;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static it.unibo.javajump.utility.Constants.RESOURCES_BACKGROUND_EASY;
import static it.unibo.javajump.utility.Constants.RESOURCES_BACKGROUND_HARD;
import static it.unibo.javajump.utility.Constants.RESOURCES_BACKGROUND_MEDIUM;
import static it.unibo.javajump.utility.Constants.RESOURCES_CLOUDS_EASY;
import static it.unibo.javajump.utility.Constants.RESOURCES_CLOUDS_HARD;
import static it.unibo.javajump.utility.Constants.RESOURCES_CLOUDS_MEDIUM;
import static it.unibo.javajump.utility.Constants.RESOURCES_COIN;
import static it.unibo.javajump.utility.Constants.RESOURCES_FONT_1;
import static it.unibo.javajump.utility.Constants.RESOURCES_FONT_2;
import static it.unibo.javajump.utility.Constants.RESOURCES_FONT_3;
import static it.unibo.javajump.utility.Constants.RESOURCES_GAMEOVER;
import static it.unibo.javajump.utility.Constants.RESOURCES_PLAYER;
import static it.unibo.javajump.utility.Constants.RESOURCES_SCORE_CONTAINER;
import static it.unibo.javajump.utility.Constants.RESOURCES_TITLE;
import static it.unibo.javajump.utility.Constants.SIZE_FONT_1;
import static it.unibo.javajump.utility.Constants.SIZE_FONT_2;
import static it.unibo.javajump.utility.Constants.SIZE_FONT_3;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

/**
 * The implementation of the GameGraphics interface.
 */
public final class GameGraphicsImpl implements GameGraphics {
    private final Font gameFont1;
    private final Font gameFont2;
    private final Font gameFont3;
    private final BufferedImage playerSheet;
    private final BufferedImage backgroundEasy;
    private final BufferedImage cloudsEasy;
    private final BufferedImage backgroundMedium;
    private final BufferedImage cloudsMedium;
    private final BufferedImage backgroundDifficult;
    private final BufferedImage cloudsDifficult;
    private final BufferedImage scoreContainer;
    private final BufferedImage coinSheet;
    private final BufferedImage gameOver;
    private final BufferedImage title;

    /**
     * Instantiates a new Game graphics.
     */
    public GameGraphicsImpl() {
        try {
            title = loadImage(RESOURCES_TITLE);
            gameOver = loadImage(RESOURCES_GAMEOVER);
            playerSheet = loadImage(RESOURCES_PLAYER);
            coinSheet = loadImage(RESOURCES_COIN);
            backgroundEasy = loadImage(RESOURCES_BACKGROUND_EASY);
            cloudsEasy = loadImage(RESOURCES_CLOUDS_EASY);
            backgroundMedium = loadImage(RESOURCES_BACKGROUND_MEDIUM);
            cloudsMedium = loadImage(RESOURCES_CLOUDS_MEDIUM);
            backgroundDifficult = loadImage(RESOURCES_BACKGROUND_HARD);
            cloudsDifficult = loadImage(RESOURCES_CLOUDS_HARD);
            scoreContainer = loadImage(RESOURCES_SCORE_CONTAINER);
            gameFont1 = loadFont(RESOURCES_FONT_1, SIZE_FONT_1);
            gameFont2 = loadFont(RESOURCES_FONT_2, SIZE_FONT_2);
            gameFont3 = loadFont(RESOURCES_FONT_3, SIZE_FONT_3);
        } catch (IOException | FontFormatException ex) {
            throw new IllegalStateException("Error loading game resources", ex);
        }
    }

    /**
     * Utility method to load images using getResourceAsStream.
     *
     * @param path the path
     * @return the loaded BufferedImage
     */
    private BufferedImage loadImage(final String path) throws IOException {
        try (InputStream is = getClass().getResourceAsStream(path)) {
            if (is == null) {
                throw new IOException("Resource not found: " + path);
            }
            return ImageIO.read(is);
        }
    }

    /**
     * Utility method to load fonts using getResourceAsStream.
     *
     * @param path the path
     * @param size the font desired size
     * @return the loaded Font
     */
    private Font loadFont(final String path, final float size) throws IOException, FontFormatException {
        try (InputStream is = getClass().getResourceAsStream(path)) {
            if (is == null) {
                throw new IOException("Font not found: " + path);
            }
            return Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(size);
        }
    }

    private BufferedImage copyImage(final BufferedImage source) {
        if (source == null) {
            return null;
        }
        final BufferedImage copy = new BufferedImage(source.getWidth(), source.getHeight(), TYPE_INT_ARGB);
        final Graphics2D g2d = copy.createGraphics();
        g2d.drawImage(source, 0, 0, null);
        g2d.dispose();
        return copy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Font getGameFont1() {
        return gameFont1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Font getGameFont2() {
        return gameFont2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Font getGameFont3() {
        return gameFont3;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedImage getPlayerSheet() {
        return copyImage(playerSheet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedImage getBackgroundEasy() {
        return copyImage(backgroundEasy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedImage getCloudsEasy() {
        return copyImage(cloudsEasy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedImage getBackgroundMedium() {
        return copyImage(backgroundMedium);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedImage getCloudsMedium() {
        return copyImage(cloudsMedium);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedImage getBackgroundDifficult() {
        return copyImage(backgroundDifficult);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedImage getCloudsDifficult() {
        return copyImage(cloudsDifficult);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedImage getCoinSheet() {
        return copyImage(coinSheet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedImage getGameOver() {
        return copyImage(gameOver);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedImage getTitle() {
        return copyImage(title);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedImage getScoreContainer() {
        return copyImage(scoreContainer);
    }
}
