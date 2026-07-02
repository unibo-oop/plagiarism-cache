package speed;

import gamelogic.GameLogic;
import movements.Movements;

/**
 * This class implements the interface {@link Speed}.
 */
public final class SpeedImpl implements Speed {
    private final GameLogic game;
    private final Movements movements;
    private int currentPause;

    /**
     * Constructor of class SpeedImpl.
     * @param game is used to get the current level.
     * @param movements is used for instant positioning.
     */
    public SpeedImpl(final GameLogic game, final Movements movements) {
        this.movements = movements;
        this.game = game;
        // speed equals to the speed value associated to the current level
        this.currentPause = this.game.getScore().getLevel().getSpeed();
    }

    @Override
    public void setSpeedToCurrentLevel() {
        this.currentPause = this.game.getScore().getLevel().getSpeed();
    }

    @Override
    public void acceleratedSpeed() {
        this.currentPause = ACCELERATED_SPEED;
    }

    @Override
    public void istantPositioning() {
        while (true) {
            if (!this.movements.dropDown()) {
                break;
            }
        }
    }

    @Override
    public int getPause() {
        return this.currentPause;
    }
}
