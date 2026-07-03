package model.test;

import static org.junit.Assert.assertEquals;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import controller.GameEngineImpl;
import model.BasicModel;
import model.entities.AbstractBarrel;
import model.entities.BarrelFactory;
import model.entities.BarrelFactoryImpl;
import model.entities.DonkeyKong;
import model.entities.Mario;
import model.entities.Movement;
import view.DrawableCanvasImpl;
import view.GameScreenImpl;
import view.InputHandler;
import view.Sprites;

public class GameLoopTest {

    @Test
    public void testLoop() throws InterruptedException {
        //final BasicModel model = new BasicModel();
        final BarrelFactory bf = new BarrelFactoryImpl();
        final List<AbstractBarrel> simpleBarrels = new ArrayList<>();
        simpleBarrels.add(bf.createStandardBarrel(10.0, 20.0, new Dimension(100,100)));
        final GameEngineImpl ge = new GameEngineImpl(new GameScreenImpl(new DrawableCanvasImpl(200, 200, "")));
        ge.startGame();
        final Mario mario = ge.getMario();
        mario.move(Optional.of(Movement.RIGHT));
        ge.setHandler(new InputHandler());
        Thread.sleep(200);
        assertEquals("Mario was supposed to face right", Sprites.MARIO_WALKING_RIGHT, ge.getMarioSpriteTest());
        
        
        /*
        mario.move(Optional.of(Movement.LEFT));
        Thread.sleep(500);
        assertEquals("Mario was supposed to walk left", Sprites.MARIO_FACING_LEFT, ge.getMarioSpriteTest());
        
        Thread.sleep(200);
        if(dk.isLaunchingBarrel()) {
            assertEquals("DonkeyKong was supposed to launch barrels", Sprites.GORILLA_FACING_RIGHT, ge.getDonkeySpriteTest());
        }else {
            assertEquals("DonkeyKong was not supposed to launch barrels", Sprites.GORILLA_IDLE, ge.getDonkeySpriteTest());
        }
        //Thread.sleep(200);
       */
    }
    
}
