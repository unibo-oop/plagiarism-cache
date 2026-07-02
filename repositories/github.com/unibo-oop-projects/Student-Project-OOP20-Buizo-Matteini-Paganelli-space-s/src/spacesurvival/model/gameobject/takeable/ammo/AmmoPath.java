package spacesurvival.model.gameobject.takeable.ammo;

import spacesurvival.utilities.path.Weapon;
import spacesurvival.utilities.path.animation.AnimationPerk;
import spacesurvival.utilities.path.bullet.BulletFire;
import spacesurvival.utilities.path.bullet.BulletHUD;

/**
 * Ammo path.
 *
 */
public enum AmmoPath {
    /**
     * Path to the normal ammo.
     */
    NORMAL(Weapon.NORMAL, BulletHUD.NORMAL, BulletFire.NORMAL, ""),
    /**
     * Path to the fire ammo.
     */
    FIRE(Weapon.FIRE, BulletHUD.FIRE, BulletFire.FIRE, AnimationPerk.FIRE0),
    /**
     * Path to the electric ammo.
     */
    ELECTRIC(Weapon.ELECTRIC, BulletHUD.ELECTRIC, BulletFire.ELECTRIC, AnimationPerk.ELECTRIC0),
    /**
     * Path to the ice ammo.
     */
    ICE(Weapon.ICE, BulletHUD.ICE, BulletFire.ICE, AnimationPerk.ICE0);

    private final String imagePath;
    private final String bulletHud;
    private final String bulletFire;
    private final String bulletInit;

    AmmoPath(final String imagePath, final String bulletHUD, final String bulletFire, final String bulletInit) {
        this.imagePath = imagePath;
        this.bulletHud = bulletHUD;
        this.bulletFire = bulletFire;
        this.bulletInit = bulletInit;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public String getBulletHud() {
        return this.bulletHud;
    }

    public String getBulletFire() {
        return this.bulletFire;
    }

    public String getBulletInit() {
        return this.bulletInit;
    }
}
