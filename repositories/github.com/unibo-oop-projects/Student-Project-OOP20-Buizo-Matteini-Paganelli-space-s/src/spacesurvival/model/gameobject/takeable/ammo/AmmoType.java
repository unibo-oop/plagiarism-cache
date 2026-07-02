package spacesurvival.model.gameobject.takeable.ammo;

import java.util.List;
import java.util.stream.Collectors;

import spacesurvival.model.gameobject.fireable.weapon.Effect;
import spacesurvival.utilities.RandomUtils;
import spacesurvival.utilities.path.animation.AnimationPerk;

/**
 * Utils for Ammo type.
 */
public enum AmmoType {
    /**
     * It has no effect on the harmed object.
     */
    NORMAL(Effect.NONE, List.of(), AmmoPath.NORMAL),
    /**
     * Causes fire effect on the harmed object.
     */
    FIRE(Effect.FIRE, AnimationPerk.LIST_FIRE, AmmoPath.FIRE),
    /**
     * Causes electric effect on the harmed object.
     */
    ELECTRIC(Effect.ELECTRIC, AnimationPerk.LIST_ELECTRIC, AmmoPath.ELECTRIC),
    /**
     * Causes ice effect on the harmed object.
     */
    ICE(Effect.ICE, AnimationPerk.LIST_ICE, AmmoPath.ICE);

    private final Effect effect;
    private final List<String> animation;
    private final AmmoPath ammoPath;

    private static final List<AmmoType> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();

    AmmoType(final Effect effect, final List<String> animation, final AmmoPath ammoPath) {
        this.effect = effect;
        this.animation = animation;
        this.ammoPath = ammoPath;
    }

    /**
     * @return the effect caused by 
     */
    public Effect getEffect() {
        return this.effect;
    }

    public String getImagePath() {
        return this.ammoPath.getImagePath();
    }

    /**
     * @return the list of paths for the ammo type animation
     */
    public List<String> getAnimation() {
        return this.animation;
    }

    public String getBulletHud() {
        return this.ammoPath.getBulletHud();
    }

    public String getBulletFire() {
        return this.ammoPath.getBulletFire();
    }

    public String getBulletInit() {
        return this.ammoPath.getBulletInit();
    }

    /**
     * @return a random ammo type
     */
    public static AmmoType randomExceptNormal()  {
        return VALUES.stream()
                .filter(ammoType -> !ammoType.equals(NORMAL))
                .collect(Collectors.toList())
                .get(RandomUtils.RANDOM.nextInt(SIZE - 1));
    }

}
