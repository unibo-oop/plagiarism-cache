package it.unibo.michelito.controller.gamecontroller.movecommandbuilder;

import it.unibo.michelito.controller.gamecontroller.movecommandbuilder.api.MoveCommandBuilder;
import it.unibo.michelito.controller.gamecontroller.movecommandbuilder.impl.MoveCommandBuilderImpl;
import it.unibo.michelito.controller.levelgenerator.LevelGenerator;
import it.unibo.michelito.controller.playercommand.api.PlayerCommand;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.maze.impl.MazeImpl;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.model.player.impl.PlayerImpl;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class TestMoveCommandBuilder {
    public static final double PLAYER_SPEED = 0.01;
    private MoveCommandBuilder moveCommandBuilder;
    private static final  double X_SPAWN = 6;
    private static final double Y_SPAWN = 6;
    private static final long TICK = 1;
    private final Player player = new PlayerImpl(new Position(X_SPAWN, Y_SPAWN));
    private final Maze maze = new MazeImpl(LevelGenerator.testLevel(), new LevelGenerator(e -> { }));

    @BeforeEach
    void setUp() {
        this.moveCommandBuilder = new MoveCommandBuilderImpl();
    }

    @Test
    void testBuild() {
        assertInstanceOf(PlayerCommand.class, moveCommandBuilder.build());
        moveCommandBuilder.build().execute(player);
        player.update(TICK, maze);
        assertEquals(new Position(X_SPAWN, Y_SPAWN), player.position());

        this.moveCommandBuilder.addDirection(Direction.UP).addDirection(Direction.RIGHT);
        moveCommandBuilder.build().execute(player);
        player.update(TICK, maze);
        final BigDecimal deltaMovement = BigDecimal.valueOf(Math.sqrt(0.5)).multiply(BigDecimal
                .valueOf(PLAYER_SPEED));
        assertEquals(new Position(BigDecimal.valueOf(X_SPAWN)
                .add(deltaMovement)
                .doubleValue(),
                BigDecimal.valueOf(Y_SPAWN).
                        subtract(deltaMovement).doubleValue()), player.position());

        this.moveCommandBuilder = new MoveCommandBuilderImpl();
        assertThrows(IllegalArgumentException.class, () -> this.moveCommandBuilder
                .addDirection(Direction.RIGHT)
                .addDirection(Direction.RIGHT)
        );

        this.moveCommandBuilder = new MoveCommandBuilderImpl();
        assertThrows(IllegalArgumentException.class, () -> this.moveCommandBuilder
                .addDirection(Direction.LEFT)
                .addDirection(Direction.LEFT)
        );
    }
}
