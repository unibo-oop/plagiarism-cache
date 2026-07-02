package it.unibo.oop.bbgmm.Entity.Component;

import java.util.ArrayList;

public class WeaponImpl extends AbstractEntityComponent implements Weapon {

    private int weaponDamage;
    private int weaponRange;
    private List<Bullet> bulletShoted;


    /**
     *
     * @param basicWeapon
     *                  The entity weapon.
     */
    public WeaponImpl (final Inventory basicWeapon) {
        this.weaponDamage = basicWeapon.damage;
        this.weaponRange = basicWeapon.range;
        this.bulletShoted = new ArrayList<Bullet>();
    }

    @Override
    public int getWeaponDamage() {
        return this.weaponDamage;
    }

    @Override
    public void setWeaponDamage(final int damage) {
        this.weaponDamage = damage;
    }

    @Override
    public int getWeaponRange() {
        return this.weaponRange;
    }

    @Override
    public void setWeaponRange(final int range) {
        this.weaponRange = range;
    }

    @Override
    public void shoot(final Direction ownerDirection) {
        this.bulletShoted(new Bullet(ownerDirection, this.weaponRange, this.weaponDamage));
    }

    @Override
    public List<Bullet> getBulletList() {
        return this.bulletShoted;
    }

}
