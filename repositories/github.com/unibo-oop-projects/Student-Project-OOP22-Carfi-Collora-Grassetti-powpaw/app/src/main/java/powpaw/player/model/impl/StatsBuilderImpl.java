package powpaw.player.model.impl;

import powpaw.player.model.api.PlayerStats;
import powpaw.player.model.api.StatsBuilder;

/**
 * StatsBuilder implementation. As every stat is a double but double are
 * inaccurate, every setter get an int
 * and later that became a double with toDouble method.
 * 
 * 
 * @author Simone Collorà
 */
public final class StatsBuilderImpl implements StatsBuilder {
    private double attack;
    private double defence;
    private double speed;

    @Override
    public void setAttack(final int attack) {
        this.attack = toDouble(attack);
    }

    @Override
    public void setDefence(final int defence) {
        this.defence = toDouble(defence / 3);
    }

    @Override
    public void setSpeed(final int speed) {
        this.speed = toDouble(speed);
    }

    /**
     * Build the new player with the stats passed before.
     */
    @Override
    public PlayerStats build() {
        return new PlayerStatsImpl(this.attack, this.defence, this.speed);
    }

    /**
     * 
     * @param value
     * @return value/10 in double because the int passed would be too big
     */
    private double toDouble(final int value) {
        return (double) value / 10;
    }
}
