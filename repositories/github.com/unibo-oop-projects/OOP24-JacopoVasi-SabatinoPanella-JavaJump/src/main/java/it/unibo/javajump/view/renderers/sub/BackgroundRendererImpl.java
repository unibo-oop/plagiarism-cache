package it.unibo.javajump.view.renderers.sub;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.level.spawn.difficulty.DifficultyState;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import static it.unibo.javajump.utility.Constants.BG_EXTRA_TILES_NUMBER;
import static it.unibo.javajump.utility.Constants.BG_HORIZONTAL_NULL_SPEED;
import static it.unibo.javajump.utility.Constants.BG_HORIZONTAL_OFFSET_INIT;
import static it.unibo.javajump.utility.Constants.BG_TRANSITION_TIMER_INIT;
import static it.unibo.javajump.utility.Constants.SCREEN_LEFT_MARGIN;

/**
 * Class that implements the BackgroundRenderer interface, used for graphical rendering of
 * the background.
 */
public class BackgroundRendererImpl implements BackgroundRenderer {
    /**
     * Image tile of the background.
     */
    private final BufferedImage bgTileEasy;
    private final BufferedImage bgTileMedium;
    private final BufferedImage bgTileHard;
    /**
     * Factor for vertical parallax effect.
     */
    private final float parallaxFactor;
    /**
     * Factor for horizontal auto-scrolling effect velocity.
     */
    private final float horizontalSpeed;
    /**
     * Current horizontal offset, used for auto-scrolling.
     */
    private float horizontalOffset;

    private BufferedImage currentBg;
    private BufferedImage targetBg;
    private boolean inTransition;
    private float transitionTimer;
    private final float transitionDuration;

    /**
     * Constructor of the BackgroundRendererImpl class.
     *
     * @param bgTileEasy         background image tile
     * @param bgTileMedium       the bg tile medium
     * @param bgTileHard         the bg tile hard
     * @param parallaxFactor     the factor for vertical parallax effect
     * @param horizontalSpeed    the speed for horizontal auto-scrolling effect.
     *                           If set to 0, the background will not scroll horizontally.
     * @param transitionDuration the transition duration
     */
    public BackgroundRendererImpl(final BufferedImage bgTileEasy, final BufferedImage bgTileMedium,
                                  final BufferedImage bgTileHard, final float parallaxFactor,
                                  final float horizontalSpeed, final float transitionDuration) {
        this.bgTileEasy = copyBufferedImage(bgTileEasy);
        this.bgTileMedium = copyBufferedImage(bgTileMedium);
        this.bgTileHard = copyBufferedImage(bgTileHard);
        this.parallaxFactor = parallaxFactor;
        this.horizontalSpeed = horizontalSpeed;
        this.horizontalOffset = BG_HORIZONTAL_OFFSET_INIT;
        this.transitionDuration = transitionDuration;
        this.currentBg = copyBufferedImage(bgTileEasy);
        this.targetBg = copyBufferedImage(bgTileEasy);
        this.inTransition = false;
        this.transitionTimer = BG_TRANSITION_TIMER_INIT;
    }

    private BufferedImage selectBackground(final DifficultyState diff) {
        return switch (diff) {
            case EASY, MEDIUM -> bgTileEasy;
            case HARD, VERY_HARD -> bgTileMedium;
            case HELL -> bgTileHard;
        };
    }

    private BufferedImage copyBufferedImage(final BufferedImage source) {
        if (source == null) {
            return null;
        }
        final BufferedImage copy = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        copy.getGraphics().drawImage(source, 0, 0, null);
        return copy;
    }

    private void updateTransition(final DifficultyState currentDiff) {
        final BufferedImage newBg = selectBackground(currentDiff);
        if (!newBg.equals(currentBg) && !inTransition) {
            targetBg = newBg;
            inTransition = true;
            transitionTimer = 0;
        }
    }

    /**
     * Private method that updates the horizontal offset automatically, independent
     * of the camera offset. Should be called every frame with the deltaTime.
     * If horizontalSpeed is 0, this method does nothing.
     * If horizontalSpeed is not 0, the horizontal offset is updated based on the
     * horizontalSpeed and the deltaTime, and the offset is kept within the
     * range [0, tileWidth].
     *
     * @param deltaTime time passed since the last update (in seconds)
     */
    private void updateHorizontalOffset(final float deltaTime) {
        if (horizontalSpeed != BG_HORIZONTAL_NULL_SPEED) {
            horizontalOffset += horizontalSpeed * deltaTime;
            final int tileW = currentBg.getWidth();
            if (horizontalOffset >= tileW) {
                horizontalOffset -= tileW;
            } else if (horizontalOffset < BG_HORIZONTAL_OFFSET_INIT) {
                horizontalOffset += tileW;
            }
        }
    }

    /**
     * Draws the specified image in a grid to cover the entire screen.
     *
     * @param g2           the Graphics2D context
     * @param img          the image to draw
     * @param verticalTiles the number of rows of tiles
     * @param horizontalTiles the number of columns of tiles
     * @param shiftX       the horizontal shift
     * @param shiftY       the vertical shift
     * @param tileW        the width of each tile
     * @param tileH        the height of each tile
     */
    private void drawTileGrid(final Graphics2D g2, final BufferedImage img, final int verticalTiles,
                              final int horizontalTiles, final int shiftX, final int shiftY,
                              final int tileW, final int tileH) {
        for (int i = 0; i < verticalTiles; i++) {
            final int drawY = -shiftY + i * tileH;
            for (int j = 0; j < horizontalTiles; j++) {
                final int drawX = -shiftX + j * tileW;
                g2.drawImage(img, drawX, drawY, null);
            }
        }
    }

    /**
     * {@inheritDoc}
     * The implementation uses camera offset (from model) and parallaxFactor to calculate the vertical
     * offset and then uses the updateHorizontalOffset method to update the horizontal
     * offset automatically. It draws the background by looping rows and columns
     * of the bgTile image, and drawing each tile in the correct position, applying offsets.
     */
    @Override
    public void drawBackground(final Graphics2D g2, final GameModel model, final float deltaTime) {
        updateHorizontalOffset(deltaTime);
        updateTransition(model.getDifficultyManager().getCurrentDifficulty());

        final int screenW = model.getScreenWidth();
        final int screenH = model.getScreenHeight();
        final float cameraOffset = model.getCameraManager().getCurrentOffset();
        final float verticalOffset = cameraOffset * parallaxFactor;

        final int tileW = currentBg.getWidth();
        final int tileH = currentBg.getHeight();

        int shiftY = (int) verticalOffset % tileH;
        if (shiftY < SCREEN_LEFT_MARGIN) {
            shiftY += tileH;
        }
        final int shiftX = (int) horizontalOffset;

        final int verticalTiles = (screenH / tileH) + BG_EXTRA_TILES_NUMBER;
        final int horizontalTiles = (screenW / tileW) + BG_EXTRA_TILES_NUMBER;

        if (inTransition) {
            transitionTimer += deltaTime;
            final float alpha = Math.min(transitionTimer / transitionDuration, 1.0f);
            final Composite originalComposite = g2.getComposite();

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1 - alpha));
            drawTileGrid(g2, currentBg, verticalTiles, horizontalTiles, shiftX, shiftY, tileW, tileH);

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            drawTileGrid(g2, targetBg, verticalTiles, horizontalTiles, shiftX, shiftY, tileW, tileH);

            g2.setComposite(originalComposite);

            if (alpha >= 1.0f) {
                currentBg = targetBg;
                inTransition = false;
            }
        } else {
            drawTileGrid(g2, currentBg, verticalTiles, horizontalTiles, shiftX, shiftY, tileW, tileH);
        }
    }
}
