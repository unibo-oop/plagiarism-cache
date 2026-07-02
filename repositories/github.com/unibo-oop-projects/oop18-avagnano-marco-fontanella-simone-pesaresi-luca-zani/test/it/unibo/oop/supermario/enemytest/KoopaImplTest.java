package it.unibo.oop.supermario.enemytest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import it.unibo.oop.supermario.enemies.KoopaImpl;
import it.unibo.oop.supermario.enemies.KoopaImpl.State;
import it.unibo.oop.supermario.game.SuperMario;
import it.unibo.oop.supermario.screens.PlayScreen;


public class KoopaImplTest {

    private KoopaImpl koopa;
    /** -./
    private static final float XAXYS = 10;
    / -.*/
    private static final float YAXYS = 10;

    private PlayScreen playscreen;


    @Before
    public void goomba() {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.disableAudio = true;
        LwjglApplication a = new LwjglApplication(new SuperMario(), config);

        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                playscreen = new PlayScreen((SuperMario) a.getApplicationListener());
                koopa = new KoopaImpl(playscreen, 10, 10);
            }
        });

    }

    @Test 
    public void testHitOnHead() {
        assertNotNull(koopa.KILLED_SCORE); 
    }

    @Test
    public void testHitByFire() {
        assertNotNull(koopa.FIRED_SCORE); 
    }

    @Test
    public void testIsDestroyed() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
        assertNotNull(koopa.isDestroyed()); 
            }
        });
    }
    
    @Test
    public void testgetCurrentState() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
        assertNotNull(koopa.getCurrentState());
            }
        });
    }
    
    @Test
    public void testsetCurrentState() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                koopa.setCurrentState(State.DYING);
                assertEquals(State.DYING, koopa.getCurrentState());
            }
        });
    }
}