package model.game.settings;

import javafx.util.Duration;

/**
 * An object of this class provides informations regarding the animation's frames specifics.
 */
public final class AnimationsFrameSpecifics {

    private static final int FRAMES_PER_ROW = 8;
    private static final int FRAME_HEIGHT = 64;
    private static final int FRAME_WIDTH = 64;
    private static final Duration ANIM_DURATION = Duration.seconds(1);

    /**
     * @return the frames per row in the spritesheet.
     */
    public int getFramesPerRow() {
        return FRAMES_PER_ROW;
    }

    /**
     * @return the frame height.
     */
    public int getFrameHeight() {
        return FRAME_HEIGHT;
    }

    /**
     * @return the frame width.
     */
    public int getFrameWidth() {
        return FRAME_WIDTH;
    }

    /**
     * @return the animation's duration.
     */
    public Duration getAnimDuration() {
        return ANIM_DURATION;
    }
}
