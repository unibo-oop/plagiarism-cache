package it.unibo.javacrush.model;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Match;
import it.unibo.javacrush.model.impl.MatchImpl;

class MatchTest {

    @Test
    void testMatchCreation() {
        final Set<Position> positions = Set.of(new Position(0, 0), new Position(1, 0), new Position(2, 0));
        final CellType expectedtype = CellType.COFFEE_BEAN;
        final Match match = new MatchImpl(positions, expectedtype);

        assertEquals(3, match.getSize());
        assertEquals(positions, match.getPositions());
        assertEquals(expectedtype, match.getType());
    }

    @Test
    void testEmptyMatch() {
        final Set<Position> positions = Set.of();
        final CellType type = CellType.MILK;
        final Match match = new MatchImpl(positions, type);

        assertTrue(match.isEmpty());
        assertEquals(0, match.getSize());
    }

}
