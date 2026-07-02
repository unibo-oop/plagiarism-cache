package it.unibo.oop.manpac.view.maze.entities;

import java.util.Objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import it.unibo.oop.manpac.model.Directions;

/**
 * Class that generalizes the functioning of a mobile entity.
 */

public class MobileEntities implements Entities {

    private final World world;
    private Body entityBody;

    private Directions direction;

    private Vector2 movement = new Vector2();

    // avoid collision bugs
    private static final float DEFAULT_START_SPEED = 0.1f;
    private float speed;

    private final Vector2 spawnPoint;

    /**
     * Constructor of MobileEntities.
     * 
     * @param world The world to create the Entity.
     * @param spawn The initial spawn point.
     */
    public MobileEntities(final World world, final Vector2 spawn) {
        this.world = Objects.requireNonNull(world);
        this.spawnPoint = Objects.requireNonNull(spawn);
        this.direction = Directions.STOP;
        this.speed = DEFAULT_START_SPEED;
        this.stopMovement();
        this.defineMobileEntity();
    }

    private void defineMobileEntity() {
        final BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        entityBody = world.createBody(bdef);
        this.setPosition(this.spawnPoint);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Body getBody() {
        return this.entityBody;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2 getPosition() {
        return new Vector2(this.entityBody.getPosition());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final Vector2 position) {
        // setTransform want the position and the angle
        this.entityBody.setTransform(Objects.requireNonNull(position), 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPositionToSpawnPoint() {
        // setTransform want the position and the angle
        this.entityBody.setTransform(this.spawnPoint, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDirection(final Directions direction) {
        if (this.direction != Objects.requireNonNull(direction)) {
            this.direction = direction;
            this.changeMovement(this.direction);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Directions getDirection() {
        return this.direction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2 getMovement() {
        return this.movement;
    }

    // Method called for a change of direction that changes the movement of the
    // entity.
    private void changeMovement(final Directions direction) {
        this.stopMovement();
        switch (direction) {
        case UP:
            this.movement.y = this.speed;
            break;
        case DOWN:
            this.movement.y = -this.speed;
            break;
        case RIGHT:
            this.movement.x = this.speed;
            break;
        case LEFT:
            this.movement.x = -this.speed;
            break;
        default:
            this.stopMovement();
        }
    }

    private void stopMovement() {
        this.movement.x = 0;
        this.movement.y = 0;
    }

    /**
     * Set the speed of the Entity.
     * 
     * @param speed The new speed
     */
    protected void setSpeed(final float speed) {
        final float diff = speed / this.speed;
        this.speed = speed;
        this.movement.x *= diff;
        this.movement.y *= diff;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + direction.hashCode();
        result = prime * result + movement.hashCode();
        result = prime * result + spawnPoint.hashCode();
        result = prime * result + Float.floatToIntBits(speed);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MobileEntities other = (MobileEntities) obj;
        if (direction != other.direction) {
            return false;
        }
        if (!movement.equals(other.movement)) {
            return false;
        }
        if (!spawnPoint.equals(other.spawnPoint)) {
            return false;
        }
        return Float.floatToIntBits(speed) == Float.floatToIntBits(other.speed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "MobileEntities [world=" + world + ", entityBody=" + entityBody + ", direction=" + direction
                + ", movement=" + movement + ", speed=" + speed + ", spawnPoint=" + spawnPoint + "]";
    }

}
