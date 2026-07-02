package it.unibo.javacrush.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.common.PowerUpNumber;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.Match;
import it.unibo.javacrush.model.impl.BoardImpl;
import it.unibo.javacrush.model.impl.CellImpl;
import it.unibo.javacrush.model.impl.MatchImpl;
import it.unibo.javacrush.powerup.api.PowerUpManager;
import it.unibo.javacrush.powerup.impl.PowerUpManagerImpl;

/**
 * Test for {@link it.unibo.javacrush.powerup.api.PowerUpManager}.
 */
class PowerUpManagerTest {

    private static final int DIM = 3;
    private PowerUpManager manager;
    private Board board;
    private Position pos;
    private Set<Match> matchSet;

    /**
     * Initialises the two Boards filling all their cells with MOKA and the PowerUpManager.
     */
    @BeforeEach
    void init() {
        this.board = new BoardImpl(DIM, DIM);
        this.manager = new PowerUpManagerImpl();
        this.pos = new Position(0, 0);
        this.matchSet = new HashSet<>();

        for (int y = 0; y < board.getRows(); y++) {
            for (int x = 0; x < board.getCols(); x++) {
                board.setCell(new Position(x, y), Optional.of(new CellImpl(CellType.MOKA)));
            }
        }

    }

    @Test
    void testSelectPowerUp() {
        assertFalse(manager.isPowerUpSelected());
        assertFalse(manager.selectPowerUp(-1));
        assertFalse(manager.selectPowerUp(3));
        assertTrue(manager.selectPowerUp(0));
        assertTrue(manager.isPowerUpSelected());
        assertTrue(manager.resetPowerUpSelection());
        assertFalse(manager.isPowerUpSelected());
    }

    @Test
    void testApplyRemoveCellPowerUp() {

        assertFalse(manager.isPowerUpSelected());
        assertFalse(manager.applyPowerUp(board, pos));

        assertTrue(manager.selectPowerUp(PowerUpNumber.SINGLECELL.ordinal()));
        assertTrue(manager.isPowerUpSelected());
        assertTrue(manager.getMatches().isEmpty());

        assertTrue(manager.applyPowerUp(board, pos));

        assertFalse(manager.getMatches().isEmpty());
        // It's possible to reselect the same PowerUp, but is not possible to use it again
        assertTrue(manager.selectPowerUp(PowerUpNumber.SINGLECELL.ordinal()));
        assertFalse(manager.applyPowerUp(board, pos));

        this.matchSet.add(new MatchImpl(Set.of(pos), this.board.getCellAt(pos).get().getType()));
        assertEquals(this.matchSet, this.manager.getMatches());

        assertTrue(manager.resetPowerUpSelection());
        assertFalse(manager.isPowerUpSelected());
        assertFalse(manager.applyPowerUp(board, pos));

    }

    @Test
    void testApplyRemoveRowPowerUp() {
        final Set<Position> tmp = new HashSet<>();

        assertFalse(manager.isPowerUpSelected());
        assertFalse(manager.applyPowerUp(board, pos));

        assertTrue(manager.selectPowerUp(PowerUpNumber.ROW.ordinal()));
        assertTrue(manager.isPowerUpSelected());
        assertTrue(manager.getMatches().isEmpty());

        assertTrue(manager.applyPowerUp(board, pos));

        assertFalse(manager.getMatches().isEmpty());
        // It's possible to reselect the same PowerUp, but is not possible to use it again
        assertTrue(manager.selectPowerUp(PowerUpNumber.ROW.ordinal()));
        assertFalse(manager.applyPowerUp(board, pos));

        for (int x = 0; x < board.getRows(); x++) {
            tmp.add(new Position(x, pos.y()));
        }

        for (final var tp : CellType.values()) {
            final var s = tmp.stream()
                                .filter(p -> board.getCellAt(p).get().getType() == tp)
                                .toList();

            if (!s.isEmpty()) {
                this.matchSet.add(new MatchImpl(Set.copyOf(s), tp));
            }
        }

        assertEquals(this.matchSet, this.manager.getMatches());

        assertTrue(manager.resetPowerUpSelection());
        assertFalse(manager.isPowerUpSelected());
        assertFalse(manager.applyPowerUp(board, pos));

    }

    @Test
    void testApplyRemoveTypePowerUp() {

        Position current;
        final Set<Position> tmp = new HashSet<>();
        this.board.setCell(new Position(DIM - 1, DIM - 1), Optional.of(new CellImpl(CellType.CUP)));

        assertFalse(manager.isPowerUpSelected());
        assertFalse(manager.applyPowerUp(board, pos));

        assertTrue(manager.selectPowerUp(PowerUpNumber.TYPE.ordinal()));
        assertTrue(manager.isPowerUpSelected());
        assertTrue(manager.getMatches().isEmpty());

        assertTrue(manager.applyPowerUp(board, pos));

        assertFalse(manager.getMatches().isEmpty());
        // It's possible to reselect the same PowerUp, but is not possible to use it again
        assertTrue(manager.selectPowerUp(PowerUpNumber.TYPE.ordinal()));
        assertFalse(manager.applyPowerUp(board, pos));

        for (int y = 0; y < board.getRows(); y++) {
            for (int x = 0; x < board.getCols(); x++) {

                current = new Position(x, y);
                if (board.getCellAt(pos).get().getType() == board.getCellAt(current).get().getType()) {
                    tmp.add(current);
                }
            }
        }

        this.matchSet.add(new MatchImpl(tmp, board.getCellAt(pos).get().getType()));
        assertEquals(this.matchSet, this.manager.getMatches());

        assertTrue(manager.resetPowerUpSelection());
        assertFalse(manager.isPowerUpSelected());
        assertFalse(manager.applyPowerUp(board, pos));

    }

}
