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
import it.unibo.oop.supermario.world.FlagImpl;
import it.unibo.oop.supermario.world.SolidWorldCreator;
import it.unibo.oop.supermario.world.SolidWorldCreatorImpl;

public class SolidWorldCreatorImplTest {
    private SolidWorldCreatorImpl solid;
    private float x=10;
    private float y=10;
    private MapObject obj;
    private PlayScreen play;
    private SuperMario marioBros;


    @Before
    public void setUp() throws Exception {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.disableAudio = true;
        LwjglApplication a = new LwjglApplication(new SuperMario(), config);
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                marioBros= new SuperMario();
                play= new PlayScreen(marioBros);
                solid= new SolidWorldCreatorImpl(play);
            }
        });
    }
    
    @Test
    public void testInitialize() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
        assertNotNull(solid);
            }
        });
    }
}
