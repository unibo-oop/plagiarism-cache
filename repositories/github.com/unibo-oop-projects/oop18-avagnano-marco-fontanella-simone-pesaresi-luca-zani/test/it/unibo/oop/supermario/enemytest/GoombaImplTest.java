package it.unibo.oop.supermario.enemytest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import it.unibo.oop.supermario.enemies.GoombaImpl;
import it.unibo.oop.supermario.game.SuperMario;

import it.unibo.oop.supermario.screens.PlayScreen;

/**
 * 
 *
 */

public class GoombaImplTest {
    /** -.*/
    private GoombaImpl goomba;
    /** -.*/
    private static final float XAXYS = 10;
    /** -.*/
    private static final float YAXYS = 10;

    private PlayScreen playscreen;

    /**
     * 
     * @throws Exception -.
     */
    @Before
    public void goomba() {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.disableAudio = true;
        LwjglApplication a = new LwjglApplication(new SuperMario(), config);
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                playscreen = new PlayScreen((SuperMario) a.getApplicationListener());
                goomba = new GoombaImpl(playscreen, 10, 10);    
            }
        });
    }

    @Test 
    public void testHitOnHead() {
        assertNotNull(goomba.KILLED_SCORE); 
    }

    @Test
    public void testHitByFire() {
        assertNotNull(goomba.FIRED_SCORE); 
    }

    @Test
    public void testIsSmashed() {
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                assertNotNull(goomba.isSmashed()); 
            }
        });
    }
}
