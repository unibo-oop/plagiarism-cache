package controllers.movement.keyInput;

import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

import application.StealthNinja;
import model.player.Player;
import view.WindowPause;

public class KeyInput extends KeyAdapter implements KeyInputInterface {

    private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    private final Player player;
    private WindowPause windowPause;

    /**
     * Contructor for KeyInput.
     * 
     * @param player
     */
    public KeyInput(final Player player) {
        this.player = Objects.requireNonNull(player);
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        final int key = e.getKeyCode();
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            player.getMovement().setUp(true);
        }
        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            player.getMovement().setDown(true);
        }
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            player.getMovement().setLeft(true);
        }
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            player.getMovement().setRight(true);
        }
        if (key == KeyEvent.VK_ESCAPE && !this.player.isGameOver()) {
            windowPause = new WindowPause();
            windowPause.setDimensions(SCREEN_WIDTH, SCREEN_HEIGHT);
            windowPause.show();
            StealthNinja.MODEL_ACTION.interruptLevel();
        }
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        final int key = e.getKeyCode();
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            player.getMovement().setUp(false);
        }
        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            player.getMovement().setDown(false);
        }
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            player.getMovement().setLeft(false);
        }
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            player.getMovement().setRight(false);
        }

    }
}
