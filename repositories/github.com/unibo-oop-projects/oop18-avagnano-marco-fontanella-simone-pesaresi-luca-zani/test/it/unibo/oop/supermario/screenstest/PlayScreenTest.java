package it.unibo.oop.supermario.screenstest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import it.unibo.oop.supermario.game.SuperMario;
import it.unibo.oop.supermario.screens.PlayScreen;
import it.unibo.oop.supermario.screens.SettingScreen;

public class PlayScreenTest {
    private PlayScreen playscreen;
    private SuperMario game;

    @Before
    public void setUp() throws Exception {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.disableAudio = true;
        LwjglApplication a = new LwjglApplication(new SuperMario(), config);
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                game = new SuperMario();
                playscreen = new PlayScreen(game);
            }
        });
    }

    @Test
    public void testIterations() {
        assertNotNull(playscreen.VEL_ITERATIONS);
        assertNotNull(playscreen.POS_ITERATIONS);

    }

    @Test
    public void testTimeStep() {
        assertNotNull(playscreen.TIME_STEP);
    }
    
    @Test
    public void testUpdate() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
        assertNotNull(playscreen.getCamera());
        assertNotNull( playscreen.getMap());
            }
        });
    }
    
    @Test
    public void testGetWorld() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
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
    public void testGetMarioController() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
        assertNotNull(playscreen.getMarioController());
    }
});
    }
}
