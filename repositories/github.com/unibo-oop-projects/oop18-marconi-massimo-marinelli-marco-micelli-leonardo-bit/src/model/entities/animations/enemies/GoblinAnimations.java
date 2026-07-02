package model.entities.animations.enemies;

import com.almasb.fxgl.texture.AnimationChannel;

import javafx.util.Duration;
import model.entities.animations.AnimationSupplierImpl;
import model.game.settings.AnimationsFrameSpecifics;

/**
 * Provides classes with all the animations a Goblin can have.
 *
 */
public class GoblinAnimations extends AnimationSupplierImpl {

    private static final String TEXTURE = "Goblin.png";
    private final AnimationsFrameSpecifics frameSpecs = new AnimationsFrameSpecifics();

    /**
     * Returns an object storing all the possible animations for the Goblin.
     */
    public GoblinAnimations() {
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

        start = 71; end = 71;
        super.getAnimations().addAnimation("died", new AnimationChannel(TEXTURE, frameSpecs.getFramesPerRow(), frameSpecs.getFrameWidth(), frameSpecs.getFrameHeight(), frameSpecs.getAnimDuration(), start, end));

        start = 0; end = 26;
        super.getAnimations().addAnimation("look_around", new AnimationChannel(TEXTURE, frameSpecs.getFramesPerRow(), frameSpecs.getFrameWidth(), frameSpecs.getFrameHeight(), Duration.seconds(3), start, end));

    }
}
