package it.unibo.oop.bounce.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import it.unibo.oop.bounce.game.Bounce;
import it.unibo.oop.bounce.screens.WinScreen;

public class WinScreenTest {

    private WinScreen winscreen;
    private Bounce game;

    @Before
    public void setUp() throws Exception {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.disableAudio = true;
        LwjglApplication app = new LwjglApplication(new Bounce(), config);
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                game = new Bounce();
                winscreen = new WinScreen(game);
            }
        });
    }

    @Test
    public void test() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                assertNotNull(winscreen.hashCode());
            }
        });
    }
}
