package it.unibo.javacrush.model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.Direction;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.GravityEngine;
import it.unibo.javacrush.model.impl.BoardImpl;
import it.unibo.javacrush.model.impl.CellImpl;
import it.unibo.javacrush.model.impl.gravity.CrazyGravity;
import it.unibo.javacrush.model.impl.gravity.DownwardGravity;
import it.unibo.javacrush.model.impl.gravity.UpwardGravity;

class CrazyGravityTest {

    private static final int MAX_ATTEMPTS = 20;

    @Test
    void testEmpryListThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new CrazyGravity(List.of()),
            "CrazyGravity should throw an exception if initialized with an empty strategy list");
    }

    @Test
    void testGravityChangesWhenStable() {
        final List<GravityEngine> strategies = List.of(new DownwardGravity(), new UpwardGravity());
        boolean changed = false;

        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            final CrazyGravity crazyGravity = new CrazyGravity(strategies);
            final Board board = new BoardImpl(8, 8);
            final Direction firstDir = crazyGravity.getDirection();

            board.setCell(new Position(4, 4), Optional.of(new CellImpl(CellType.getRandomType())));
            crazyGravity.applyGravity(board);

            for (int r = 0; r < board.getRows(); r++) {
                for (int c = 0; c < board.getCols(); c++) {
                    if (board.getCellAt(new Position(c, r)).isEmpty()) {
                        board.setCell(new Position(c, r), Optional.of(new CellImpl(CellType.getRandomType())));
                    }
                }
            }

            crazyGravity.applyGravity(board);

            if (crazyGravity.getDirection() != firstDir) {
                changed = true;
                break;
            }
        }

        assertTrue(changed, "CrazyGravity should eventually change direction when board is stable and full");
    }

    @Test
    void testGravityDoesNotChangeWhenMoving() {
        final List<GravityEngine> strategies = List.of(new DownwardGravity(), new UpwardGravity());
        final CrazyGravity crazyGravity = new CrazyGravity(strategies);
        final Board board = new BoardImpl(8, 8);

        final Direction initialDir = crazyGravity.getDirection();

        final int startY = (initialDir == Direction.DOWN) ? 0 : 7;
        board.setCell(new Position(0, startY), Optional.of(new CellImpl(CellType.MOKA)));

        final boolean moved = crazyGravity.applyGravity(board);

        assertTrue(moved, "Il pezzo avrebbe dovuto muoversi");
        assertEquals(initialDir, crazyGravity.getDirection(), 
            "La gravità NON deve cambiare se c'è stato movimento (moved = true)");
    }
}
