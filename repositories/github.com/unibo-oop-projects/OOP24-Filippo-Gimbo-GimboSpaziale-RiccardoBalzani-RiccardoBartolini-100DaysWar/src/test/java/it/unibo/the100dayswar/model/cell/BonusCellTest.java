package it.unibo.the100dayswar.model.cell;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;



import it.unibo.the100dayswar.model.cell.api.BonusCell;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.cell.impl.BonusCellImpl;
import it.unibo.the100dayswar.model.cell.impl.CellImpl;
import it.unibo.the100dayswar.commons.utilities.impl.PositionImpl;
import org.junit.jupiter.api.Test;


class BonusCellTest {

    @Test
    void testBonusActivation() {
    final Cell baseCell = new CellImpl(new PositionImpl(3, 3), true, true);
    final BonusCell bonusCell = new BonusCellImpl(baseCell);

    assertTrue(bonusCell.isBonusActive(), "Bonus should be active initially");
    bonusCell.setBonusActive(false);
    assertFalse(bonusCell.isBonusActive(), "Bonus should be inactive after deactivation");
    }

    @Test
    void testBonusAmount() {
    final Cell baseCell = new CellImpl(new PositionImpl(5, 5), true, false);
    final BonusCell bonusCell = new BonusCellImpl(baseCell);

    assertEquals(100, bonusCell.getAmount(), "Bonus amount should be 100");
    }
}

