package it.unibo.oop.manpac.view.maze;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import it.unibo.oop.manpac.model.Entity;
import it.unibo.oop.manpac.view.maze.entities.PhantomImpl;
import it.unibo.oop.manpac.view.screens.game.GameView;

/**
 * Collision listener that implements ContactListener from
 * {@link ContactListener}.
 */
public class Collision implements ContactListener {

    /**
     * Collision bit used in fixture filters for recognizing walls.
     */
    public static final int WALL_BIT = 1;
    /**
     * Collision bit used in fixture filters for recognizing pills.
     */
    public static final int PILL_BIT = 2;
    /**
     * Collision bit used in fixture filters for recognizing portals.
     */
    public static final int PORTAL_BIT = 4;
    /**
     * Collision bit used in fixture filters for recognizing gates.
     */
    public static final int GATE_BIT = 8;
    /**
     * Collision bit used in fixture filters for recognizing pacman.
     */
    public static final int PACMAN_BIT = 16;
    /**
     * Collision bit used in fixture filters for recognizing phantoms.
     */
    public static final int PHANTOM_BIT = 32;
    /**
     * Collision bit used in fixture filters for recognizing power pills.
     */
    public static final int POWER_BIT = 64;
    /**
     * Collision bit used in fixture filters for recognizing spawnPoints.
     */
    public static final int SPAWN_BIT = 128;

    private final GameView view;

    /**
     * Constructor for Collision class.
     * 
     * @param view The main GameView
     */
    public Collision(final GameView view) {
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void beginContact(final Contact contact) {
        // In a collision, a contact class is created, which contains the two fixture
        // that are colliding
        final Fixture fixA = contact.getFixtureA();
        final Fixture fixB = contact.getFixtureB();
        // I'll save the categoryFilters of both fixtures in cDef, for an easier switch
        final int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch (cDef) {
        case WALL_BIT | PACMAN_BIT:
            // For each case, i need to check who each fixtures represents
            if (fixA.getFilterData().categoryBits == PACMAN_BIT) {
                // then i'll send the data gathered to the GameView via the collisionEvent
                // method
                view.collisionEvent(Entity.PACMAN, ((MazeEntity) fixB.getUserData()).getEntity());
            } else {
                view.collisionEvent(Entity.PACMAN, ((MazeEntity) fixA.getUserData()).getEntity());
            }
            break;

        case PILL_BIT | PACMAN_BIT:
            if (fixA.getFilterData().categoryBits == PACMAN_BIT) {
                view.collisionEvent(Entity.PACMAN, ((MazeEntity) fixB.getUserData()).getEntity());
                view.setToDestroy(fixB.getBody(), fixB,
                        ((MazeEntity) fixB.getUserData()).getCell(MazeCreatorImpl.PILLS_TILES_LAYER));
            } else {
                view.collisionEvent(Entity.PACMAN, ((MazeEntity) fixA.getUserData()).getEntity());
                view.setToDestroy(fixA.getBody(), fixA,
                        ((MazeEntity) fixA.getUserData()).getCell(MazeCreatorImpl.PILLS_TILES_LAYER));
            }
            break;

        case POWER_BIT | PACMAN_BIT:
            if (fixA.getFilterData().categoryBits == PACMAN_BIT) {
                view.collisionEvent(Entity.PACMAN, ((MazeEntity) fixB.getUserData()).getEntity());
                view.setToDestroy(fixB.getBody(), fixB,
                        ((MazeEntity) fixB.getUserData()).getCell(MazeCreatorImpl.POWER_PILLS_TILES_LAYER));
            } else {
                view.collisionEvent(Entity.PACMAN, ((MazeEntity) fixA.getUserData()).getEntity());
                view.setToDestroy(fixA.getBody(), fixA,
                        ((MazeEntity) fixA.getUserData()).getCell(MazeCreatorImpl.POWER_PILLS_TILES_LAYER));
            }
            break;

        case PORTAL_BIT | PACMAN_BIT:
            if (fixA.getFilterData().categoryBits == PACMAN_BIT) {
                view.collisionEvent(Entity.PACMAN, ((MazeEntity) fixB.getUserData()).getEntity());
            } else {
                view.collisionEvent(Entity.PACMAN, ((MazeEntity) fixA.getUserData()).getEntity());
            }
            break;

        case GATE_BIT | PACMAN_BIT:
            if (fixA.getFilterData().categoryBits == PACMAN_BIT) {
                view.collisionEvent(Entity.PACMAN, ((MazeEntity) fixB.getUserData()).getEntity());
            } else {
                view.collisionEvent(Entity.PACMAN, ((MazeEntity) fixA.getUserData()).getEntity());
            }
            break;

        case WALL_BIT | PHANTOM_BIT:
        case GATE_BIT | PHANTOM_BIT:
        case PORTAL_BIT | PHANTOM_BIT:
            if (fixA.getFilterData().categoryBits == PHANTOM_BIT) {
                view.collisionEvent(((PhantomImpl) fixA.getUserData()).getName(),
                        ((MazeEntity) fixB.getUserData()).getEntity());
            } else {
                view.collisionEvent(((PhantomImpl) fixB.getUserData()).getName(),
                        ((MazeEntity) fixA.getUserData()).getEntity());
            }
            break;

        case PACMAN_BIT | PHANTOM_BIT:
            if (fixA.getFilterData().categoryBits == PHANTOM_BIT) {
                view.collisionEvent(Entity.PACMAN, ((PhantomImpl) fixA.getUserData()).getName());
            } else {
                view.collisionEvent(Entity.PACMAN, ((PhantomImpl) fixB.getUserData()).getName());
            }
            break;

        default:
            view.collisionEvent(Entity.PACMAN, Entity.PACMAN);
            break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endContact(final Contact contact) {
        // method that do something when two fxture stop colliding, not implemented in
        // this version of the game
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void preSolve(final Contact contact, final Manifold oldManifold) {
        // method that do something the instant before two fixture begin to collide, not
        // implemented in this version of the game
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void postSolve(final Contact contact, final ContactImpulse impulse) {
        // method that do something the instant before two fixture stop colliding, not
        // implemented in this version of the game
    }

}
