package model.entities.survivor;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import model.armory.munition.Munition;
import model.armory.weapon.Weapon;
import model.bounding_box.BoundingBox;
import model.physics.physics_entities.PhysicsSurvivorComponent;

/**
 * Implementation of the {@link Survivor} interface representing a common
 * survivor in the model.
 * <p>
 * This class encapsulates the state and behavior of a survivor including
 * health,
 * position, velocity, weapon, and state management. It also interacts with the
 * physics component to update movement.
 */
public class Common implements Survivor {

    private static final int MIN_HEALTH = 0;

    private int width;
    private int height;
    private int live;
    private Pair<Double, Double> pos;
    private Pair<Double, Double> vel;
    private final Pair<Double, Double> velBase;
    private final int maxLife;
    private SurvivorState state;
    private BoundingBox bbox;
    private PhysicsSurvivorComponent physicComp;
    private Weapon weapon;
    private boolean isDead;

    /**
     * Constructs a new {@code Common} survivor with the specified attributes.
     *
     * @param health     initial health points
     * @param width      width dimension in model units
     * @param height     height dimension in model units
     * @param pos        initial position as a pair (x, y)
     * @param vel        initial velocity as a pair (vx, vy), also used as base
     *                   velocity
     * @param physicComp physics component handling survivor's physical behavior
     * @param bbox       bounding box used for collision detection
     */
    public Common(final int health,
            final int width, final int height,
            final Pair<Double, Double> pos, final Pair<Double, Double> vel,
            final PhysicsSurvivorComponent physicComp,
            final BoundingBox bbox) {

        this.live = health;
        this.width = width;
        this.height = height;
        this.pos = pos;
        this.vel = vel;
        this.velBase = vel;
        this.maxLife = health;
        this.state = SurvivorState.SURVIVOR_IDLE;
        this.bbox = bbox;
        this.physicComp = physicComp;
        this.isDead = false;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Applies damage to the survivor, ensuring health does not fall below zero.
     */
    @Override
    public void damageSuffer(final int dm) {
        this.live = Math.max(MIN_HEALTH, this.live - dm);

        if (this.live == 0) {
            this.isDead = true;
        }

    }

    /**
     * {@inheritDoc}
     * <p>
     * Updates the survivor's physics state through the associated physics
     * component.
     *
     * @param dt elapsed time since last update
     */
    @Override
    public void updatePhysics(final int dt) {
        physicComp.updateSurvivor(this, dt);
    }

    /**
     * {@inheritDoc}
     * Sets the survivor's velocity.
     */
    @Override
    public void setVelocity(final Pair<Double, Double> vel) {
        this.vel = vel;
    }

    /**
     * {@inheritDoc}
     * Sets the survivor's position and updates the bounding box accordingly.
     */
    @Override
    public void setPosition(final Pair<Double, Double> pos) {
        this.pos = pos;
        this.bbox.updateBBox(pos);
    }

    /**
     * {@inheritDoc}
     * Sets the survivor's current state.
     */
    @Override
    public void setState(final SurvivorState newState) {
        this.state = newState;
    }

    /**
     * {@inheritDoc}
     * Sets the survivor's equipped weapon.
     */
    @Override
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * {@inheritDoc}
     * Returns the survivor's current health.
     */
    @Override
    public int getLive() {
        return this.live;
    }

    /**
     * {@inheritDoc}
     * Returns the survivor's width.
     */
    @Override
    public int getWidth() {
        return this.width;
    }

    /**
     * {@inheritDoc}
     * Returns the survivor's height.
     */
    @Override
    public int getHeight() {
        return this.height;
    }

    /**
     * {@inheritDoc}
     * Returns the survivor's current position.
     */
    @Override
    public Pair<Double, Double> getCurrentPos() {
        return this.pos;
    }

    /**
     * {@inheritDoc}
     * Returns the survivor's current velocity.
     */
    @Override
    public Pair<Double, Double> getCurrentVel() {
        return this.vel;
    }

    /**
     * {@inheritDoc}
     * Returns the base velocity of the survivor.
     */
    @Override
    public Pair<Double, Double> getBaseSurvivorVel() {
        return this.velBase;
    }

    /**
     * {@inheritDoc}
     * Returns the survivor's current state.
     */
    @Override
    public SurvivorState getState() {
        return this.state;
    }

    /**
     * {@inheritDoc}
     * Returns the survivor's equipped weapon.
     */
    @Override
    public Weapon getWeapon() {
        return this.weapon;
    }

    /**
     * {@inheritDoc}
     * Returns the survivor's bounding box.
     */
    @Override
    public BoundingBox getBBox() {
        return this.bbox;
    }

    /**
     * {@inheritDoc}
     * Returns the maximum health of the survivor.
     */
    @Override
    public int getMaxSurvivorHealth() {
        return this.maxLife;
    }

    /**
     * {@inheritDoc}
     * Triggers the weapon shooting mechanism and returns the list of projectiles
     * fired.
     *
     * @param deltaTime time elapsed since last shot
     * @return a list of {@link Munition} fired; empty if no weapon equipped
     */
    @Override
    public List<Munition> shoot(final double deltaTime) {
        return this.weapon != null ? this.weapon.shoot(deltaTime) : List.of();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSurvivorDead() {
        return this.isDead;
    }

    /**
     * Computes the hash code based on the survivor's current health.
     *
     * @return hash code value
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + live;
        return result;
    }

    /**
     * Checks equality based on the survivor's health.
     * <p>
     * Two {@code Common} survivors are considered equal if they have the same
     * health.
     *
     * @param obj the object to compare to
     * @return {@code true} if equal, {@code false} otherwise
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Common other = (Common) obj;
        if (live != other.live)
            return false;
        return true;
    }

}
