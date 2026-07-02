package it.unibo.jtrs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.jtrs.model.api.Tetromino;
import it.unibo.jtrs.model.impl.TetrominoImpl;
import it.unibo.jtrs.utils.Pair;
import it.unibo.jtrs.utils.TetrominoData;

/**
 * Tetromino test class.
 */
class TetrominoTest {

    private Tetromino tTetromino;

    /**
     * Initialize necessary field for the tests.
     */
    @BeforeEach
    void init() {
        tTetromino = new TetrominoImpl(TetrominoData.T_COORD, 0, 0, TetrominoData.T_COLOR);
    }

    /**
     * Test Tetromino rotation.
     */
    @Test
    void testRotate() {

        tTetromino.rotate();
        assertEquals(Set.of(new Pair<>(1, 1), new Pair<>(0, 2), new Pair<>(1, 2), new Pair<>(2, 2)),
            tTetromino.getComponents());
        tTetromino.rotate();
        assertEquals(Set.of(new Pair<>(2, 0), new Pair<>(2, 1), new Pair<>(2, 2), new Pair<>(1, 1)),
            tTetromino.getComponents());
        tTetromino.rotate();
        assertEquals(Set.of(new Pair<>(0, 0), new Pair<>(1, 0), new Pair<>(2, 0), new Pair<>(1, 1)),
            tTetromino.getComponents());
        tTetromino.rotate(); // return to starting position after 4 rotation
        assertEquals(TetrominoData.T_COORD, tTetromino.getComponents());
    }

    /**
     * Test Tetromino component deletion.
     */
    @Test
    void testDelete() {

        final var t1 = new TetrominoImpl(Set.of(new Pair<>(0, 2)), 0, 0, tTetromino.getColor());
        final var t2 = new TetrominoImpl(Set.of(new Pair<>(2, 2)), 0, 0, tTetromino.getColor());

        tTetromino.rotate();
        // delete some components and get the remaining Tetrominoes' components
        final var res = tTetromino.delete(1).stream()
            .map(Tetromino::getComponents)
            .collect(Collectors.toSet());

        assertTrue(res.contains(t1.getComponents()) && res.contains(t2.getComponents()));

        // delete single components to test their total deletion
        assertTrue(t1.delete(0).isEmpty());
        assertTrue(t2.delete(2).isEmpty());
    }

}
