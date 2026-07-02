package it.unibo.bmbman.model.entities.powerups;

import it.unibo.bmbman.model.collision.Collision;
import it.unibo.bmbman.model.entities.AbstractEntity;
import it.unibo.bmbman.model.entities.HeroImpl;
import it.unibo.bmbman.model.utilities.Dimension;
import it.unibo.bmbman.model.utilities.EntityType;
import it.unibo.bmbman.model.utilities.Position;

/**
 * Models the general aspects of a power-up entity.
 */
public abstract class AbstractPowerupEntity extends AbstractEntity {

    private static final int DURATION = 8;
    private static final long MILLIS = 1000;
    private static final int DIMENSION = 30;
    private final boolean bonus;
    private HeroImpl hero;
    private long startTime;
    /**
     * Constructor for a general power-up.
     * @param position the position where i want to locate the power-up
     * @param isBonus if it's a bonus or a malus.
     * @param dimension the dimension of the power-up.
     */
    public AbstractPowerupEntity(final Position position, final Dimension dimension, final boolean isBonus) {
        super(new Position(position.getX(), position.getY()), EntityType.POWER_UP, dimension);
        this.bonus = isBonus;
    }
    /**
     * Constructor for a general power-up that doesn't require dimension.
     * @param position the position where i want to locate the power-up.
     * @param isBonus if it's a bonus or a malus.
     */
    public AbstractPowerupEntity(final Position position, final boolean isBonus) {
        this(new Position(position.getX() + 10, position.getY() + 10), new Dimension(DIMENSION, DIMENSION), isBonus);
    }
    /**
     * After a collision with the hero, activate the power-up effect.
     */
    @Override
    public void onCollision(final Collision c) {
        if (c.getReceiver().getType() == EntityType.HERO) {
            this.hero = (HeroImpl) c.getReceiver();
            this.startTime = System.currentTimeMillis() / MILLIS;
            powerupEffect(hero);
        }
    }
    /**
     * 
     * @return true if the power up is bonus, false if it is malus
     */
    public boolean isBonus() {
        return this.bonus;
    }
    /**
     * Active the effect.
     * @param hero the target of the effect.
     */
    public abstract void powerupEffect(HeroImpl hero);
    /**
     * Remove the effect.
     * @param hero the hero.
     */
    public abstract void removeEffect(HeroImpl hero);
    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        if (startTime > 0) {
            final long now = System.currentTimeMillis() / MILLIS;
            if (now - startTime >= DURATION) {
                startTime = 0;
                removeEffect(hero);
            }
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove() {
        return this.startTime > 0;
    }
}
