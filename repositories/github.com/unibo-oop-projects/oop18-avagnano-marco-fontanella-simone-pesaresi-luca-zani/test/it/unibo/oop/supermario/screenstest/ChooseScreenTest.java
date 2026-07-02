package it.unibo.oop.supermario.screenstest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import it.unibo.oop.supermario.enemies.KoopaImpl;
import it.unibo.oop.supermario.game.SuperMario;
import it.unibo.oop.supermario.gamemanager.GameManager;
import it.unibo.oop.supermario.screens.ChooseScreen;
import it.unibo.oop.supermario.screens.PlayScreen;

public class ChooseScreenTest {
    private ChooseScreen choose;
    private SuperMario game;
    private GameManager manager;

    @Before
    public void setUp() throws Exception {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.disableAudio = true;
        LwjglApplication a = new LwjglApplication(new SuperMario(), config);
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                manager = GameManager.instance;
                game = new SuperMario();
                choose= new ChooseScreen(game, manager);
                
            }
        });
    }

    @Test
    public void testupdate() {
       assertNotNull(choose.getCurrentItem());
    }

    @Test
    public void testHandleInput() {
        assertNotNull(choose.getCurrentItem());
    }

    @Test
    public void testSelect() {
        assertNotNull(choose.getCurrentItem());
    }
}
