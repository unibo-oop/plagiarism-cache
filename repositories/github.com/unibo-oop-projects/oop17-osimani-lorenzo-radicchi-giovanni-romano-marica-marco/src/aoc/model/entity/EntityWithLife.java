package aoc.model.entity;

import aoc.utilities.Vector;

/**
 * Defines the basic implementation of an entity with life.
 */
public class EntityWithLife extends Entity implements WithLife {

    /**
     * The maximum value of life of the entity, equal to her initial life.
     */
    private int maxLife;
	
    /**
     * The actual life of the entity.
     */
    private int life;

    public EntityWithLife(final Vector position, final Vector speed, final String name, final int life) {
	super(position, speed, name);
	this.life = life;
	this.maxLife = life;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getLifeAsPercentage() {
	return (this.life / this.maxLife);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void damaged(final int amount) {
        this.life = this.life - amount;
        if (this.life <= 0) {
            this.kill();
            this.life = 0;
        }
    }

}
