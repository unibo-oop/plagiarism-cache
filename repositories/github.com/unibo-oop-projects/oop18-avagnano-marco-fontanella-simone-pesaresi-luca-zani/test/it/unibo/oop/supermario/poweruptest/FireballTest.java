package it.unibo.oop.supermario.poweruptest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.maps.MapObject;

import it.unibo.oop.supermario.game.SuperMario;
import it.unibo.oop.supermario.powerup.FireballModel;
import it.unibo.oop.supermario.screens.PlayScreen;
import it.unibo.oop.supermario.world.Brick;

public class FireballTest {

    private FireballModel fireball;
    final float x=10;
    final float y=10;
    boolean movingRight = true;
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
                fireball = new FireballModel(play, x, y,movingRight);
            }
        });
    }

    @Test
    public void testInstance() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                assertNotNull(fireball.getBody());
                assertNotNull(fireball.getPosition());
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
