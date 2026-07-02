package it.unibo.jtrs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import it.unibo.jtrs.model.api.Tetromino;
import it.unibo.jtrs.model.api.TetrominoFactory;
import it.unibo.jtrs.model.impl.TetrominoFactoryImpl;

class TetrominoFactoryTest {

    private static final int DIFFERENT_TETROMINOES = 7;
    private static final int NUMBER_OF_TESTS = 5;

    private final TetrominoFactory factory = new TetrominoFactoryImpl();

    @Test
    @RepeatedTest(NUMBER_OF_TESTS)
    void testFactoryGeneration() {

        final List<Tetromino> list = new ArrayList<>();

        for (int i = 0; i < DIFFERENT_TETROMINOES; i++) {
            list.add(factory.getRandomTetromino());
        }

        final var components = list.stream().map(Tetromino::getComponents).collect(Collectors.toList());
        final var differents = (int) components.stream().distinct().count();
        assertEquals(DIFFERENT_TETROMINOES, differents);
    }
}
