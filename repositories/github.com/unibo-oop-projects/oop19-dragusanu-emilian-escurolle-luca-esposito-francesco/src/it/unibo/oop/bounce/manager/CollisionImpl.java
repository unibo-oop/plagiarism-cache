package it.unibo.oop.bounce.manager;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import it.unibo.oop.bounce.ball.Ball;
import it.unibo.oop.bounce.obstacles.Obstacles;

public class CollisionImpl implements ContactListener {

	@Override
	public final void beginContact(final Contact collision) {
		final Fixture fixtureA = collision.getFixtureA();
		final Fixture fixtureB = collision.getFixtureB();

		final int collisionAB = fixtureA.getFilterData().categoryBits | fixtureB.getFilterData().categoryBits;

		switch (collisionAB) {
		case Manager.BOUNCE_ID | Manager.BLOCK_ID:
			break;
		case Manager.BOUNCE_ID | Manager.CHECKPOINT_ID:
			if (fixtureA.getFilterData().categoryBits == Manager.CHECKPOINT_ID) {
				((Obstacles) fixtureA.getUserData()).onCollide((Ball) fixtureB.getUserData());
			} else {
				((Obstacles) fixtureB.getUserData()).onCollide((Ball) fixtureA.getUserData());
			}
			break;
		case Manager.BOUNCE_ID | Manager.THORN_ID:
			if (fixtureA.getFilterData().categoryBits == Manager.THORN_ID) {
				((Obstacles) fixtureA.getUserData()).onCollide((Ball) fixtureB.getUserData());
			} else {
				((Obstacles) fixtureB.getUserData()).onCollide((Ball) fixtureA.getUserData());
			}
			break;
		case Manager.BOUNCE_ID | Manager.LIFE_ID:
			if (fixtureA.getFilterData().categoryBits == Manager.LIFE_ID) {
				((Obstacles) fixtureA.getUserData()).onCollide((Ball) fixtureB.getUserData());
			} else {
				((Obstacles) fixtureB.getUserData()).onCollide((Ball) fixtureA.getUserData());
			}
			break;
		case Manager.BOUNCE_ID | Manager.DEFLATER_ID:
			if (fixtureA.getFilterData().categoryBits == Manager.DEFLATER_ID) {
				((Obstacles) fixtureA.getUserData()).onCollide((Ball) fixtureB.getUserData());
			} else {
				((Obstacles) fixtureB.getUserData()).onCollide((Ball) fixtureA.getUserData());
			}
			break;
		case Manager.BOUNCE_ID | Manager.PUMPER_ID:
			if (fixtureA.getFilterData().categoryBits == Manager.PUMPER_ID) {
				((Obstacles) fixtureA.getUserData()).onCollide((Ball) fixtureB.getUserData());
			} else {
				((Obstacles) fixtureB.getUserData()).onCollide((Ball) fixtureA.getUserData());
			}
			break;
		case Manager.BOUNCE_ID | Manager.RING_ID:
			if (fixtureA.getFilterData().categoryBits == Manager.RING_ID) {
				((Obstacles) fixtureA.getUserData()).onCollide((Ball) fixtureB.getUserData());
			} else {
				((Obstacles) fixtureB.getUserData()).onCollide((Ball) fixtureA.getUserData());
			}
			break;
		case Manager.BOUNCE_ID | Manager.END_BLOCK_ID:
			break;
		case Manager.BOUNCE_ID | Manager.FINISH_BLOCK_ID:
			if (fixtureB.getFilterData().categoryBits == Manager.FINISH_BLOCK_ID) {
				((Obstacles) fixtureB.getUserData()).onCollide((Ball) fixtureA.getUserData());
			} else {
				((Obstacles) fixtureA.getUserData()).onCollide((Ball) fixtureB.getUserData());
			}
			break;
		case Manager.BOUNCE_ID | Manager.HALFBLOCK:
			break;
		default:
			break;
		}
	}

	@Override
	public void endContact(final Contact collision) {
	}

	@Override
	public void postSolve(final Contact collision, final ContactImpulse contactImpulse) {
	}

	@Override
	public void preSolve(final Contact collision, final Manifold manifold) {
	}

}
