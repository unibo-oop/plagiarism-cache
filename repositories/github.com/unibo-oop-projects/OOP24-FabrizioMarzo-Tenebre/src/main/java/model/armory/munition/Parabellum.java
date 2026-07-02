package model.armory.munition;

import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import model.bounding_box.BoundingBox;
import utils.PairUtils;

/**
 * Implementation of the {@link Munition} interface representing a Parabellum
 * bullet.
 * <p>
 * This munition has properties like damage, velocity, width, position,
 * direction, and bounding box.
 * It supports being shot, moving over time, and updating its position and
 * bounding box accordingly.
 */
public class Parabellum implements Munition {

    private static final double MILLISECONDS_TO_SECONDS = 0.001;

    private int damage;
    private int width;
    private int velocity;
    private Boolean isShoot = false;
    private Pair<Double, Double> pos;
    private Optional<Pair<Double, Double>> dir;
    private BoundingBox bbox;

    /**
     * Constructs a new Parabellum munition.
     * 
     * @param damage   the damage inflicted by this munition
     * @param velocity the speed of the munition (units per second)
     * @param width    the width of the munition (for collision and rendering)
     * @param pos      the initial position of the munition (x, y)
     * @param bbox     the bounding box associated with the munition
     */
    public Parabellum(final int damage, final int velocity, final int width, final Pair<Double, Double> pos,
            final BoundingBox bbox) {
        this.damage = damage;
        this.width = width;
        this.velocity = velocity;
        this.pos = pos;
        this.bbox = bbox;
        this.dir = Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDamage() {
        return this.damage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BoundingBox getBBox() {
        return this.bbox;
    }

    /**
     * Sets the munition as shot with the specified shooting direction and starting
     * position.
     * The direction vector is normalized internally.
     * 
     * @param dirShoot the shooting direction vector (x, y)
     * @param posShoot the starting position of the munition (x, y)
     */
    @Override
    public void setShoot(final Pair<Double, Double> dirShoot, final Pair<Double, Double> posShoot) {
        this.dir = Optional.ofNullable(PairUtils.normalize(dirShoot));
        this.pos = posShoot;
        this.isShoot = true;
    }

    /**
     * Moves the munition based on its velocity and the elapsed time in
     * milliseconds.
     * Updates the position and bounding box accordingly.
     * 
     * @param dt the elapsed time in milliseconds since last update
     * @throws IllegalStateException if the shooting direction was not initialized
     *                               before moving
     */
    @Override
    public void moveShoot(final int dt) {
        Pair<Double, Double> direction = dir.orElseThrow(() -> new IllegalStateException("Direction not inizialize"));

        Pair<Double, Double> displacement = PairUtils.mulScale(PairUtils.mulScale(direction, this.velocity),
                MILLISECONDS_TO_SECONDS * dt);

        this.setPos(PairUtils.sum(this.pos, displacement));
        this.bbox.updateBBox(this.pos);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isShoot() {
        return this.isShoot;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPos(final Pair<Double, Double> nextPos) {
        this.pos = nextPos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Double, Double> getCurrentPos() {
        return this.pos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return this.width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + damage;
        result = prime * result + width;
        result = prime * result + velocity;
        result = prime * result + ((isShoot == null) ? 0 : isShoot.hashCode());
        result = prime * result + ((pos == null) ? 0 : pos.hashCode());
        result = prime * result + ((dir == null) ? 0 : dir.hashCode());
        result = prime * result + ((bbox == null) ? 0 : bbox.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Parabellum other = (Parabellum) obj;
        if (damage != other.damage)
            return false;
        if (width != other.width)
            return false;
        if (velocity != other.velocity)
            return false;
        if (isShoot == null) {
            if (other.isShoot != null)
                return false;
        } else if (!isShoot.equals(other.isShoot))
            return false;
        if (pos == null) {
            if (other.pos != null)
                return false;
        } else if (!pos.equals(other.pos))
            return false;
        if (dir == null) {
            if (other.dir != null)
                return false;
        } else if (!dir.equals(other.dir))
            return false;
        if (bbox == null) {
            if (other.bbox != null)
                return false;
        } else if (!bbox.equals(other.bbox))
            return false;
        return true;
    }

}
