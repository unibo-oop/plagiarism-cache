package it.unibo.oop.supermario.worldtest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.physics.box2d.Fixture;

import it.unibo.oop.supermario.game.SuperMario;
import it.unibo.oop.supermario.screens.PlayScreen;
import it.unibo.oop.supermario.world.FlagImpl;
import it.unibo.oop.supermario.world.TiledObject;

public class TiledObjectTest {
    private TiledObject tiled;
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
                obj= new MapObject();
                marioBros = new SuperMario();
                play= new PlayScreen(marioBros);
                tiled = new TiledObject(play, x, y, obj);
            }
        });
    }


    @Test
    public void testGetFixture() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                assertNotNull(tiled.getFixture());
            }
        });

    }

    @Test
    public void testGetTileSet() {
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                assertNotNull(tiled.getTileSet());
            }
        });

    }

}
