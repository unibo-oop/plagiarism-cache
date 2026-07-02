package it.unibo.plantsfarm.model.animation;

import java.awt.image.BufferedImage;
import java.util.List;

import it.unibo.plantsfarm.model.animation.api.Animation;
import it.unibo.plantsfarm.model.animation.api.AnimationFrames;

/**
 *  Class created for implements all animation about the player movement.
 */
public final class AnimationCorsa implements Animation {
    private final List<BufferedImage> frames;
    private int frameIndex;
    private long lastFrameTimeNs;
    private final long frameDurationNs;
    private boolean playing;

    /**
     * Create a movement animation for the player.
     *
     * @param frameDurationNs duration animation.
     * @param frames the sequence of frame used for the animation.
     */
    public AnimationCorsa(final long frameDurationNs, final List<BufferedImage> frames) {
        this.frameDurationNs = frameDurationNs;
        this.frames = List.copyOf(frames);
    }

    @Override
    public void start(final long nowNs) {
        if (playing) {
            return;
        }
        playing = true;
        frameIndex = 0;
        lastFrameTimeNs = nowNs;
    }

    @Override
    public BufferedImage getCurrentFrame(final long nowNs) {
        if (!playing) {
            return AnimationFrames.base();
        }

        if (nowNs - lastFrameTimeNs >= frameDurationNs) {
            frameIndex = (frameIndex + 1) % frames.size();
            lastFrameTimeNs = nowNs;
        }
        return frames.get(frameIndex);
    }

    @Override
    public boolean isPlaying() {
        return playing;
    }

}
