package rogue.model.items.weapons;

import rogue.model.creature.Player;

/**
 * A minimal implementation for a {@link Weapon}.
 */
public final class BaseWeapon implements Weapon {

    private final WeaponType weapon;

    /**
     * Builds a new Weapon.
     * @param weapon
     *          the weapon type
     */
    public BaseWeapon(final WeaponType weapon) {
        this.weapon = weapon;
    }

    /**
     * Equip the player with this weapon.
     * @param player
     *          the player who wants to wear this weapon
     */
    @Override
    public boolean use(final Player player) {
        player.getEquipment().setWeapon(this);
        return true;
    }

    @Override
    public int getDamage(final Use use) {
        return this.weapon.getDamageSupplier(use).get();
    }

    @Override
    public int getAccuracy() {
        return this.weapon.getAccuracy();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((weapon == null) ? 0 : weapon.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BaseWeapon)) {
            return false;
        }
        final BaseWeapon other = (BaseWeapon) obj;
        return weapon == other.weapon;
    }

    @Override
    public String toString() {
        return this.weapon.getName();
    }

}
