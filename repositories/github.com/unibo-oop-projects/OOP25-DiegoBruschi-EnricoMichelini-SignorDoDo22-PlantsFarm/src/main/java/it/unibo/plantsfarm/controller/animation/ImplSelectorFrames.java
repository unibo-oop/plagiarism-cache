package it.unibo.plantsfarm.controller.animation;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import it.unibo.plantsfarm.controller.animation.api.SelectorFrames;
import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput;
import it.unibo.plantsfarm.model.animation.AnimationAzione;
import it.unibo.plantsfarm.model.animation.AnimationCorsa;
import it.unibo.plantsfarm.model.animation.api.Animation;
import it.unibo.plantsfarm.model.animation.api.AnimationFrames;
import it.unibo.plantsfarm.view.gamepanel.ImplViewGamePanel;
import it.unibo.plantsfarm.view.utility.AnimationTime;

/**
 * Implementation of the class SelectorFrames.
 */
public final class ImplSelectorFrames implements SelectorFrames {

    private final AnimationAzione animationWater = new AnimationAzione(AnimationTime.FRAME_8_FPS, AnimationFrames.water());
    private final AnimationAzione animationHoe = new AnimationAzione(AnimationTime.FRAME_8_FPS, AnimationFrames.dig());
    private final AnimationCorsa animationLeft = new AnimationCorsa(AnimationTime.FRAME_8_FPS, AnimationFrames.walkLeft());
    private final AnimationCorsa animationUp = new AnimationCorsa(AnimationTime.FRAME_8_FPS, AnimationFrames.walkUp());
    private final AnimationCorsa animationDown = new AnimationCorsa(AnimationTime.FRAME_8_FPS, AnimationFrames.walkDown());
    private final AnimationCorsa animationRight = new AnimationCorsa(AnimationTime.FRAME_8_FPS, AnimationFrames.walkRight());
    private final AnimationAzione animationAxe = new AnimationAzione(AnimationTime.FRAME_8_FPS, AnimationFrames.axe());
    private Animation currentAnimation;
    private BufferedImage currentImage = AnimationFrames.base();

    @Override
    public void takeInput(final UserInput input) {
        final long nowNs = System.nanoTime();

        switch (input) {
            case ACTIONHOE -> {
                animationHoe.start(nowNs);
                currentAnimation = animationHoe;
            }
            case ACTIONWATER -> {
                animationWater.start(nowNs);
                currentAnimation = animationWater;
            }
            case UP -> {
                animationUp.start(nowNs);
                currentAnimation = animationUp;
            }
            case RIGHT -> {
                animationRight.start(nowNs);
                currentAnimation = animationRight;
            }
            case DOWN -> {
                animationDown.start(nowNs);
                currentAnimation = animationDown;
            }
            case LEFT -> {
                animationLeft.start(nowNs);
                currentAnimation = animationLeft;
            }
            case REMOVE_PLANT -> {
                animationAxe.start(nowNs);
                currentAnimation = animationAxe;
            }

            case SELECT_SEED -> { }

            case FERMO -> {
                final boolean hoePlaying = animationHoe.equals(currentAnimation) && animationHoe.isPlaying();
                final boolean waterPlaying = animationWater.equals(currentAnimation) && animationWater.isPlaying();
                final boolean axePlaying = animationAxe.equals(currentAnimation) && animationAxe.isPlaying();
                if (hoePlaying || waterPlaying || axePlaying) {
                    return;
                }
                currentAnimation = null;
                currentImage = AnimationFrames.base();
            }
        }
    }

    @Override
    public void update(final Long nowNs) {
        if (currentAnimation != null) {
            currentImage = currentAnimation.getCurrentFrame(nowNs);

            if (!currentAnimation.isPlaying()) {
                currentAnimation = null;
                currentImage = AnimationFrames.base();
            }
        }
    }

    @Override
    public void render(final Graphics2D g2d, final double posPlayerx,
        final double posPlayery, final int camerax, final int cameray
    ) {
        g2d.drawImage(currentImage,
                (int) posPlayerx - camerax,
                (int) posPlayery - cameray,
                ImplViewGamePanel.PLAYER_SIZE,
                ImplViewGamePanel.PLAYER_SIZE,
                null
            );
    }
}
