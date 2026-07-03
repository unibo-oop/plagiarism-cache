package laterunner.input;

import laterunner.model.user.User;
import laterunner.model.world.GameState;
import laterunner.physics.S2d;

/**
 * Move right command class.
 */
public class MoveRight implements Command {

private static final int RIGHT_SPEED = +300;

    /**
     * Moves the car to the right.
     * 
     * @param gameState
     *          game coordinator
     */
    public void execute(final GameState gameState) {
        gameState.getWorld().getCar().setSpd(new S2d(RIGHT_SPEED * User.getUser().getSpeedMul(), 0));
    }
}
