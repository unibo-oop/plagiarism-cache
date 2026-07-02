package it.unibo.oop.supermario.screenstest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import it.unibo.oop.supermario.game.SuperMario;
import it.unibo.oop.supermario.gamemanager.GameManager;
import it.unibo.oop.supermario.screens.ChooseScreen;
import it.unibo.oop.supermario.screens.SettingScreen;

public class SettingScreenTest {
    private SettingScreen setting;
    private SuperMario game;

    @Before
    public void setUp() throws Exception {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.disableAudio = true;
        LwjglApplication a = new LwjglApplication(new SuperMario(), config);
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                game = new SuperMario();
                setting = new SettingScreen(game);
            }
        });
    }

    @Test
    public void testupdate() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                assertNotNull(setting.getCurrentItem());
            }
        });
    }

    @Test
    public void testHandleInput() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                assertNotNull(setting.getCurrentItem());
            }
        });
    }

    @Test
    public void testSelect() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                assertNotNull(setting.getCurrentItem());
            }
        });
    }

}
