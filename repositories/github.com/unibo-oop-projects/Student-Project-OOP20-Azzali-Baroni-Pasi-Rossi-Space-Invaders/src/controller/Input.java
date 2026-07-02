package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import controller.view.ViewObserver;
import model.BulletImpl;
import model.Game;
import model.GameImpl;
import model.PlayerImpl;
import view.StateV;

/**
 * The Class Input that manages the input .
 */
public class Input extends KeyAdapter{
	
    /** The Constant NUMBER_OF_KEY_EACH_PLAYER_CAN_PRESS. */
    private static final int NUMBER_OF_KEY_EACH_PLAYER_CAN_PRESS = 3;
    
    /** The speed. */
    private final int speed = GameImpl.ARENA_WIDTH / 150;
    
    /** The key down. */
    private boolean [] keyDown = new boolean[NUMBER_OF_KEY_EACH_PLAYER_CAN_PRESS];
    
    /** The shot ready. */
    private boolean shotReady = true;
    
    /** The game. */
    private final Game game;
    
    /** The observer. */
    private final ViewObserver observer;

    /**
     * standard constructor for the KeyInput.
     * @param game {@link game.GameImpl}.
     * @param observer {@link ViewObserver}.
     */
    public Input(final Game game, final ViewObserver observer) {
        super();
        this.game = game;
        this.observer = observer;
        for (int i = 0; i < 3; i++) {
            keyDown[i] = false;
        }
    }

    /**
     * checks if any player presses any key and saves it in the appropriate slot in the boolean array.
     * @param e event.
     */
    public void keyPressed(final KeyEvent e) {
        final int key = e.getKeyCode();
        switch (key) {
        case KeyEvent.VK_LEFT : keyDown[0] = true;
        break;
        case KeyEvent.VK_RIGHT : keyDown[1] = true;
        break;
        case KeyEvent.VK_SPACE : keyDown[2] = true;
        break;
        case KeyEvent.VK_P : 
            this.observer.update(null, StateV.PAUSE);
            break;
        case KeyEvent.VK_ESCAPE : Runtime.getRuntime().exit(0);
        default:
            break;
        }
    }

    /**
     * checks if any key is released.
     * @param e event.
     */
    public void keyReleased(final KeyEvent e) {
        final int key = e.getKeyCode();
        switch (key) {
        case KeyEvent.VK_LEFT : keyDown[0] = false;
        break;
        case KeyEvent.VK_RIGHT : keyDown[1] = false;
        break;
        case KeyEvent.VK_SPACE : keyDown[2] = false;
        break;
        default:
            break;
        }
    }
    /**
     * updates players' input based on what is pressed.
     */
    public void update() {
        final PlayerImpl player = game.getPlayer();
        if ((this.keyDown[0] && this.keyDown[1]) || (!this.keyDown[0] && !this.keyDown[1])) {
            player.setSpeed(0, player.getSpeed().getY());
        } else if (this.keyDown[1]) {
            player.setSpeed(speed, player.getSpeed().getY());
        } else {
            player.setSpeed(-speed, player.getSpeed().getY());
        }
        if (this.keyDown[2] && shotReady) {
            game.getBullets().add(new BulletImpl(player.getPosition().getX(), player.getPosition().getY() - (player.getHitbox().height / 2), player.getID()));
            shotReady = false;
            player.setShotReady(player.getCoolDown());
        }

        if (!shotReady) {
            player.setShotReady(player.getShotReady() - 1);
            if (player.getShotReady() <= 0) {
            	shotReady = true;
            }
        }
    }
}
