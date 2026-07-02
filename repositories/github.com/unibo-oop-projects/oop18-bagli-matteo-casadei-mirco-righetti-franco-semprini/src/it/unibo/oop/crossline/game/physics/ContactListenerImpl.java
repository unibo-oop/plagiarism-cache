package it.unibo.oop.crossline.game.physics;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

import it.unibo.oop.crossline.game.actor.Actor;
import it.unibo.oop.crossline.game.actor.player.Player;
import it.unibo.oop.crossline.game.bullet.Bullet;
import it.unibo.oop.crossline.game.world.platform.Platform;
import it.unibo.oop.crossline.game.world.spike.Spike;

/**
 * This class detects and handles the collisions between two objects.
 */
public class ContactListenerImpl implements ContactListener {

    @Override
    public final void beginContact(final Contact contact) {
        final Object objectA = contact.getFixtureA().getBody().getUserData();
        final Object objectB = contact.getFixtureB().getBody().getUserData();

        /* Player */
        if (objectA instanceof Player && objectB instanceof Platform) {
            final Player player = (Player) objectA;
            player.setJumpState(false);
        }

        if (objectB instanceof Player && objectA instanceof Platform) {
            final Player player = (Player) objectB;
            player.setJumpState(false);
        }

        if (objectA instanceof Player && objectB instanceof Spike) {
            final Player player = (Player) objectA;
            player.queueForDestruction();
        }

        if (objectB instanceof Player && objectA instanceof Spike) {
            final Player player = (Player) objectB;
            player.queueForDestruction();
        }

        /* Bullets */
        if (objectA instanceof Bullet) {
            final Bullet bullet = (Bullet) (objectA);
            bullet.queueForDestruction();
            if (objectB instanceof Actor) {
                final Actor actor = (Actor) objectB;
                if (bullet.getOwner().getTeam() != actor.getTeam()) {
                    actor.applyDamage(bullet.getDamage());
                }
            }
        }
        if (objectB instanceof Bullet) {
            final Bullet bullet = (Bullet) (objectB);
            bullet.queueForDestruction();
            if (objectA instanceof Actor) {
                final Actor actor = (Actor) objectA;
                if (bullet.getOwner().getTeam() != actor.getTeam()) {
                    actor.applyDamage(bullet.getDamage());
                }
            }
        }
    }

    @Override
    public final void endContact(final Contact contact) {
    }

    @Override
    public final void postSolve(final Contact contact, final ContactImpulse contactImpulse) {
    }

    @Override
    public final void preSolve(final Contact contact, final Manifold manifold) {
    }
}
