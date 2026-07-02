package it.unibo.the100dayswar.model.cell;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import java.util.Optional;

import it.unibo.the100dayswar.commons.utilities.impl.PositionImpl;
import it.unibo.the100dayswar.model.cell.impl.CellImpl;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.impl.HumanPlayerImpl;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.soldier.impl.SoldierImpl;
import it.unibo.the100dayswar.model.soldier.api.Soldier;
import it.unibo.the100dayswar.model.player.api.HumanPlayer;

class CellTest {

    @Test
    void testIsBuildable() {
        final Cell cell = new CellImpl(new PositionImpl(0, 0), true, false);

        assertTrue(cell.isBuildable());
        assertFalse(cell.isSpawn());
    }

    @Test
    void testIsFree() {
        final Cell cell = new CellImpl(new PositionImpl(0, 1), true, true);

        assertTrue(cell.isFree());

        final HumanPlayer player = new HumanPlayerImpl("Player1", cell);
        final Soldier soldier = new SoldierImpl(player);
        cell.setOccupation(Optional.of(soldier));

        assertFalse(cell.isFree());
    }

    @Test
    void testSetAndGetOccupation() {
        final Cell cell = new CellImpl(new PositionImpl(0, 2), true, true);

        assertTrue(cell.getUnit().isEmpty());

        final Player player = new HumanPlayerImpl("Player1", cell);
        final Soldier soldier = new SoldierImpl(player);
        cell.setOccupation(Optional.of(soldier));

        assertTrue(cell.getUnit().isPresent());
        assertEquals(soldier, cell.getUnit().get());
    }

}
