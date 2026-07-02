package it.unibo.oop.supermario.charactertest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import it.unibo.oop.supermario.character.MarioImpl;
import it.unibo.oop.supermario.game.SuperMario;
import it.unibo.oop.supermario.powerup.Mushroom;
import it.unibo.oop.supermario.screens.PlayScreen;

public class MarioImplTest {


    private MarioImpl mario;
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
                mario = new MarioImpl(play, x, y);
            }
        });
    }

    @Test
    public void testGetter() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                assertNotNull(mario);
                assertNotNull(mario.getBody());
                assertNotNull(mario.getPosition());
                assertNotNull(mario.getState());
            }
        });
    }

    @Test
    public void test() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                mario.setDestroyed();
                assertEquals(true, mario.isToDestroy());

            }
        });
    }

}
