package input;

import graphics.SpriteSheet;
import model.Player;
import physics.Direction;
import utilities.Position;

/**
 * Input component for the player.
 */
public class PlayerInputComponent extends AbstractInputComponent {

    private static final int PIXEL_PER_SECOND = 4 * SpriteSheet.SPRITE_SIZE_IN_GAME;

    /**
     * Constructor that takes player reference.
     * @param player reference
     */
    public PlayerInputComponent(final Player player) {
        super(player);
    }

    /**
     * Move methods that creates a command to add on queue.
     * @param way to take
     */
    public void move(final Direction way) {
        super.createCommand(way, PIXEL_PER_SECOND);
        super.getEntity().setDirection(way);
    }

    /**
     * It stops the player when no button is pressed.
     */
    public void stop() {
        super.createCommand(Direction.STOP, 0);
        super.getEntity().setDirection(Direction.STOP);
    }

    /**
     * Methods that drops the bomb in a specific position.
     * @param position where to drop the bomb
     */
    public void dropBomb(final Position position) {
        //TODO
    }
}
