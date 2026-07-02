package model.entities.animations.enemies;

import com.almasb.fxgl.texture.AnimationChannel;

import javafx.util.Duration;
import model.entities.animations.AnimationSupplierImpl;
import model.game.settings.AnimationsFrameSpecifics;

/**
 * Provides classes with all the animations a Skeleton can have.
 */
public class SkeletonAnimations extends AnimationSupplierImpl {

    private static final String TEXTURE = "Skeleton.png";
    private final AnimationsFrameSpecifics frameSpecs = new AnimationsFrameSpecifics();

    /**
     * Returns an object storing all the possible animations for the Skeleton.
     */
    public SkeletonAnimations() {
        super();

        int start = 18, end = 18;
        super.getAnimations().addAnimation("idle", new AnimationChannel(TEXTURE, 7, frameSpecs.getFrameWidth(), frameSpecs.getFrameHeight(), frameSpecs.getAnimDuration(), start, end));

        start = 18; end = 26;
        super.getAnimations().addAnimation("walk_l/r", new AnimationChannel(TEXTURE, 7, frameSpecs.getFrameWidth(), frameSpecs.getFrameHeight(), frameSpecs.getAnimDuration(), start, end));

        start = 0; end = 8;
        super.getAnimations().addAnimation("walk_up", new AnimationChannel(TEXTURE, 7, frameSpecs.getFrameWidth(), frameSpecs.getFrameHeight(), frameSpecs.getAnimDuration(), start, end));

        start = 9; end = 17;
        super.getAnimations().addAnimation("walk_down", new AnimationChannel(TEXTURE, 7, frameSpecs.getFrameWidth(), frameSpecs.getFrameHeight(), frameSpecs.getAnimDuration(), start, end));


        start = 39; end = 44;
        super.getAnimations().addAnimation("attack_l/r", new AnimationChannel(TEXTURE, 7, frameSpecs.getFrameWidth(), frameSpecs.getFrameHeight(), frameSpecs.getAnimDuration(), start, end));

        start = 45; end = 50;
        super.getAnimations().addAnimation("die", new AnimationChannel(TEXTURE, 7, frameSpecs.getFrameWidth(), frameSpecs.getFrameHeight(), frameSpecs.getAnimDuration(), start, end));

        start = 50; end = 50;
        super.getAnimations().addAnimation("died", new AnimationChannel(TEXTURE, 7, frameSpecs.getFrameWidth(), frameSpecs.getFrameHeight(), frameSpecs.getAnimDuration(), start, end));

        start = 0; end = 26;
        super.getAnimations().addAnimation("look_around", new AnimationChannel(TEXTURE, 7, frameSpecs.getFrameWidth(), frameSpecs.getFrameHeight(), Duration.seconds(3), start, end));

    }
}
