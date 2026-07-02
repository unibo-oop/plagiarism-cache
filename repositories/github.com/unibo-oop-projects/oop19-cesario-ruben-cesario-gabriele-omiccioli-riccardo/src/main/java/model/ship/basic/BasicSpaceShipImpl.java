package model.ship.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import model.entity.EntityID;
import model.entity.MovingEntityImpl;
import model.entity.EntityID.EntityIDCategory;
import model.weapon.Weapon;
import utilities.math.Point2D;
import utilities.math.Vector2D;
import utilities.math.Vector2DImpl;

/**
 * Standard implementation for BasicSpaceShip.
 */
class BasicSpaceShipImpl extends MovingEntityImpl implements BasicSpaceShip {

    private static final BasicSpaceShipTemplate DEFAULT_MODEL = new BasicSpaceShipTemplate(EntityID.SPACESHIP_BASIC, 0, 0, 0, 0, 0, 0D, Collections.emptyList());
    private static final double MOVEMENT_TOLLERANCE = 0.001;
    private final BasicSpaceShipTemplate shipModel;
    private double health;

    BasicSpaceShipImpl(final Point2D position, final double radiantAngle, final Vector2D speed) {
        this(position, radiantAngle, speed, DEFAULT_MODEL);
    }
    BasicSpaceShipImpl(final Point2D position, final double radiantAngle, final Vector2D speed, 
                          final BasicSpaceShipTemplate model) {
        super(position, model.radius, radiantAngle, speed);
        this.shipModel = model;
        this.health = this.shipModel.maxHealth;
    }

    /**
     * {@inheritDoc}
     * A BasicSpaceShip will return the id of its model.
     */
    @Override
    public EntityID getID() {
        return this.shipModel.modelID;
    }

    /**
     * {@inheritDoc}
     * A BasicSpaceShip cannot rotate faster than its maximum angular speed.
     */
    @Override
    public void rotateAnticlockwise(final double angle) {
        if (angle > this.getMaxAngularSpeed() + MOVEMENT_TOLLERANCE) {
            throw new IllegalArgumentException("The ship [" + this.getID() + "] cannot rotate this fast.");
        }
        super.rotateAnticlockwise(angle);
    }

    /**
     * {@inheritDoc}
     * A BasicSpaceShip will decelerate due to its drag while moving.
     */
    @Override
    public void move() {
        super.move();
        this.decelerateByDrag();
    }

    /**
     * {@inheritDoc}
     * A BasicSpaceShip cannot accelerate faster than its maximum acceleration.
     */
    @Override
    public void accelerate(final Vector2D acceleration) {
        if (acceleration.module() > this.getMaxAcceleration() + MOVEMENT_TOLLERANCE) {
            throw new IllegalArgumentException("The ship [" + this.getID() + "] cannot accelerate this fast.");
        }
        super.accelerate(acceleration);
        this.capSpeed();
    }

    /**
     * {@inheritDoc}
     * A BasicSpaceShip is destroyed when its health is zero or below. 
     */
    @Override
    public boolean is() {
        return this.health > 0;
    }

    /**
     * {@inheritDoc}
     * For a BasicSpaceShip, reduces its health by the amount of damage received.
     */
    @Override
    public void receiveDamage(final double damage) {
        this.health -= damage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Weapon> getWeapons() {
        return this.shipModel.weapons;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxHealth() {
        return this.shipModel.maxHealth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getHealth() {
        return health;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getMaxSpeed() {
        return this.shipModel.maxSpeed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getMaxAcceleration() {
        return this.shipModel.maxAcceleration;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getMaxAngularSpeed() {
        return this.shipModel.maxAngularSpeed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getDrag() {
        return this.shipModel.drag;
    }

    /*SETTERS & GETTERS------------------------------------------------*/
    /**
     * Returns the model of this ship.
     * @return the model of this ship.
     */
    public BasicSpaceShipTemplate getShipModel() {
        return shipModel;
    }
    /*-----------------------------------------------------------------*/

    /**
     * Reduces the speed of this ship to a certain percentage depending
     * on its drag. The higher the drag, the lower the percentage.
     */
    protected void decelerateByDrag() {
        this.resetSpeed(this.getSpeed().multiplyByScalar(1 - this.getDrag()));
        this.capSpeed();
    }

    /**
     * Caps the speed of this ship to its maximum speed or zero, assuming
     * a BasicSpaceShip can't accelerate over its maximum speed or decelerate
     * till moving backwards.
     */
    private void capSpeed() {
        if (this.getSpeed().module() > this.getMaxSpeed()) {
            this.resetSpeed(this.getSpeed().multiplyByScalar(this.getMaxSpeed() / this.getSpeed().module()));
        } else if (this.getSpeed().module() < 0) {
            this.resetSpeed(new Vector2DImpl(0, 0));
        }
    }

    /**
     * Represents the model of a BasicSpaceShip, containing all its
     * model-dependent properties.
     */
    protected static class BasicSpaceShipTemplate {

        private final EntityID modelID;
        private final int maxHealth;
        private final double maxSpeed;
        private final double maxAcceleration;
        private final double maxAngularSpeed;
        private final double radius;
        private final double drag;
        private final List<Weapon> weapons = new ArrayList<>();

        public BasicSpaceShipTemplate(final EntityID modelID, final int maxHealth, final double maxSpeed, 
                                      final double maxAcceleration, final double maxAngularSpeed,
                                      final double radius, final double drag, final Collection<Weapon> weapons) {
            this.modelID = EntityID.requireBelonging(modelID, EntityIDCategory.SPACESHIPS_BASIC);
            this.maxHealth = maxHealth;
            this.maxSpeed = maxSpeed;
            this.maxAcceleration = maxAcceleration;
            this.maxAngularSpeed = maxAngularSpeed;
            this.drag = drag;
            this.radius = radius;
            this.weapons.addAll(weapons);
        }

        /*SETTERS & GETTERS------------------------------------------------*/
        /**
         * Returns this model ID.
         * @return this model ID.
         */
        public EntityID getModelID() {
            return this.modelID;
        }
        /*-----------------------------------------------------------------*/
    }

}

