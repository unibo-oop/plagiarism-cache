package it.unibo.oop.supermario.poweruptest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import it.unibo.oop.supermario.game.SuperMario;
import it.unibo.oop.supermario.powerup.Flower;
import it.unibo.oop.supermario.powerup.Mushroom;
import it.unibo.oop.supermario.screens.PlayScreen;
import it.unibo.oop.supermario.world.FlagImpl;

public class MushroomTest {
    private Mushroom mushroom;
    private float x=10;
    private float y=10;
    private PlayScreen play;
    private SuperMario marioBros;


    @Before
    public void setUp() throws Exception {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.disableAudio = true;
        LwjglApplication a = new LwjglApplication(new SuperMario(), config);
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                marioBros = new SuperMario();
                play= new PlayScreen(marioBros);
                mushroom = new Mushroom(play, x, y);
            }
        });
    }
    @Test
    public void testInstance() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                assertNotNull(mushroom.getBody());
                assertNotNull(mushroom.getPosition());
            }
        });
    }

    @Test
    public void testHud() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                assertNotNull(play);
                assertNotNull(play.getHud());
            }
        });
    }
}
