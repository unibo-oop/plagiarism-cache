package it.unibo.javajump.view.renderers.sub;

import it.unibo.javajump.model.entities.character.Character;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import static it.unibo.javajump.utility.Constants.FLIP_MAX;
import static it.unibo.javajump.utility.Constants.FLIP_MIN;
import static it.unibo.javajump.utility.Constants.PLAYER_ANIMATION_CYCLE_DURATION;
import static it.unibo.javajump.utility.Constants.PLAYER_JUMP_END_FRAME;
import static it.unibo.javajump.utility.Constants.PLAYER_JUMP_START_FRAME;
import static it.unibo.javajump.utility.Constants.PLAYER_LANDING_END_FRAME;
import static it.unibo.javajump.utility.Constants.PLAYER_LANDING_START_FRAME;
import static it.unibo.javajump.utility.Constants.RENDER_PLAYER_ANIM_TIMER_INIT;
import static it.unibo.javajump.utility.Constants.RENDER_PLAYER_FRAME_GET_IMG_Y;
import static it.unibo.javajump.utility.Constants.RENDER_PLAYER_FRAME_X;
import static it.unibo.javajump.utility.Constants.RENDER_PLAYER_FRAME_Y;

/**
 * Implementation of the PlayerRenderer interface, used for graphical rendering of the player (animated).
 */
public class PlayerRendererImpl implements PlayerRenderer {
    /**
     * The sprite sheet containing the player's animation frames.
     */
    private final BufferedImage playerSheet;
    /**
     * The width of the player's animation frames.
     */
    private final int frameWidth;
    /**
     * The height of the player's animation frames.
     */
    private final int frameHeight;
    /**
     * The duration of each animation frame.
     */
    private final float frameDuration;
    /**
     * Value to cycle in the desired frames.
     */
    private float animTimer;
    /**
     * Flag to memorize if the player has been on a Platform.
     */
    private boolean prevOnPlatform;

    /**
     * Constructor for the PlayerRendererImpl class.
     *
     * @param sheet         the sprite sheet containing the player's animation frames
     * @param frameWidth    the width of the player's animation frames
     * @param frameHeight   the height of the player's animation frames
     * @param frameDuration the duration of each animation frame
     */
    public PlayerRendererImpl(final BufferedImage sheet, final int frameWidth,
                              final int frameHeight, final float frameDuration) {
        this.playerSheet = new BufferedImage(sheet.getColorModel(),
                sheet.copyData(null),
                sheet.isAlphaPremultiplied(),
                null);
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.prevOnPlatform = false;
        this.animTimer = RENDER_PLAYER_ANIM_TIMER_INIT;
        this.frameDuration = frameDuration;

    }

    /**
     * {@inheritDoc}
     * The method checks the state of the Character (in model) and updates the animation timer accordingly.
     * The draw logic also uses the private methods to draw the correct frame and to flip the sheet.
     *
     * @param g2        the Graphics2D context
     * @param player    the Player to draw
     * @param offsetY   the vertical offset
     * @param deltaTime the time passed since the last frame (used for animation)
     */
    @Override
    public void drawPlayer(final Graphics2D g2, final Character player, final float offsetY, final float deltaTime) {
        if (player.isOnPlatform() != prevOnPlatform) {
            animTimer = RENDER_PLAYER_ANIM_TIMER_INIT;
            prevOnPlatform = player.isOnPlatform();
        } else {
            animTimer += deltaTime;
        }

        final int sx = getAnimationFrame(player);
        final BufferedImage frame = playerSheet.getSubimage(sx, RENDER_PLAYER_FRAME_GET_IMG_Y, frameWidth, frameHeight);

        final float drawX = player.getX();
        final float drawY = player.getY() - offsetY;

        flipSheet(g2, player, drawX, drawY, frame);
    }

    /**
     * Private method that returns the index of the frame to draw.
     * The animation frames are extracted from the sprite sheet.
     * The sheet contains a single row of frames,
     * with the first 2 frames for jumping and the last 2 for falling.
     * The animation logic for the character is:
     * if the character lands on a platform, the first 2 frames get drawn,
     * otherwise it draws the third frame and the fourth after, which will remain
     * until the character lands on another platform.
     *
     * @param player the player to check
     * @return the right frame to draw
     */
    private int getAnimationFrame(final Character player) {
        final int frameIndex;
        if (player.isOnPlatform()) {
            final float cycle = frameDuration * PLAYER_ANIMATION_CYCLE_DURATION; //
            final float t = animTimer % cycle;
            frameIndex = (t < frameDuration) ? PLAYER_LANDING_START_FRAME : PLAYER_LANDING_END_FRAME;
        } else {
            frameIndex = (animTimer < frameDuration) ? PLAYER_JUMP_START_FRAME : PLAYER_JUMP_END_FRAME;
        }
        return frameIndex * frameWidth;
    }

    /**
     * Private method that flips the sprite sheet horizontally if the player is facing left.
     *
     * @param g2     the Graphics2D context
     * @param player the player to check
     * @param drawX  the x position to draw
     * @param drawY  the y position to draw
     * @param frame  the frame to draw
     */
    private void flipSheet(final Graphics2D g2, final Character player, final float drawX,
                           final float drawY, final BufferedImage frame) {
        final AffineTransform old = g2.getTransform();
        if (!player.isFacingRight()) {
            g2.translate(drawX + frameWidth, drawY);
            g2.scale(FLIP_MIN, FLIP_MAX);
            g2.drawImage(frame, RENDER_PLAYER_FRAME_X, RENDER_PLAYER_FRAME_Y, null);
        } else {
            g2.drawImage(frame, (int) drawX, (int) drawY, null);
        }
        g2.setTransform(old);
    }
}
