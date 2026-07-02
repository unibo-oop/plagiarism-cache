package it.unibo.oop.bounce.obstacles;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.physics.box2d.Fixture;

import it.unibo.oop.bounce.ball.Ball;
import it.unibo.oop.bounce.game.Bounce;
import it.unibo.oop.bounce.manager.Manager;
import it.unibo.oop.bounce.screens.PlayScreen;
import it.unibo.oop.bounce.screens.WinScreen;

public class FinishBlock implements Obstacles {
	
	private Fixture fixture;
	private Bounce game;
	private static final int TYPE = 9;
	
	public FinishBlock(final PlayScreen playscreen, final float x, final float y, final MapObject rObject, final Bounce game) {
		TypeDefinerImpl typeDef = new TypeDefinerImpl(playscreen, x, y, rObject);
		typeDef.setEntity(1, TYPE);
		typeDef.setCategoryFilter(Manager.FINISH_BLOCK_ID);
		fixture = typeDef.getFixture();
		fixture.setUserData(this);
		this.game = game;

	}

	@Override
	public final void onCollide(final Ball ball) {
		game.setScreen(new WinScreen(game));
	}

}
