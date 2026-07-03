package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import game.Bullet;
import game.Game;
import game.GameImpl;
import game.Player;
import view.ViewState;

/**
 * creating the class that manages the keyInput.
 *
 */
public class KeyInput extends KeyAdapter {
    private static final int NUMBER_OF_KEY_EACH_PLAYER_CAN_PRESS = 5;
    private final int velocity = GameImpl.GAMEAREA_WIDTH / 150;
    private boolean [] keyDownPlayer1 = new boolean[NUMBER_OF_KEY_EACH_PLAYER_CAN_PRESS];
    private boolean [] keyDownPlayer2 = new boolean[NUMBER_OF_KEY_EACH_PLAYER_CAN_PRESS];
    private boolean p1ShotReady = true;
    private boolean p2ShotReady = true;
    private final Game game;
    private final ViewObserver observer;

    /**
     * standard constructor for the KeyInput.
     * @param game {@link game.GameImpl}.
     * @param observer {@link ViewObserver}.
     */
    public KeyInput(final Game game, final ViewObserver observer) {
        super();
        this.game = game;
        this.observer = observer;
        for (int i = 0; i < 4; i++) {
            keyDownPlayer1[i] = false;
            keyDownPlayer2[i] = false;
        }
    }

    /**
     * checks if any player presses any key and saves it in the appropriate slot in the boolean array.
     * @param e event.
     */
    public void keyPressed(final KeyEvent e) {
        final int key = e.getKeyCode();
        switch (key) {
        case KeyEvent.VK_UP : keyDownPlayer1[0] = true;
        break;
        case KeyEvent.VK_DOWN : keyDownPlayer1[1] = true;
        break;
        case KeyEvent.VK_LEFT : keyDownPlayer1[2] = true;
        break;
        case KeyEvent.VK_RIGHT : keyDownPlayer1[3] = true;
        break;
        case KeyEvent.VK_L : keyDownPlayer1[4] = true;
        break;
        case KeyEvent.VK_W : keyDownPlayer2[0] = true;
        break;
        case KeyEvent.VK_S : keyDownPlayer2[1] = true;
        break;
        case KeyEvent.VK_A : keyDownPlayer2[2] = true;
        break;
        case KeyEvent.VK_D : keyDownPlayer2[3] = true;
        break;
        case KeyEvent.VK_G : keyDownPlayer2[4] = true;
        break;
        case KeyEvent.VK_P : 
            this.observer.update(null, ViewState.PAUSE);
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
        case KeyEvent.VK_UP : keyDownPlayer1[0] = false;
        break;
        case KeyEvent.VK_DOWN : keyDownPlayer1[1] = false;
        break;
        case KeyEvent.VK_LEFT : keyDownPlayer1[2] = false;
        break;
        case KeyEvent.VK_RIGHT : keyDownPlayer1[3] = false;
        break;
        case KeyEvent.VK_L : keyDownPlayer1[4] = false;
        break;
        case KeyEvent.VK_W : keyDownPlayer2[0] = false;
        break;
        case KeyEvent.VK_S : keyDownPlayer2[1] = false;
        break;
        case KeyEvent.VK_A : keyDownPlayer2[2] = false;
        break;
        case KeyEvent.VK_D : keyDownPlayer2[3] = false;
        break;
        case KeyEvent.VK_G : keyDownPlayer2[4] = false;
        break;
        default:
            break;
        }
    }
    /**
     * updates players' input based on what is pressed.
     */
    public void update() {
        final Player player1 = game.getPlayer().get(0);
        if ((this.keyDownPlayer1[0] && this.keyDownPlayer1[1]) || (!this.keyDownPlayer1[0] && !this.keyDownPlayer1[1])) {
            player1.setVelocity(0, 0);
        } else if (this.keyDownPlayer1[1]) {
            player1.setVelocity(0, velocity);
        } else {
            player1.setVelocity(0, -velocity);
        }
        if ((this.keyDownPlayer1[2] && this.keyDownPlayer1[3]) || (!this.keyDownPlayer1[2] && !this.keyDownPlayer1[3])) {
            player1.setVelocity(0, player1.getVelocity().getY());
        } else if (this.keyDownPlayer1[3]) {
            player1.setVelocity(velocity, player1.getVelocity().getY());
        } else {
            player1.setVelocity(-velocity, player1.getVelocity().getY());
        }
        if (this.keyDownPlayer1[4] && p1ShotReady) {
            game.getBullets().add(new Bullet(player1.getPosition().getX(), player1.getPosition().getY() - (player1.getHitbox().height / 2), player1.getID()));
            p1ShotReady = false;
            player1.setShotReadyIn(player1.getShotCD());
        }
        if (game.getPlayer().size() >= 2) {
            final Player player2 = game.getPlayer().get(1);
            if ((this.keyDownPlayer2[0] && this.keyDownPlayer2[1]) || (!this.keyDownPlayer2[0] && !this.keyDownPlayer2[1])) {
                player2.setVelocity(0, 0);
            } else if (this.keyDownPlayer2[1]) {
                player2.setVelocity(0, velocity);
            } else {
                player2.setVelocity(0, -velocity);
            }
            if ((this.keyDownPlayer2[2] && this.keyDownPlayer2[3]) || (!this.keyDownPlayer2[2] && !this.keyDownPlayer2[3])) {
                player2.setVelocity(0, player2.getVelocity().getY());
            } else if (this.keyDownPlayer2[3]) {
                player2.setVelocity(velocity, player2.getVelocity().getY());
            } else {
                player2.setVelocity(-velocity, player2.getVelocity().getY());
            }
            if (this.keyDownPlayer2[4] && p2ShotReady) {
                game.getBullets().add(new Bullet(player2.getPosition().getX(), player2.getPosition().getY() + (player2.getHitbox().height / 2), player2.getID()));
                p2ShotReady = false;
                player2.setShotReadyIn(player2.getShotCD());
            }
            if (!p2ShotReady) {
                player2.setShotReadyIn(player2.getShotReadyIn() - 1);
                if (player2.getShotReadyIn() <= 0) {
                    p2ShotReady = true;
                }
            }
        }
        if (!p1ShotReady) {
            player1.setShotReadyIn(player1.getShotReadyIn() - 1);
            if (player1.getShotReadyIn() <= 0) {
                p1ShotReady = true;
            }
        }
    }
}
