package model;

import java.awt.Graphics2D;

import input.PlayerInputComponent;
import utilities.Position;

/**
 * This class represents the player, with its components.
 */
public class Player extends AbstractEntity {

    private final PlayerInputComponent input;
    //private final PlayerGraphicsComponent gfx;
    //private final PlayerPhysicsComponent physics;

    /**
     * Constructor.
     * @param position : set player position
     * @param solid : set if it's solid or not
     * @param breakable : set if it's breakable or not
     */
    public Player(final Position position, final boolean solid, final boolean breakable) {
        super(position, solid, breakable);
        this.input = new PlayerInputComponent(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processInput() {
        if (!super.getCommandQueue().isEmpty()) {
            super.getCommandQueue().stream().findFirst().get().execute();
        }
    }

    @Override
    public void update(final long elapsedTime) {
        // TODO
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics2D g) {
        //TODO
    }

    /**
     * @return player input component
     */
    public PlayerInputComponent getInput() {
        return this.input;
    }
}
