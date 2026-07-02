package spacesurvival.model.gameobject.fireable.weapon;

import spacesurvival.utilities.dimension.ScaleOf;
import spacesurvival.utilities.dimension.Screen;
import spacesurvival.utilities.gameobject.BulletUtils;
import spacesurvival.utilities.gameobject.VelocityUtils;
import spacesurvival.model.common.P2d;
import spacesurvival.model.common.V2d;
import spacesurvival.model.gameobject.fireable.FireableObject;
import spacesurvival.model.gameobject.moveable.Bullet;
import spacesurvival.model.gameobject.takeable.ammo.AmmoType;

import java.awt.geom.AffineTransform;
import java.util.HashSet;
import java.util.Set;

import spacesurvival.model.EngineImage;
import spacesurvival.model.collision.bounding.RectBoundingBox;
import spacesurvival.model.collision.eventgenerator.BulletComponent;

/**
 * This class allows to a FireableObject to fire.
 */
public class Weapon {
    private FireableObject owner;
    private AmmoType ammoType;
    private Magazine magazine;
    private int munitions;

    private final Set<Bullet> shootedBullets;
    private boolean canShoot = true;

    /**
     * Create a weapon with normal ammo type and without owner.
     */
    public Weapon() {
        this.ammoType = AmmoType.NORMAL;
        this.magazine =  Magazine.UNLIMITED;
        this.munitions = BulletUtils.INFINITY;
        this.shootedBullets = new HashSet<>();
    }

    /**
     * Create a weapon with normal ammo type.
     * 
     * @param owner the owner of weapon
     */
    public Weapon(final FireableObject owner) {
        this.ammoType = AmmoType.NORMAL;
        this.magazine =  Magazine.UNLIMITED;
        this.munitions = BulletUtils.INFINITY;
        this.shootedBullets = new HashSet<>();
        this.owner = owner;
    }

    /**
     * Create a weapon with custom ammo type.
     * 
     * @param ammoType the ammo type of bullets fired from the weapon 
     * @param owner the owner of weapon
     */
    public Weapon(final AmmoType ammoType, final FireableObject owner) {
        this.owner = owner;
        this.ammoType = ammoType;

        switch (this.ammoType) {
        case NORMAL:
            this.magazine =  Magazine.UNLIMITED;
            this.munitions = BulletUtils.INFINITY;
            break;
        case FIRE:
        case ICE: 
        case ELECTRIC:
            this.magazine =  Magazine.LIMITED;
            this.munitions = BulletUtils.SPECIAL_MUNITIONS_QUANTITY;
            break;
        default:
            break;
        }

        this.shootedBullets = new HashSet<>();
    }

    /**
     * Create a Bullet and add it to world.
     */
    public void shoot() {
        final EngineImage engineImage = new EngineImage(ScaleOf.BULLET, Screen.WIDTH_FULLSCREEN, ammoType.getBulletFire());
        final P2d position = new P2d();
        final V2d velocity = VelocityUtils.BULLET_VEL;
        final double acceleration = VelocityUtils.NO_ACCELERATION;

        final Bullet bullet = new Bullet(engineImage, position, new RectBoundingBox(), new BulletComponent(),
                velocity, acceleration, null, BulletUtils.BULLET_DAMAGE,
                ammoType.getEffect(), this);


        final AffineTransform newTransform = new AffineTransform();
        newTransform.setTransform(owner.getTransform());

        final double bulletX = owner.getWidth() / 2 - bullet.getWidth() / 2;
        final double bulletY = -owner.getHeight() / 2;
        newTransform.translate(bulletX, bulletY);
        bullet.setTransform(newTransform);

        if (magazine == Magazine.LIMITED) {
            munitions--;
            if (munitions == 0) {
                setAmmoType(AmmoType.NORMAL);
            }
        }
        shootedBullets.add(bullet);
        bullet.startMoving();
    }

    /**
     * @return the owner of the weapon.
     */
    public FireableObject getOwner() {
        return owner;
    }

    /**
     * Sets a new owner to the weapon.
     * 
     * @param owner the owner to set.
     */
    public void setOwner(final FireableObject owner) {
        this.owner = owner;
    }

    /**
     * @return the current ammo type of this weapon
     */
    public AmmoType getAmmoType() {
        return ammoType;
    }

    /**
     * Sets a new ammo type to the weapon.
     * 
     * @param ammoType the ammo type to set
     */
    public void setAmmoType(final AmmoType ammoType) {
        this.ammoType = ammoType;
        if (this.ammoType == AmmoType.NORMAL) {
            setMagazine(Magazine.UNLIMITED);
            setMunitions(BulletUtils.INFINITY);
        } else {
            setMagazine(Magazine.LIMITED);
            setMunitions(BulletUtils.SPECIAL_MUNITIONS_QUANTITY);
        }
    }

    /**
     * @return the weapon magazine
     */
    public Magazine getMagazine() {
        return magazine;
    }

    /**
     * Sets a new magazine to the weapon.
     * 
     * @param magazine the magazine to set.
     */
    private void setMagazine(final Magazine magazine) {
        this.magazine = magazine;
    }

    /**
     * @return the weapon munitions
     */
    public int getMunitions() {
        return munitions;
    }

    /**
     * Sets a new number of munitions to the weapon.
     * 
     * @param munitions the munitions number to set.
     */
    private void setMunitions(final int munitions) {
        this.munitions = munitions;
    }

    /**
     * @return bullets fired from the weapon
     */
    public Set<Bullet> getShootedBullets() {
        return shootedBullets;
    }

    /**
     * If parameter is true allows the weapon to shoot.
     * 
     * @param canShoot true is allowed
     */
    public void canShoot(final boolean canShoot) {
        this.canShoot = canShoot;
    }

    /**
     * Return true if weapon is allowed to shoot.
     * 
     * @return true if weapon can shoot
     */
    public boolean canShoot() {
        return this.canShoot;
    }

}
