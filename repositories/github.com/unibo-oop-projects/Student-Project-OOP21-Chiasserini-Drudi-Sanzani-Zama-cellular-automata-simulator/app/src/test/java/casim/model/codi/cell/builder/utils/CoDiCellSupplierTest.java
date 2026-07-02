package casim.model.codi.cell.builder.utils;

import java.util.EnumMap;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import casim.model.codi.cell.CoDiCell;
import casim.model.codi.cell.CoDiCellState;
import casim.model.codi.utils.CoDiUtils;
import casim.model.codi.utils.CoDiDirection;

/**
 * Test class for {@link CoDiCellSupplier}.
 */
class CoDiCellSupplierTest {

    private static final int ACTIVATION_COUNTER = 0;
    private static final EnumMap<CoDiDirection, Integer> ENUMMAP = 
            CoDiUtils.newFilledEnumMap(() -> 0);
    private static final CoDiCellState STATE = CoDiCellState.BLANK;

    /**
     * Test for {@link CoDiCellSupplier#get()}.
     */
    @Test
    void testGet() {
       final CoDiCellSupplier supplier = new CoDiCellSupplier();
       final CoDiCell cell = supplier.get();
       Assert.assertEquals(cell.getActivationCounter(), ACTIVATION_COUNTER);
       Assert.assertEquals(cell.getState(), STATE);
       Assert.assertEquals(cell.getNeighborsPreviousInput(), ENUMMAP);
       Assert.assertTrue(cell.getGate().isEmpty());
    }
}
