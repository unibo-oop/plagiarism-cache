package it.unibo.superpeach.keyboard;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Optional;

import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.gameentities.player.PlayerHandler;

/**
 * This class allows to interact with the user via keyboard.
 * 
 * @author Patrick Sbrighi
 */
public final class Keyboard extends KeyAdapter {
    private final Optional<PlayerHandler> playHand;
    private static final long MIN_MILLS = 150;
    private long current;
    private final Optional<Game> game;

    /**
     * Class constructor.
     * 
     * @param handler Player handler
     * @param game    Game
     */
    public Keyboard(final PlayerHandler handler, final Game game) {
        this.playHand = Optional.of(handler);
        this.current = System.currentTimeMillis();
        this.game = Optional.of(game);
    }

    /**
     * This method recognizes if a key is clicked on the keyboard.
     * 
     * @param e key pressed
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        final int pressed = e.getKeyCode();

        if (pressed == KeyEvent.VK_SPACE && !playHand.get().getPlayer().hasJumped()
                && (System.currentTimeMillis() - current) >= MIN_MILLS) {
            current = System.currentTimeMillis();
            playHand.get().getPlayer().jump();
        }

        if (pressed == KeyEvent.VK_A) {
            playHand.get().getPlayer().moveLeft();
        }

        if (pressed == KeyEvent.VK_D) {
            playHand.get().getPlayer().moveRight();
        }

        if (pressed == KeyEvent.VK_ESCAPE) {
            game.get().restart();
        }
    }

    /**
     * This method recognizes if a keyboard key is released.
     * 
     * @param e key relased
     */
    @Override
    public void keyReleased(final KeyEvent e) {
        final int pressed = e.getKeyCode();

        if (pressed == KeyEvent.VK_SPACE) {
            playHand.get().getPlayer().setHasJumped(false);
        }

        if (pressed == KeyEvent.VK_A || pressed == KeyEvent.VK_D) {
            playHand.get().getPlayer().setMoveX(0);
        }
    }
}
