package it.unibo.michelito.model.player;

import it.unibo.michelito.controller.levelgenerator.LevelGenerator;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.maze.impl.MazeImpl;
import it.unibo.michelito.model.player.impl.PlayerImpl;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestPlayer {

    public static final int TIME_TO_REACH_LEFT_WALL = 200;
    public static final int TIME_TO_REACH_UP_WALL = 100;
    private PlayerImpl player;
    private final Maze maze = new MazeImpl(LevelGenerator.testLevel(), new LevelGenerator(e -> { }));
    private static final  double X_SPAWN = 6;
    private static final double Y_SPAWN = 6;
    private static final long TICK = 1;

    @BeforeEach
    void setUp() {
        this.player = new PlayerImpl(new Position(X_SPAWN, Y_SPAWN));
        /*spawns at (6, 6)*/
    }

    @Test
    void testMovement() {

        assertEquals(new Position(X_SPAWN, Y_SPAWN), player.position());
        player.update(TICK, this.maze);
        assertEquals(new Position(X_SPAWN, Y_SPAWN), player.position());

        player.setDirection(Direction.RIGHT);
        player.update(TICK, this.maze);
        assertEquals(new Position(X_SPAWN + player.getSpeed(), Y_SPAWN), player.position());

        player.update(TICK, this.maze);
        assertEquals(new Position(X_SPAWN + player.getSpeed(), Y_SPAWN), player.position());

        player.setDirection(Direction.LEFT);
        player.update(TICK, this.maze);
        assertEquals(new Position(X_SPAWN, Y_SPAWN), player.position());

        player.setDirection(Direction.DOWN);
        player.update(TICK, this.maze);
        assertEquals(new Position(X_SPAWN, Y_SPAWN + player.getSpeed()), player.position());

        player.setDirection(Direction.UP);
        player.update(TICK, this.maze);
        assertEquals(new Position(X_SPAWN, Y_SPAWN), player.position());
    }

    @Test
    void testBumpInToWalls() {


        player.setDirection(Direction.LEFT);
        player.update(TICK * TIME_TO_REACH_LEFT_WALL, maze);
        assertEquals(new Position(X_SPAWN, Y_SPAWN), player.position());

        player.setDirection(Direction.UP);
        player.update(TICK * TIME_TO_REACH_UP_WALL, maze);
        assertEquals(new Position(X_SPAWN, Y_SPAWN), player.position());

        player.setDirection(Direction.LEFT);
        player.update(TICK, maze);
        assertEquals(new Position(X_SPAWN - player.getSpeed(), Y_SPAWN), player.position());

        player.setDirection(Direction.DOWN);
        player.update(TICK, maze);
        assertEquals(new Position(X_SPAWN - player.getSpeed(), Y_SPAWN + player.getSpeed()), player.position());
    }
}
