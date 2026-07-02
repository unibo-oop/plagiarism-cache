package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Optional;

import model.Player;
import physics.Direction;

/**
 * KeyInput to move the player.
 */
public class KeyInput implements KeyListener {

    private final Player player;

    /**
     * Constructor takes player reference.
     * @param player to move
     */
    public KeyInput(final Player player) {
        this.player = player;
    }

    /**
     * It generates a direction when specific key is pressed and
     * call specific method on player to move. If space is pressed
     * player drops bomb.
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        final Optional<Direction> way;
        switch (e.getKeyCode()) {
        case KeyEvent.VK_A:
            way = Optional.ofNullable(Direction.LEFT);
            break;
        case KeyEvent.VK_S:
            way = Optional.ofNullable(Direction.DOWN);
            break;
        case KeyEvent.VK_D:
            way = Optional.ofNullable(Direction.RIGHT);
            break;
        case KeyEvent.VK_W:
            way = Optional.ofNullable(Direction.UP);
            break;
        case KeyEvent.VK_SPACE:
            this.player.getInput().dropBomb(this.player.getPosition());
            way = Optional.empty();
            break;
        default:
            way = Optional.ofNullable(Direction.STOP);
            break;
        }
        if (way.isPresent()) {
            this.player.getInput().move(way.get());
        }
    }

    /**
     * It stops the player when a key button is released.
     */
    @Override
    public void keyReleased(final KeyEvent e) {
        if (e.getKeyCode() != KeyEvent.VK_SPACE) {
            this.player.getInput().stop();
        }
    }

    @Override
    public void keyTyped(final KeyEvent e) {
    }
}
