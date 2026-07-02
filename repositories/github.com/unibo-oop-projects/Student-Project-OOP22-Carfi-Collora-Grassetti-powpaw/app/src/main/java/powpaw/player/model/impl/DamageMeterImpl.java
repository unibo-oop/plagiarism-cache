package powpaw.player.model.impl;

import powpaw.player.model.api.DamageMeter;

/**
 * DamageMeterImpl that implement DamageMeter model.
 * 
 * @author Simone Collorà
 */
public final class DamageMeterImpl implements DamageMeter {

    private double damage;

    /**
     * DamageMeter costructor that set damage to 0.
     */
    public DamageMeterImpl() {
        damage = 0;
    }

    @Override
    public double getDamage() {
        return this.damage;
    }

    @Override
    public void setDamage(final double damage) {
        this.damage += damage;
    }

}
