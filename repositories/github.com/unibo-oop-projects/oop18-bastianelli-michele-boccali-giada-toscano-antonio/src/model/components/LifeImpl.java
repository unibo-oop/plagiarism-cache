package model.components;

import model.entities.EntityModel;

/**
 * Implementation of the life component.
 */
public class LifeImpl extends AbstractComponent implements Life {
    private final int minLife;
    private final int maxLife;
    private int currentLife;

    /**
     * @param owner       the component owner
     * @param minLife     the minimum value of life allowed to keep the entity alive
     * @param maxLife     the max value of life allowed
     * @param currentLife the starting value of life that the entity keeps
     */
    public LifeImpl(final EntityModel owner, final int minLife, final int maxLife, final int currentLife) {
        super(owner);
        this.minLife = minLife;
        this.maxLife = maxLife;
        this.currentLife = currentLife;
    }

    @Override
    public final int getLife() {
        return this.currentLife;
    }

    @Override
    public final void setLife(final int lifeToSet) {
        if (lifeToSet < minLife) {
            this.currentLife = Math.max(lifeToSet, minLife);
        } else {
            this.currentLife = Math.min(maxLife, lifeToSet);
        }
    }

    @Override
    public final void increaseLife(final int lifeToAdd) {
        this.currentLife = Math.min(maxLife, currentLife + lifeToAdd);
    }

    @Override
    public final void decreaseLife(final int lifeToRemove) {
        if (currentLife >= minLife) {
            this.currentLife = currentLife - lifeToRemove;
        }
    }

    @Override
    public final boolean isAlive() {
        return currentLife >= minLife;
    }

    @Override
    public void setDead() {
        this.currentLife = minLife - 1; 
    }

}
