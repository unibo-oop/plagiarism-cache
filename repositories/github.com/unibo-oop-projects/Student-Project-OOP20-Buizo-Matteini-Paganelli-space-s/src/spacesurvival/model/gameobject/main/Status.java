package spacesurvival.model.gameobject.main;

import spacesurvival.utilities.gameobject.BulletUtils;
import spacesurvival.utilities.gameobject.DurationUtils;
import spacesurvival.utilities.path.animation.AnimationEffect;

import java.util.List;

/**
 * All types of possible status.
 */
public enum Status {
    /**
     * The main status of an object.
     */
    NORMAL(AnimationEffect.LIST_NORMAL),
    /**
     * Make an object immune to damages.
     */
    INVINCIBLE(AnimationEffect.LIST_INVINCIBLE, DurationUtils.INVINCIBLE),
    /**
     * Applies a fire damage for a certain time.
     */
    ON_FIRE(AnimationEffect.LIST_BURN, DurationUtils.ON_FIRE),
    /**
     * Slow down the object affected.
     */
    FROZEN(AnimationEffect.LIST_ICE, DurationUtils.FROZEN),
    /**
     * Stop moving the object affected.
     */
    PARALYZED(AnimationEffect.LIST_ELECTRIC, DurationUtils.PARALYZED),
    /**
     * Heals the object of a certain quantity.
     */
    HEALED(AnimationEffect.LIST_LIFE_UP, DurationUtils.HEALED),
    /**
     * Increase lives of the object.
     */
    LIVES_INCREASED(AnimationEffect.LIST_HEALED, DurationUtils.LIVES_INCREASED);

    private final List<String> animation;
    private final int duration;

    Status(final List<String> animation) {
        this.animation = animation;
        this.duration = BulletUtils.INFINITY;
    }

    Status(final List<String> animation, final int duration) {
        this.animation = animation;
        this.duration = duration;
    }

    /**
     * @return the animation for game object when suffering from an effect
     */
    public List<String> getAnimation() {
        return this.animation;
    }

    /**
     * @return the effect duration
     */
    public int getDuration() {
        return duration;
    }

}
