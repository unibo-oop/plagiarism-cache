package powpaw.player.model.impl;

import powpaw.player.model.api.PlayerStats;

/**
 * PlayerStats implementation.
 * 
 * @author Simone Collorà
 */
public final class PlayerStatsImpl implements PlayerStats {

    private double attack;
    private double defence;
    private double speed;

    /**
     * PLayerStats costructor that builder will use.
     * 
     * @param attack
     * @param defence
     * @param speed
     */
    public PlayerStatsImpl(final double attack, final double defence, final double speed) {
        this.attack = attack;
        this.defence = defence;
        this.speed = speed;
    }

    @Override
    public double getAttack() {
        return this.attack;
    }

    @Override
    public double getDefence() {
        return this.defence;
    }

    @Override
    public double getSpeed() {
        return this.speed;
    }

    @Override
    public void setAttack(final double attack) {
        this.attack = attack;
    }

    @Override
    public void setDefence(final double defence) {
        this.defence = defence;
    }

    @Override
    public void setSpeed(final double speed) {
        this.speed = speed;
    }

}
