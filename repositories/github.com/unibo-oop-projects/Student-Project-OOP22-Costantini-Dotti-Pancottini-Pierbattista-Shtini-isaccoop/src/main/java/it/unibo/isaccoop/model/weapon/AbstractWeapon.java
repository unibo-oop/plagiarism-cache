package it.unibo.isaccoop.model.weapon;

import java.util.ArrayList;
import java.util.List;

import it.unibo.isaccoop.model.common.MapElement;

/**
 * AbstractWeapon abstract class that implements Weapon interface, it models
 * a common container for weapons.
 * */
public abstract class AbstractWeapon implements Weapon {

    /**
     * Weapon shots list field.
     * */
    private final List<WeaponShot> weaponShots;

    /**
     *  Constructor for {@link EnemyWeapon}.
     * */
    public AbstractWeapon() {
       this.weaponShots = new ArrayList<>();
    }

    /**
     *  Add weapon shot to weapon shots list.
     *
     *  @param newShot new shot to attach to shots list
     * */
    protected void addWeaponShot(final WeaponShot newShot) {
        this.weaponShots.add(newShot);
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void tickShots() {
        this.weaponShots.forEach(shot -> shot.tickShot());
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public List<WeaponShot> getWeaponShots() {
        return List.copyOf(this.weaponShots);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(final MapElement e) {
        this.weaponShots.remove(e);
    }

}
