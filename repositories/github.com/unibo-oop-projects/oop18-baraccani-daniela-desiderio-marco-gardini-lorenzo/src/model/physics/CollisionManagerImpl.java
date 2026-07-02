package model.physics;

import java.util.Objects;

import model.Model;
import model.entitiesutil.Entity;
import model.entitiesutil.EntityDirection;
import model.entitiesutil.EntityMovable;

/**
 * It controls collisions between {@link Entity}s.
 */
public class CollisionManagerImpl implements CollisionManager {

    private final Model model;

    /**
     * Initialize the collision manager.
     * 
     * @param model the {@link Model} of the game
     */
    public CollisionManagerImpl(final Model model) {
        this.model = Objects.requireNonNull(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collision() {
        for (final EntityMovable entityMoved : this.model.getLevel().getToRecheckEntities()) {
            for (final Entity entityLevel : this.model.getLevel().getEntities()) {
                collision(entityMoved, entityLevel);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collision(final EntityMovable entityMoved) {
        for (final Entity entityLevel : this.model.getLevel().getEntities()) {
            collision(entityMoved, entityLevel);
        }
    }

    /*
     * Check collisions between two specific Enities.
     */
    private void collision(final EntityMovable entityMoved, final Entity entityLevel) {
        if (entityMoved != entityLevel && entityMoved.isVisible() && entityLevel.isVisible()) {
            final int xMoved = entityMoved.getX();
            final int yMoved = entityMoved.getY();
            final int wMoved = entityMoved.getWidth();
            final int hMoved = entityMoved.getHeight();
            final int xLevel = entityLevel.getX();
            final int yLevel = entityLevel.getY();
            final int wLevel = entityLevel.getWidth();
            final int hLevel = entityLevel.getHeight();

            if ((xLevel + wLevel - xMoved) >= 0 && (xMoved + wMoved - xLevel) >= 0
                    && (yLevel + hLevel - yMoved) >= 0 && (yMoved + hMoved - yLevel) >= 0) {
                if ((xLevel < xMoved) && entityMoved.getDirection().equals(EntityDirection.LEFT)) {
                    entityLevel.isTouchedBy(entityMoved, CollisionDirection.RIGHT);
                    entityMoved.isTouchedBy(entityLevel, CollisionDirection.LEFT);
                }
                if ((xLevel + wLevel) > (xMoved + wMoved) && entityMoved.getDirection().equals(EntityDirection.RIGHT)) {
                    entityLevel.isTouchedBy(entityMoved, CollisionDirection.LEFT);
                    entityMoved.isTouchedBy(entityLevel, CollisionDirection.RIGHT);
                }
                if (((xMoved + wMoved) >= (xLevel + (wLevel / 2)) && (xMoved + wMoved) <= (xLevel + wLevel)) 
                        || (xMoved >= xLevel && xMoved <= (xLevel + (wLevel / 2)))) {
                    if (entityMoved.getDirection() == EntityDirection.DOWN_LEFT
                            || entityMoved.getDirection() == EntityDirection.DOWN_RIGHT) {
                        entityLevel.isTouchedBy(entityMoved, CollisionDirection.TOP);
                        entityMoved.isTouchedBy(entityLevel, CollisionDirection.BOTTOM);
                    } else if (entityMoved.getDirection() == EntityDirection.UP_LEFT
                            || entityMoved.getDirection() == EntityDirection.UP_RIGHT) {
                        entityLevel.isTouchedBy(entityMoved, CollisionDirection.BOTTOM);
                        entityMoved.isTouchedBy(entityLevel, CollisionDirection.TOP);
                    }
                } else if (xMoved >= (xLevel + (wLevel / 2)) && xMoved < (xLevel + wLevel)) {
                    entityLevel.isTouchedBy(entityMoved, CollisionDirection.RIGHT);
                    entityMoved.isTouchedBy(entityLevel, CollisionDirection.LEFT);
                } else if ((xMoved + wMoved) > xLevel && (xMoved + wMoved) <= (xLevel + (wLevel / 2))) {
                    entityLevel.isTouchedBy(entityMoved, CollisionDirection.LEFT);
                    entityMoved.isTouchedBy(entityLevel, CollisionDirection.RIGHT);
                }
            }
        }
    }
}
