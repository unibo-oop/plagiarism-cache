package it.unibo.oop.supermario.screenstest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import it.unibo.oop.supermario.game.SuperMario;
import it.unibo.oop.supermario.gamemanager.GameManager;
import it.unibo.oop.supermario.screens.ChooseScreen;
import it.unibo.oop.supermario.screens.GameConstant;

public class GameConstantTest {

    GameConstant constant;
    @Before
    public void setUp() throws Exception {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.disableAudio = true;
        LwjglApplication a = new LwjglApplication(new SuperMario(), config);
        Gdx.app.postRunnable(new Runnable() {
            public void run() {
                constant= new GameConstant();    
            }
        });
    }
        
    @Test 
    public void testCostants() {
        assertNotNull(constant.HEIGHT_RESOLUTION); 
        assertNotNull(constant.WIDTH_RESOLUTION); 
        assertNotNull(constant.CENTER_X); 
        assertNotNull(constant.CENTER_Y); 
        assertNotNull(constant.COL_WIDTH); 
        assertNotNull(constant.SCREEN_HEIGHT); 
        assertNotNull(constant.SCREEN_WIDTH);
        assertNotNull(constant.SKIN);
        assertNotNull(constant.SKIN_ATLAS);
    }
    
}
