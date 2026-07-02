package frogger;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Button;
import java.awt.event.KeyEvent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import frogger.common.Position;
import frogger.controller.GameControllerImpl;
import frogger.view.GameScene;

class InputTest {

    private static final int INITIAL_PLAYER_X = 0;
    private static final int INITIAL_PLAYER_Y = -6; 
    private static final int MODIFIED_X = -1;
    private static final int MODIFIED_Y = -5;
    private static final int MIN_PLAYER_X = -7;
    private GameControllerImpl controller;

    @BeforeEach
    void setUp() {
        this.controller = new GameControllerImpl();
        this.controller.init(new GameScene());
        this.controller.newGame();
    }

     @Test
     void testInput() {
        assertEquals(new Position(INITIAL_PLAYER_X, INITIAL_PLAYER_Y), this.controller.getGame().getPlayer().getPos());

        this.controller.getKeyListener().keyPressed(new KeyEvent(
            new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, 'S'));

        this.controller.getInputController().processInput(this.controller.getGame());
        assertEquals(new Position(INITIAL_PLAYER_X, MODIFIED_Y), this.controller.getGame().getPlayer().getPos());

        this.controller.getKeyListener().keyPressed(new KeyEvent(
            new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, 'D'));
        this.controller.getInputController().processInput(this.controller.getGame());

        assertEquals(new Position(INITIAL_PLAYER_X, INITIAL_PLAYER_Y), this.controller.getGame().getPlayer().getPos());

        this.controller.getKeyListener().keyPressed(new KeyEvent(
            new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'L'));
        this.controller.getInputController().processInput(this.controller.getGame());

        assertEquals(new Position(MODIFIED_X, INITIAL_PLAYER_Y), this.controller.getGame().getPlayer().getPos());

        this.controller.getKeyListener().keyPressed(new KeyEvent(

        new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, 'R'));
        this.controller.getInputController().processInput(this.controller.getGame());

        assertEquals(new Position(INITIAL_PLAYER_X, INITIAL_PLAYER_Y), this.controller.getGame().getPlayer().getPos());

        this.controller.getKeyListener().keyPressed(new KeyEvent(
            new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, 'D'));
        this.controller.getInputController().processInput(this.controller.getGame());

        assertEquals(new Position(INITIAL_PLAYER_X, INITIAL_PLAYER_Y), this.controller.getGame().getPlayer().getPos());

        this.controller.getKeyListener().keyPressed(new KeyEvent(
            new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'L'));
        this.controller.getInputController().processInput(this.controller.getGame());

        this.controller.getKeyListener().keyPressed(new KeyEvent(
            new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'L'));
        this.controller.getInputController().processInput(this.controller.getGame());

        this.controller.getKeyListener().keyPressed(new KeyEvent(
            new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'L'));
        this.controller.getInputController().processInput(this.controller.getGame());

        this.controller.getKeyListener().keyPressed(new KeyEvent(
            new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'L'));
        this.controller.getInputController().processInput(this.controller.getGame());

        this.controller.getKeyListener().keyPressed(new KeyEvent(
            new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'L'));
        this.controller.getInputController().processInput(this.controller.getGame());

        this.controller.getKeyListener().keyPressed(new KeyEvent(
            new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'L'));
        this.controller.getInputController().processInput(this.controller.getGame());

        this.controller.getKeyListener().keyPressed(new KeyEvent(
            new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'L'));
        this.controller.getInputController().processInput(this.controller.getGame());

        assertEquals(new Position(MIN_PLAYER_X, INITIAL_PLAYER_Y), this.controller.getGame().getPlayer().getPos());

        this.controller.getKeyListener().keyPressed(new KeyEvent(
            new Button(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'L'));
        this.controller.getInputController().processInput(this.controller.getGame());

        assertEquals(new Position(MIN_PLAYER_X, INITIAL_PLAYER_Y), this.controller.getGame().getPlayer().getPos());
     }
}
