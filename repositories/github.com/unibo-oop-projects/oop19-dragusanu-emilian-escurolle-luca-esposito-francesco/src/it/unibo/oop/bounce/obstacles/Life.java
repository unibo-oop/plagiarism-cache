package it.unibo.oop.bounce.obstacles;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.physics.box2d.Fixture;

import it.unibo.oop.bounce.ball.Ball;
import it.unibo.oop.bounce.manager.Manager;
import it.unibo.oop.bounce.screens.PlayScreen;

public class Life implements Obstacles {
	
	private Fixture fixture;
	private static final int TYPE = 8;

	public Life(final PlayScreen playscreen, final float x, final float y, final MapObject pObject) {
		TypeDefinerImpl typeDef = new TypeDefinerImpl(playscreen, x, y, pObject);
		typeDef.setEntity(2, TYPE);
		typeDef.setCategoryFilter(Manager.LIFE_ID);
		fixture = typeDef.getFixture();
		fixture.setUserData(this);
	}

	@Override
	public void onCollide(final Ball ball) {
	}

}
