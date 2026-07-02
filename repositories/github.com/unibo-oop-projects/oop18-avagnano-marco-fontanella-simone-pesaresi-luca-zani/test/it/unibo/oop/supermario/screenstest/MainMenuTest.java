package it.unibo.oop.supermario.screenstest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import it.unibo.oop.supermario.game.SuperMario;
import it.unibo.oop.supermario.screens.MainMenu;
import it.unibo.oop.supermario.screens.SettingScreen;

public class MainMenuTest {
    private SuperMario game;
    private MainMenu menu;

    @Before
    public void setUp() throws Exception {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.disableAudio = true;
        LwjglApplication a = new LwjglApplication(new SuperMario(), config);
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                game = new SuperMario();
                menu = new MainMenu(game);
            }
        });
    }

    @Test
    public void testupdate() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                assertNotNull(menu.getCurrentLabel());
                menu.setCurrentLabel(5);
                assertEquals(5,menu.getCurrentLabel());
                menu.setCurrentLabel(10);
                assertEquals(10,menu.getCurrentLabel());
            }
        });
    }

    @Test
    public void testHandleInput() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                assertNotNull(menu.getCurrentLabel());
            }
        });
    }

    @Test
    public void testSelect() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                assertNotNull(menu.getCurrentLabel());
            }
        });
    }

}
