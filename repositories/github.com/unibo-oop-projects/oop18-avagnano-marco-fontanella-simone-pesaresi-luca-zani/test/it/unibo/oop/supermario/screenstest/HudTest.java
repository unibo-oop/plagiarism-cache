package it.unibo.oop.supermario.screenstest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import it.unibo.oop.supermario.game.SuperMario;
import it.unibo.oop.supermario.screens.Hud;

public class HudTest {
    private Hud hud;
    SuperMario game;
    

    @Before
    public void setUp() throws Exception {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.disableAudio = true;
        LwjglApplication a = new LwjglApplication(new SuperMario(), config);
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                game= new SuperMario();
                hud= new Hud(game.batch);
            }
        });
    }

    @Test
    public void testTimeCount() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
        assertNotNull(hud.getTime());
            }
        });
    }
    
    @Test
    public void testAddScore() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
        assertNotNull(hud.getScore());
            }
        });
    }
    
    
    @Test
    public void testCoinCount() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
        assertNotNull(hud.getCoinCount());
        }
    });
}
}
