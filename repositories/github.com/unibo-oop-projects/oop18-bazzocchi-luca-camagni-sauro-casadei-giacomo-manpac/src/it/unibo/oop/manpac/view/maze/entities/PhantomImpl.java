package it.unibo.oop.manpac.view.maze.entities;

import java.util.Objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import it.unibo.oop.manpac.model.Directions;
import it.unibo.oop.manpac.model.Entity;
import it.unibo.oop.manpac.utils.UtilsForUI;
import it.unibo.oop.manpac.view.maze.Collision;

/**
 * Class for the management of the entity phantom.
 */
public final class PhantomImpl extends MobileEntities implements Phantom {

    private final Entity phantomName;

    private static final float SPEED = 4000;
    private static final float SPEED_DRECREASE_DEFAULT = 2.5f;

    private Directions secondLastDirection;

    private boolean scared;

    /**
     * Constructor of the Phantom class.
     * 
     * @param world The world in which to create the phantom
     * @param spawn The initial spawn point
     * @param name  The name of the phantom
     */
    public PhantomImpl(final World world, final Vector2 spawn, final Entity name) {
        super(world, spawn);
        this.phantomName = Objects.requireNonNull(name);
        this.secondLastDirection = Directions.STOP;
        this.setSpeed(SPEED);
        definePhantom();
    }

    private void definePhantom() {
        final Body phantomBody = this.getBody();

        // the form of phantom for collisions
        final CircleShape shape = new CircleShape();
        shape.setRadius(UtilsForUI.MOBILE_ENTITIES_RADIUS);

        // a fixture definition is used to create the physics of the body in question
        final FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        // the bit that identifies phantom in collisions
        fixtureDef.filter.categoryBits = Collision.PHANTOM_BIT;
        // everything that phantom can collide with
        fixtureDef.filter.maskBits = Collision.WALL_BIT | Collision.GATE_BIT | Collision.PACMAN_BIT
                | Collision.PORTAL_BIT;
        phantomBody.createFixture(fixtureDef).setUserData(this);
    }

    @Override
    public void setDirection(final Directions direction) {
        if (!this.getDirection().equals(Objects.requireNonNull(direction))) {
            this.secondLastDirection = this.getDirection();
            super.setDirection(direction);
        }
    }

    @Override
    public Entity getName() {
        return this.phantomName;
    }

    @Override
    public Directions getSecondLastDirection() {
        return this.secondLastDirection;
    }

    @Override
    public void setFear(final boolean fear) {
        if (this.scared != fear) {
            this.scared = fear;
            if (fear) {
                this.setSpeed(SPEED / SPEED_DRECREASE_DEFAULT);
            } else {
                this.setSpeed(SPEED);
            }
        }
    }

    @Override
    public boolean isScared() {
        return this.scared;
    }

    @Override
    public void goOut() {
        this.setPosition(new Vector2(UtilsForUI.PHANTOMS_SPAWN_POSITION_X, UtilsForUI.PHANTOMS_SPAWN_POSITION_Y));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + phantomName.hashCode();
        result = prime * result + (scared ? 0 : 1);
        result = prime * result + secondLastDirection.hashCode();
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PhantomImpl other = (PhantomImpl) obj;
        if (phantomName != other.phantomName) {
            return false;
        }
        if (scared != other.scared) {
            return false;
        }
        return secondLastDirection == other.secondLastDirection;
    }

}
