package it.unibo.javajump.view.renderers.sub;

import it.unibo.javajump.model.entities.platforms.BouncePlatform;
import it.unibo.javajump.model.entities.platforms.BreakablePlatform;
import it.unibo.javajump.model.entities.platforms.MovingPlatform;
import it.unibo.javajump.model.entities.platforms.Platform;
import it.unibo.javajump.view.sound.sfx.SFXType;
import it.unibo.javajump.view.sound.sfx.SoundEffectsManager;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;

import static it.unibo.javajump.utility.Constants.BOUNCE_PLATFORM_COLOR;
import static it.unibo.javajump.utility.Constants.BREAKABLE_PLATFORM_COLOR;
import static it.unibo.javajump.utility.Constants.MOVING_PLATFORM_COLOR;
import static it.unibo.javajump.utility.Constants.OUTLINE_COLOR;
import static it.unibo.javajump.utility.Constants.PLATFORM_HIGHLIGHT_COLOR;
import static it.unibo.javajump.utility.Constants.STANDARD_PLATFORM_COLOR;

/**
 * Class implementing the PlatformRenderer interface, used to draw a platform.
 */
public class PlatformRendererImpl implements PlatformRenderer {
    /**
     * The width of the outline stroke.
     */
    private final float outlineStrokeWidth;
    /**
     * the width of the rounded corners.
     */
    private final int roundArcW;
    /**
     * the height of the rounded corners.
     */
    private final int roundArcH;

    private final SoundEffectsManager soundEffectsManager;

    /**
     * Class constructor for the PlatformRendererImpl, that initializes the fields for Platform rendering.
     *
     * @param outlineStrokeWidth  the width of the outline stroke
     * @param arcW                the width of the rounded corners
     * @param arcH                the height of the rounded corners
     * @param soundEffectsManager the sound effects manager
     */
    public PlatformRendererImpl(final float outlineStrokeWidth, final int arcW, final int arcH,
                                final SoundEffectsManager soundEffectsManager) {
        this.outlineStrokeWidth = outlineStrokeWidth;
        this.roundArcW = arcW;
        this.roundArcH = arcH;
        this.soundEffectsManager = soundEffectsManager;
    }

    /**
     * {@inheritDoc}
     * The platforms are drawn using the drawPlatformCommon method,
     * the base color of the platform is based on the type of platform,
     * and the highlight color is always the same for all platforms.
     */
    @Override
    public void drawPlatform(final Graphics2D g2, final Platform platform, final float cameraOffsetY) {
        final float drawX = platform.getX();
        final float drawY = platform.getY() - cameraOffsetY;
        final float w = platform.getWidth();
        final float h = platform.getHeight();

        switch (platform) {
            case BouncePlatform ignored -> drawPlatformCommon(g2, drawX, drawY, w, h,
                    Color.decode(BOUNCE_PLATFORM_COLOR), Color.decode(PLATFORM_HIGHLIGHT_COLOR));
            case BreakablePlatform ignored -> drawPlatformCommon(g2, drawX, drawY, w, h,
                    Color.decode(BREAKABLE_PLATFORM_COLOR), Color.decode(PLATFORM_HIGHLIGHT_COLOR));
            case MovingPlatform ignored -> drawPlatformCommon(g2, drawX, drawY, w, h,
                    Color.decode(MOVING_PLATFORM_COLOR), Color.decode(PLATFORM_HIGHLIGHT_COLOR));
            default -> drawPlatformCommon(g2, drawX, drawY, w, h,
                    Color.decode(STANDARD_PLATFORM_COLOR), Color.decode(PLATFORM_HIGHLIGHT_COLOR));
        }

        if (platform.consumeTouched()) {
            if (platform instanceof BouncePlatform) {
                soundEffectsManager.playSound(SFXType.BOUNCE);

            } else if (platform instanceof BreakablePlatform bp) {
                soundEffectsManager.playSound(SFXType.BREAK);

                bp.setFinished();
            } else {
                soundEffectsManager.playSound(SFXType.DEFAULT);

            }
        }
    }

    /**
     * Common drawing method for all Platform type objects.
     * Uses an outline stroke & a vertical gradient for inner color.
     *
     * @param g2         the Graphics2D context
     * @param x          Platform's X position
     * @param y          Platform's Y position
     * @param w          Platform's width
     * @param h          Platform's height
     * @param startColor the starting color for the gradient
     * @param endColor   the ending color for the gradient
     */
    private void drawPlatformCommon(final Graphics2D g2, final float x, final float y, final float w,
                                    final float h, final Color startColor, final Color endColor) {
        final Paint oldPaint = g2.getPaint();
        final Stroke oldStroke = g2.getStroke();

        final GradientPaint gp = new GradientPaint(x, y, startColor, x, y + h, endColor);
        g2.setPaint(gp);
        g2.fillRoundRect((int) x, (int) y, (int) w, (int) h, roundArcW, roundArcH);

        g2.setStroke(new BasicStroke(outlineStrokeWidth));
        g2.setColor(Color.decode(OUTLINE_COLOR));
        g2.drawRoundRect((int) x, (int) y, (int) w, (int) h, roundArcW, roundArcH);

        g2.setStroke(oldStroke);
        g2.setPaint(oldPaint);
    }
}
