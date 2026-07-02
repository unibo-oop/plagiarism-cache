package it.unibo.michelito.model.powerup;

import it.unibo.michelito.controller.levelgenerator.LevelGenerator;
import it.unibo.michelito.model.bomb.api.BombType;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.maze.impl.MazeImpl;
import it.unibo.michelito.model.player.impl.PlayerImpl;
import it.unibo.michelito.model.powerups.api.PowerUp;
import it.unibo.michelito.model.powerups.api.PowerUpFactory;
import it.unibo.michelito.model.powerups.api.PowerUpType;
import it.unibo.michelito.model.powerups.impl.PowerUpFactoryImpl;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

final class TestPowerUp {
    public static final int STANDARD_COOLDOWN = 500;
    private Maze maze;
    private static final double X_SPAWN = 6;
    private static final double Y_SPAWN = 6;
    private final Position spawn = new Position(X_SPAWN, Y_SPAWN);
    private final PowerUpFactory factory = new PowerUpFactoryImpl();
    private static final long TICK = 1;

    @BeforeEach
    void setUp() {
        this.maze = new MazeImpl(LevelGenerator.testLevel(), new LevelGenerator(e -> { }));
    }

    @Test
    void testSpeedPowerUp() {
        final PowerUp powerUp = this.factory.createPowerUp(this.spawn, PowerUpType.SPEED_POWERUP);
        final PlayerImpl player = new PlayerImpl(this.maze.getPlayer().position()); /*(6, 6)*/
        final double expectedMovement = 0.02;
        powerUp.applyEffect(player);

        player.setDirection(Direction.RIGHT);
        player.update(TICK, this.maze);
        assertEquals(new Position(X_SPAWN + expectedMovement, Y_SPAWN), player.position());
    }

    @Test
    void testBombLimitPowerUp() {
        final int expectedBombs = 2;
        final PowerUp powerUp = this.factory.createPowerUp(this.spawn, PowerUpType.BOMB_LIMIT_POWERUP);
        final PlayerImpl player = new PlayerImpl(this.maze.getPlayer().position());

        assertEquals(1, player.getBombLimit());
        powerUp.applyEffect(player);
        assertEquals(2, player.getBombLimit());

        player.notifyToPlace();
        player.update(TICK, this.maze);
        player.notifyToPlace();
        player.setDirection(Direction.RIGHT);
        player.update(TICK + STANDARD_COOLDOWN, this.maze);
        assertEquals(expectedBombs, this.maze.getBombs().size());
    }

    @Test
    void testBombTypePowerUp() {
        final PlayerImpl player = new PlayerImpl(this.maze.getPlayer().position());
        final PowerUp bombTypePowerUp = this.factory.createPowerUp(this.spawn, PowerUpType.BOMB_TYPE_POWERUP);

        assertEquals(BombType.STANDARD, player.getBombType());
        bombTypePowerUp.applyEffect(player);
        assertNotEquals(BombType.STANDARD, player.getBombType());
    }
}
