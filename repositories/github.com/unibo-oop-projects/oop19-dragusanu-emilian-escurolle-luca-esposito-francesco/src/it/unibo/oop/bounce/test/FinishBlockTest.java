package it.unibo.oop.bounce.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.maps.MapObject;

import it.unibo.oop.bounce.obstacles.FinishBlock;
import it.unibo.oop.bounce.game.Bounce;
import it.unibo.oop.bounce.screens.PlayScreen;

public class FinishBlockTest {
	
	private FinishBlock finishBlock;
	private float x = 10;
	private float y = 10;
	private Bounce game;
	private PlayScreen ps;
	private MapObject rObject;
	
	@Before
    public void setUp() throws Exception {
        LwjglApplicationConfiguration conf = new LwjglApplicationConfiguration();
        LwjglApplication app = new LwjglApplication(new Bounce(), conf);
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                ps = new PlayScreen((Bounce) app.getApplicationListener());
                game = new Bounce();
                finishBlock = new FinishBlock(ps, x, y, rObject, game);
            }
        });
	}
	
	@Test
	public void testExists() {
		Gdx.app.postRunnable(new Runnable() {
			public void run() {
				assertNotNull(finishBlock);
			}
		});
	}
}
