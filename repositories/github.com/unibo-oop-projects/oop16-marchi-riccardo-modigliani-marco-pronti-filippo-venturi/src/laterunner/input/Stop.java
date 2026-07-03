package laterunner.input;

import laterunner.model.world.GameState;
import laterunner.physics.S2d;

/**
 * Stop command class.
 */
public class Stop implements Command {

    /**
     * Stops car movement.
     * @param gameState
     *          game coordinator
     */
    public void execute(final GameState gameState) {
        gameState.getWorld().getCar().setSpd(new S2d(0, 0));
    }
}
