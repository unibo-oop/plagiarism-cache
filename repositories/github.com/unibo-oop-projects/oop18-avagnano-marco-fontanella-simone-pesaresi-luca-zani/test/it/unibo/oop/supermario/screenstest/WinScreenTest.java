package it.unibo.oop.supermario.screenstest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import it.unibo.oop.supermario.game.SuperMario;
import it.unibo.oop.supermario.screens.SettingScreen;
import it.unibo.oop.supermario.screens.WinScreen;

public class WinScreenTest {

    private WinScreen winscreen;
    private SuperMario game;

    @Before
    public void setUp() throws Exception {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.disableAudio = true;
        LwjglApplication a = new LwjglApplication(new SuperMario(), config);
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                game = new SuperMario();
                winscreen = new WinScreen(game);
            }
        });
    }

    @Test
    public void testHashCode() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                assertNotNull(winscreen.hashCode());
            }
        });
    }


}
