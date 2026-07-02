package speed.test;

import static org.junit.Assert.assertEquals;

import java.util.Optional;
import gamelogic.GameLogic;
import gamelogic.GameLogicImpl;
import level.Levels;
import movements.Movements;
import movements.MovementsImpl;
import piece.PieceImpl;
import piece.Type;
import speed.Speed;
import speed.SpeedImpl;

/**
 * This class tests methods of class Speed.
 */
public class TestSpeed {
    private static final int TOP = 18;
    private final GameLogic game = new GameLogicImpl(Levels.LEVEL_1, Optional.empty());
    private final Movements mov = new MovementsImpl(this.game);
    private final Speed speed = new SpeedImpl(this.game, this.mov);

    /**
     * Tests the method for instant positioning. So the piece must have a top equals
     * to 18 (I know it because I choose the piece J to place).
     */
    @org.junit.Test
    public void testInstantPositioning() {
        // I set the coordinates of a proper piece to be sure of the top value
        this.game.setCurrent(new PieceImpl(Type.L, Optional.empty(), Optional.empty(), Optional.empty()));
        this.speed.istantPositioning();
        assertEquals(TOP, this.game.getCurrent().getTop());
    }

    /**
     * Tests the method to initialize the quantity of milliseconds (pause).
     * I can test just the default start level.
     */
    @org.junit.Test
    public void testInitPause() {
        this.speed.setSpeedToCurrentLevel();
        assertEquals(Levels.LEVEL_1.getSpeed(), this.speed.getPause());
    }

    /**
     * Tests the method for the accelerated speed.
     */
    @org.junit.Test
    public void testAcceleratedSpeed() {
        this.speed.acceleratedSpeed();
        assertEquals(Speed.ACCELERATED_SPEED, this.speed.getPause());
        this.speed.setSpeedToCurrentLevel();
    }
}
