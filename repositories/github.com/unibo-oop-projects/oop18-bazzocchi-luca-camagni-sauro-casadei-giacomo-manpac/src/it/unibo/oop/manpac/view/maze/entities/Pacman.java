package it.unibo.oop.manpac.view.maze.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import it.unibo.oop.manpac.utils.UtilsForUI;
import it.unibo.oop.manpac.view.maze.Collision;

/**
 * Class for the management of the main entity pacman.
 */

public final class Pacman extends MobileEntities {

    private static final float SPEED = 2500;

    /**
     * Constructor of the Pacman class.
     * 
     * @param world The world in which to create pacman
     * @param spawn The initial spawn point
     */
    public Pacman(final World world, final Vector2 spawn) {
        super(world, spawn);
        this.setSpeed(SPEED);
        definePacman();
    }

    private void definePacman() {
        final Body pacmanBody = this.getBody();

        // the form of pacman for collisions
        final CircleShape shape = new CircleShape();
        shape.setRadius(UtilsForUI.MOBILE_ENTITIES_RADIUS);

        // a fixture definition is used to create the physics of the body in question
        final FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        // the bit that identifies pacman in collisions
        fixtureDef.filter.categoryBits = Collision.PACMAN_BIT;
        // everything that pacman can collide with
        fixtureDef.filter.maskBits = Collision.WALL_BIT | Collision.GATE_BIT | Collision.PILL_BIT | Collision.PORTAL_BIT
                | Collision.POWER_BIT | Collision.PHANTOM_BIT;
        pacmanBody.createFixture(fixtureDef).setUserData(this);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return super.hashCode() + prime;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        return getClass() == obj.getClass();
    }

}
