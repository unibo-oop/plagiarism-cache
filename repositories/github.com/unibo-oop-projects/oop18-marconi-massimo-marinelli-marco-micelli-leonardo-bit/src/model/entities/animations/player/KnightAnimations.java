package model.entities.animations.player;

import com.almasb.fxgl.texture.AnimationChannel;

import model.entities.animations.AnimationSupplierImpl;
import model.game.settings.AnimationsFrameSpecifics;

/**
 * Provides classes with all the animations a knight can have.
 */
public class KnightAnimations extends AnimationSupplierImpl {

    private static final String TEXTURE = "Knight.png";
    private final AnimationsFrameSpecifics frameSpecs = new AnimationsFrameSpecifics();

    /**
     * Returns an object storing all the possible animations for the Knight class.
     */
    public KnightAnimations() {
        super();

        int start = 42, end = 42;
        super.getAnimations().addAnimation("idle", new AnimationChannel(TEXTURE, frameSpecs.getFramesPerRow(), frameSpecs.getFrameWidth(), frameSpecs.getFrameHeight(), frameSpecs.getAnimDuration(), start, end));

        start = 42; end = 50;
        super.getAnimations().addAnimation("walk_l/r", new AnimationChannel(TEXTURE, frameSpecs.getFramesPerRow(), frameSpecs.getFrameWidth(), frameSpecs.getFrameHeight(), frameSpecs.getAnimDuration(), start, end));

        start = 24; end = 32;
        super.getAnimations().addAnimation("walk_up", new AnimationChannel(TEXTURE, frameSpecs.getFramesPerRow(), frameSpecs.getFrameWidth(), frameSpecs.getFrameHeight(), frameSpecs.getAnimDuration(), start, end));

        start = 33; end = 41;
        super.getAnimations().addAnimation("walk_down", new AnimationChannel(TEXTURE, frameSpecs.getFramesPerRow(), frameSpecs.getFrameWidth(), frameSpecs.getFrameHeight(), frameSpecs.getAnimDuration(), start, end));

        start = 16; end = 23;
        super.getAnimations().addAnimation("attack_l/r", new AnimationChannel(TEXTURE, frameSpecs.getFramesPerRow(), frameSpecs.getFrameWidth(), frameSpecs.getFrameHeight(), frameSpecs.getAnimDuration(), start, end));

        start = 51; end = 56;
        super.getAnimations().addAnimation("die", new AnimationChannel(TEXTURE, frameSpecs.getFramesPerRow(), frameSpecs.getFrameWidth(), frameSpecs.getFrameHeight(), frameSpecs.getAnimDuration(), start, end));
    }
}
