package model.entities.animations.player;

import com.almasb.fxgl.texture.AnimationChannel;

import model.entities.animations.AnimationSupplierImpl;
import model.game.settings.AnimationsFrameSpecifics;

/**
 * Provides classes with all the animations an Archer can have.
 */
public class ArcherAnimations extends AnimationSupplierImpl {

    private static final String TEXTURE = "Archer.png";
    private final AnimationsFrameSpecifics frameSpecs = new AnimationsFrameSpecifics();

    /**
     * Returns an object storing all the possible animations for the Archer class.
     */
    public ArcherAnimations() {
        super();

        int start = 18, end = 18;
        super.getAnimations().addAnimation("idle", new AnimationChannel(TEXTURE, frameSpecs.getFramesPerRow(), frameSpecs.getFrameWidth(), frameSpecs.getFrameHeight(), frameSpecs.getAnimDuration(), start, end));

        start = 18; end = 26;
        super.getAnimations().addAnimation("walk_l/r", new AnimationChannel(TEXTURE, frameSpecs.getFramesPerRow(), frameSpecs.getFrameWidth(), frameSpecs.getFrameHeight(), frameSpecs.getAnimDuration(), start, end));

        start = 0; end = 8;
        super.getAnimations().addAnimation("walk_up", new AnimationChannel(TEXTURE, frameSpecs.getFramesPerRow(), frameSpecs.getFrameWidth(), frameSpecs.getFrameHeight(), frameSpecs.getAnimDuration(), start, end));

        start = 9; end = 17;
        super.getAnimations().addAnimation("walk_down", new AnimationChannel(TEXTURE, frameSpecs.getFramesPerRow(), frameSpecs.getFrameWidth(), frameSpecs.getFrameHeight(), frameSpecs.getAnimDuration(), start, end));


        start = 53; end = 65;
        super.getAnimations().addAnimation("attack_l/r", new AnimationChannel(TEXTURE, frameSpecs.getFramesPerRow(), frameSpecs.getFrameWidth(), frameSpecs.getFrameHeight(), frameSpecs.getAnimDuration(), start, end));

        start = 66; end = 71;
        super.getAnimations().addAnimation("die", new AnimationChannel(TEXTURE, frameSpecs.getFramesPerRow(), frameSpecs.getFrameWidth(), frameSpecs.getFrameHeight(), frameSpecs.getAnimDuration(), start, end));
    }
}
