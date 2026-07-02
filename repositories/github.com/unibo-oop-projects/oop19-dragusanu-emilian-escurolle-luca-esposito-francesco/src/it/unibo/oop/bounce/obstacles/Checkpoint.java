package it.unibo.oop.bounce.obstacles;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.physics.box2d.Fixture;

import it.unibo.oop.bounce.ball.Ball;
import it.unibo.oop.bounce.manager.Manager;
import it.unibo.oop.bounce.screens.PlayScreen;

public class Checkpoint implements Obstacles {
	
	private float x;
	private float y;
	private Fixture fixture;
	private static final int TYPE = 5;

	public Checkpoint(final PlayScreen playscreen, final float x, final float y, final MapObject pObject) {
		TypeDefinerImpl typeDef = new TypeDefinerImpl(playscreen, x, y, pObject);
		typeDef.setEntity(2, TYPE);
		this.x = x;
		this.y = y;
		typeDef.setCategoryFilter(Manager.CHECKPOINT_ID);
		fixture = typeDef.getFixture();
		fixture.setUserData(this);
	}

	public final float getX(final Checkpoint checkpoint) {
		return checkpoint.x;
	}

	public final float getY(final Checkpoint checkpoint) {
		return checkpoint.y;
	}

	@Override
	public final void onCollide(final Ball ball) {
		ball.setRespawnX(x);
		ball.setRespawnY(y);

	}

}

