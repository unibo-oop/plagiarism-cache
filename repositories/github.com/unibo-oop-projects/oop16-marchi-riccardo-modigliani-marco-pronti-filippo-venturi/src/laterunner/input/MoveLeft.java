package laterunner.input;

import laterunner.model.user.User;
import laterunner.model.world.GameState;
import laterunner.physics.S2d;

/**
 * Move left command class.
 */
public class MoveLeft implements Command {

    private static final int LEFT_SPEED = -300;

    /**
     * Moves the car to the left.
     * 
     * @param gameState
     *          game coordinator
     */
    public void execute(final GameState gameState) {
        gameState.getWorld().getCar().setSpd(new S2d(LEFT_SPEED * User.getUser().getSpeedMul(), 0));
    }

}
