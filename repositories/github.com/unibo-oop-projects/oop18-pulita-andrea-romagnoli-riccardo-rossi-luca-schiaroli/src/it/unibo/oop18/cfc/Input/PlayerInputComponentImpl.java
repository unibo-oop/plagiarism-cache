package it.unibo.oop18.cfc.Input;


import it.unibo.oop18.cfc.Objects.Entity.PlayerImpl;
import it.unibo.oop18.cfc.Physics.Direction;
import it.unibo.oop18.cfc.TileMap.Tile;
import it.unibo.oop18.cfc.Util.Position;

/**
 * Input component for the player. It implements {@link PlayerInputComponent}.
 */
public class PlayerInputComponentImpl extends AbstractInputComponent implements PlayerInputComponent {

    private static final int PIXEL_PER_SECOND = 4;

    private final PlayerImpl player;

    /**
     * Creates {@code PlayerInputComponentImpl}.
     *
     * @param player reference
     */
    public PlayerInputComponentImpl(final PlayerImpl player) {
        super(player);
        this.player = player;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(final Direction way) {
        super.createDirectionCommand(way, PIXEL_PER_SECOND);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        super.createDirectionCommand(Direction.STOP, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doAction(final Position position) {
        
    }
}
