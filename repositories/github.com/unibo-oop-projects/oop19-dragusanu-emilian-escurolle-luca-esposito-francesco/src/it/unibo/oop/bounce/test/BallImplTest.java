package it.unibo.oop.bounce.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import it.unibo.oop.bounce.ball.BallImpl;
import it.unibo.oop.bounce.game.Bounce;
import it.unibo.oop.bounce.screens.PlayScreen;

public class BallImplTest {
	
	private Bounce game;
	private BallImpl ball;
	private float x = 10;
	private float y = 10;
	private PlayScreen ps;

	@Before
    public void setUp() throws Exception {
        LwjglApplicationConfiguration conf = new LwjglApplicationConfiguration();
        LwjglApplication app = new LwjglApplication(new Bounce(), conf);
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                game = new Bounce();
                ps = new PlayScreen(game);
                ball = new BallImpl(ps, x, y, game);
            }
        });
    }
	
	@Test
    public void testGetter() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                assertNotNull(ball);
                assertNotNull(ball.getBody());
                assertNotNull(ball.getPosition());
                assertNotNull(ball.getState());
                assertNotNull(ball.getHud());
                assertNotNull(ball.getWorld());
            }
        });
    }
	
	@Test
	public void testSetter() {
		Gdx.app.postRunnable(new Runnable() {
			public void run() {
				assertFalse(ball.isJumping());
				ball.setJumping(true);
				assertTrue(ball.isJumping());
				assertFalse(ball.isBig());
				ball.setBig();
				assertTrue(ball.isBig());
				assertFalse(ball.isSmall());
				ball.setSmall();
				assertTrue(ball.isSmall());
			}
		});
	}
}
