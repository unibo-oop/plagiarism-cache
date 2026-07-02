package it.unibo.oop.bounce.obstacles;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.physics.box2d.Fixture;

import it.unibo.oop.bounce.screens.PlayScreen;
import it.unibo.oop.bounce.ball.Ball;
import it.unibo.oop.bounce.manager.Manager;


public class Block implements Obstacles {
	
	private Fixture fixture;

	public Block(final PlayScreen playscreen, final float x, final float y, final MapObject rObject) {
		final TypeDefinerImpl typeDef = new TypeDefinerImpl(playscreen, x, y, rObject);
		typeDef.setEntity(1, 4);
		typeDef.setCategoryFilter(Manager.BLOCK_ID);
		fixture = typeDef.getFixture();
		fixture.setUserData(this);
	}

	@Override
	public final void onCollide(final Ball ball) {
	}
}

