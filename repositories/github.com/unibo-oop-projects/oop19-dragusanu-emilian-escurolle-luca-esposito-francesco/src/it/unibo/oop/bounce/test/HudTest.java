package it.unibo.oop.bounce.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import it.unibo.oop.bounce.game.Bounce;
import it.unibo.oop.bounce.screens.Hud;

public class HudTest {
    private Hud hud;
    private Bounce game;

    @Before
    public void setUp() throws Exception {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        //config.disableAudio = true;
        LwjglApplication a = new LwjglApplication(new Bounce(), config);
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                game = new Bounce();
                hud = new Hud(game.batch);
            }
        });
    }

    @Test
    public void testGetLife() {
    	Gdx.app.postRunnable(new Runnable() {
    		public void run() {
    			assertNotNull(hud.getLifeCounter());
    		}
    	});
    }
    
    @Test
    public void testRingCounter() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
        assertNotNull(hud.getRingCounter());
            }
        });
    }
}
