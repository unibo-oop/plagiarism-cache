package it.unibo.michelito.controller.levelgenerator;

import it.unibo.michelito.util.GameObject;
import it.unibo.michelito.util.ObjectType;
import it.unibo.michelito.util.Position;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * test for {@link LevelGenerator}.
 */
class TestLevelGenerator {
    private static final  int BLOCK_SIZE = 6;
    private static final int MAZE_WIDE = 19 * BLOCK_SIZE;
    private static final int MAZE_HEIGHT = 14 * BLOCK_SIZE;
    private static final Position TEST_BLOCK_POSITION = new Position(4 * BLOCK_SIZE, 6 * BLOCK_SIZE);

    @Test
    void testLevelGeneratorTestLevel() {
        testLevelGeneratorBaseLevel(LevelGenerator.testLevel());
    }

    @Test
    void testLevelGeneratorLevel1() {
        final Set<GameObject> baseMaze = new HashSet<>(testLevelGeneratorBaseLevel(LevelGenerator.testLevel()));
        final Set<GameObject> maze1 = new HashSet<>(testLevelGeneratorBaseLevel(1));

        assertNotEquals(baseMaze, maze1);
        assertTrue(maze1.stream().map(GameObject::objectType).anyMatch(x -> x.equals(ObjectType.DOOR)));
    }

    Set<GameObject> testLevelGeneratorBaseLevel(final int level) {
        final LevelGenerator levelGenerator = new LevelGenerator(e -> { });
        final Set<GameObject> maze = levelGenerator.apply(level);

        assertFalse(maze.isEmpty());
        assertEquals(1, maze.stream().map(GameObject::objectType).filter(x -> x.equals(ObjectType.PLAYER)).count());
        assertTrue(maze.stream().map(GameObject::objectType).anyMatch(x -> x.equals(ObjectType.WALL)));
        assertTrue(maze.stream().map(GameObject::objectType).anyMatch(x -> x.equals(ObjectType.BLANK_SPACE)));

        assertTrue(maze.contains(new GameObject(ObjectType.WALL, new Position(0, 0))));
        assertTrue(maze.contains(new GameObject(ObjectType.WALL, new Position(MAZE_WIDE, 0))));
        assertTrue(maze.contains(new GameObject(ObjectType.WALL, new Position(0, MAZE_HEIGHT))));
        assertTrue(maze.contains(new GameObject(ObjectType.WALL, new Position(MAZE_WIDE, MAZE_HEIGHT))));

        assertTrue(maze.contains(new GameObject(ObjectType.BLANK_SPACE, new Position(0, 0))));
        assertTrue(maze.contains(new GameObject(ObjectType.BLANK_SPACE, new Position(MAZE_WIDE, 0))));
        assertTrue(maze.contains(new GameObject(ObjectType.BLANK_SPACE, new Position(0, MAZE_HEIGHT))));
        assertTrue(maze.contains(new GameObject(ObjectType.BLANK_SPACE, new Position(MAZE_WIDE, MAZE_HEIGHT))));

        assertTrue(maze.contains(new GameObject(ObjectType.WALL, new Position(2 * BLOCK_SIZE, 2 * BLOCK_SIZE))));
        assertTrue(maze.contains(new GameObject(ObjectType.WALL, TEST_BLOCK_POSITION)));

        return maze;
    }

}
