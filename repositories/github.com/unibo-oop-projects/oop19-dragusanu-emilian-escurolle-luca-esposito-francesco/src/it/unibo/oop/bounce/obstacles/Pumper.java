package it.unibo.oop.bounce.obstacles;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.physics.box2d.Fixture;

import it.unibo.oop.bounce.ball.Ball;
import it.unibo.oop.bounce.manager.Manager;
import it.unibo.oop.bounce.screens.PlayScreen;

public class Pumper implements Obstacles {
	
	private Fixture fixture;
	private static final int TYPE = 11;

	public Pumper(final PlayScreen playscreen, final float x, final float y, final MapObject rObject) {
		TypeDefinerImpl typeDef = new TypeDefinerImpl(playscreen, x, y, rObject);
		typeDef.setEntity(1, TYPE);
		typeDef.setCategoryFilter(Manager.PUMPER_ID);
		fixture = typeDef.getFixture();
		fixture.setUserData(this);
	}

	@Override
	public final void onCollide(final Ball ball) {
		ball.big();

	}

}
