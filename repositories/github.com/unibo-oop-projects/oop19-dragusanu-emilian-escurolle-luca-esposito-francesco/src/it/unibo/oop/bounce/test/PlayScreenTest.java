package it.unibo.oop.bounce.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import it.unibo.oop.bounce.game.Bounce;
import it.unibo.oop.bounce.screens.PlayScreen;


public class PlayScreenTest {
    private PlayScreen playscreen;
    private Bounce game;

    @Before
    public void setUp() throws Exception {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        //config.disableAudio = true;
        LwjglApplication app = new LwjglApplication(new Bounce(), config);
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                game = new Bounce();
                playscreen = new PlayScreen(game);
            }
        });
    }

    @Test
    public void testIterations() {
        assertNotNull(PlayScreen.VEL_ITERATION);
        assertNotNull(PlayScreen.POS_ITERATION);

    }

    @Test
    public void testTimeStep() {
        assertNotNull(playscreen.FREQUENCY);
    }
    @Test
    public void testUpdate() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
        assertNotNull(playscreen.getCamera());
        assertNotNull(playscreen.getMap());
            }
        });
    }
    @Test
    public void testGetWorld() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
            	assertNotNull(playscreen);
            	assertNotNull(playscreen.getWorld());
            }
        });
    }
    @Test
    public void testGetMap() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
        assertNotNull(playscreen.getMap());
            }
        });
    }
    @Test
    public void testGetPlayer() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
        assertNotNull(playscreen.getPlayer());
    }
});
    }
}
