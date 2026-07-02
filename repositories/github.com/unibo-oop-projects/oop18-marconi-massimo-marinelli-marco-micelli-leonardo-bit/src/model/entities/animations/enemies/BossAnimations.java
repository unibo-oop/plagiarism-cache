package model.entities.animations.enemies;

import com.almasb.fxgl.texture.AnimationChannel;

import model.entities.animations.AnimationSupplierImpl;
import model.game.settings.AnimationsFrameSpecifics;

/**
 * Provides classes with all the animations a boss can have.
 *
 */
public class BossAnimations extends AnimationSupplierImpl {

    private static final String TEXTURE = "SkeletonKingBoss.png";
    private final AnimationsFrameSpecifics frameSpecs = new AnimationsFrameSpecifics();

    /**
     * Returns an object storing all the possible animations for the boss.
     */
    public BossAnimations() {
        super();

        int start = 0, end = 0;
        super.getAnimations().addAnimation("idle", new AnimationChannel(TEXTURE, 3, 128, 128, frameSpecs.getAnimDuration() , start, end));

        start = 0; end = 4;
        super.getAnimations().addAnimation("attack_l/r", new AnimationChannel(TEXTURE, 3, 128, 128, frameSpecs.getAnimDuration() , start, end));

        start = 0; end = 0;
        super.getAnimations().addAnimation("look_around", new AnimationChannel(TEXTURE, 3, 128, 128, frameSpecs.getAnimDuration() , start, end));

        start = 5; end = 8;
        super.getAnimations().addAnimation("die", new AnimationChannel(TEXTURE, 3, 128, 128, frameSpecs.getAnimDuration() , start, end));

        start = 8; end = 8;
        super.getAnimations().addAnimation("died", new AnimationChannel(TEXTURE, 3, 128, 128, frameSpecs.getAnimDuration() , start, end));


    }
}
