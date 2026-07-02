package it.unibo.oop.bounce.obstacles;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.physics.box2d.Fixture;

import it.unibo.oop.bounce.screens.PlayScreen;
import it.unibo.oop.bounce.ball.Ball;
import it.unibo.oop.bounce.manager.Manager;
import it.unibo.oop.bounce.manager.State;



public class HalfBlock implements Obstacles  {
	
	private Fixture fixture;
	private static final int TYPE = 10;
	private static final float SIZE = 0.2f; 
	
	
	
	public HalfBlock(final PlayScreen playscreen, final float x, final float y, final MapObject rObject) {
	
		final TypeDefinerImpl typeDef = new TypeDefinerImpl(playscreen, x, y, rObject);
		typeDef.setEntity(2, TYPE);
		typeDef.setCategoryFilter(Manager.BLOCK_ID);
        fixture = typeDef.getFixture();
        fixture.setUserData(this);
	}


	@Override
	public final void onCollide(final Ball ball) {
		if (ball.getState() == State.FALLING) {
			this.fixture.setRestitution(SIZE);
		}
	}

}
