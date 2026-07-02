package it.unibo.aknightstale.models.entity;

import it.unibo.aknightstale.utils.Borders;


public class ObstacleEntity extends BaseCharacter {


    public ObstacleEntity(final Borders borders) {
        super(borders, EntityType.OBSTACLE, true, Direction.RIGHT, 0, 0, 0, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getAttackRange() {
        return 0;
    }

}
