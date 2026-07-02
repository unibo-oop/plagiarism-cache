package input;

import model.GameState;

/**
 * 
 * Command for Player's jump.
 * Implements {@link Command} interface.
 *
 */
public class Space implements Command {

    private final GameState gameState;

    /**
     * Creates a new Space command.
     * @param gameState the {@link GameState} of the model.
     */
    public Space(final GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        this.gameState.getPlayer().jump();
    }

}
