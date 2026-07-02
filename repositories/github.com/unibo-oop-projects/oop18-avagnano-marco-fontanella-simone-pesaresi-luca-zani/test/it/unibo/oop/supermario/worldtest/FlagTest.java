package it.unibo.oop.supermario.worldtest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.maps.MapObject;

import it.unibo.oop.supermario.game.SuperMario;
import it.unibo.oop.supermario.screens.PlayScreen;
import it.unibo.oop.supermario.world.Brick;
import it.unibo.oop.supermario.world.FlagImpl;

public class FlagTest {
    private FlagImpl flag;
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
                flag = new FlagImpl(play, x, y);
            }
        });
    }

    @Test
    public void testFalling() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
            assertEquals(false, flag.isFlagFalling());
            flag.setFlagFalling(true);
            assertEquals(true, flag.isFlagFalling());
            }
        });
    }

    @Test
    public void testGetBody() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
        assertNotNull(flag.getBody());
            }
        });
    }
}
