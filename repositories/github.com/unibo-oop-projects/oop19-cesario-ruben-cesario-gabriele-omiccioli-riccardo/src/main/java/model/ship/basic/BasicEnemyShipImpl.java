package model.ship.basic;

import java.util.Collection;

import model.entity.EntityID;
import model.weapon.Weapon;
import utilities.math.Point2D;
import utilities.math.Vector2D;

/**
 * Standard implementation for BasicEnemyShip.
 */
class BasicEnemyShipImpl extends BasicSpaceShipImpl implements BasicEnemyShip {

    BasicEnemyShipImpl(final Point2D position, final double radiantAngle, final Vector2D speed, 
                             final BasicEnemyShipTemplate model) {
        super(position, radiantAngle, speed, model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScorePoints() {
        return ((BasicEnemyShipTemplate) this.getShipModel()).scorepointValue;
    }

    /**
     * Represents the model of a BasicEnemyShip, containing all its
     * model-dependent properties, including the scorepoint value of this ship.
     */
    protected static class BasicEnemyShipTemplate extends BasicSpaceShipTemplate {

        private final int scorepointValue;

        public BasicEnemyShipTemplate(final EntityID modelID, final int maxHealth, final double maxSpeed, 
                                      final double maxAcceleration, final double maxAngularSpeed, final double radius,
                                      final double drag, final Collection<Weapon> weapons, final int scorepointValue) {
            super(modelID, maxHealth, maxSpeed, maxAcceleration, maxAngularSpeed, radius, drag, weapons);
            this.scorepointValue = scorepointValue;
        }
    }
}
