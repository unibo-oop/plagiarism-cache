package casim.model.codi.cell;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import casim.model.codi.cell.builder.CoDiCellBuilder;
import casim.model.codi.cell.builder.CoDiCellBuilderImpl;
import casim.model.codi.cell.builder.utils.CoDiCellSupplier;
import casim.model.codi.utils.CoDiDirection;

/**
 * Test class for {@link CoDiCell}.
 */
class CoDiCellTest {

    private static final int TEST_VALUE = 10;
    private static final CoDiDirection DIRECTION = CoDiDirection.NORTH;

    private final CoDiCellSupplier supplier = new CoDiCellSupplier();
    private final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
    private CoDiCell cell = supplier.get();

    /**
     * Test for {@link CoDiCell#getGate()}.
     */
    @Test
    void testGetGate() {
       Assert.assertTrue(cell.getGate().isEmpty());
       Assert.assertTrue(cell.getOppositeToGate().isEmpty());
       cell = builder.activationCounter(cell.getActivationCounter())
                   .chromosome(cell.getChromosome())
                   .gate(Optional.of(DIRECTION))
                   .neighborsPreviousInput(cell.getNeighborsPreviousInput())
                   .state(cell.getState())
                   .build();
       Assert.assertTrue(cell.getGate().isPresent());
       Assert.assertTrue(cell.getOppositeToGate().isPresent());
       Assert.assertEquals(cell.getGate().get(), DIRECTION);
    }

    /**
     * Test for {@link CoDiCell#setActivationCounter(int)}.
     */
    @Test
    void testSetActivationCounter() {
        cell.setActivationCounter(TEST_VALUE);
        Assert.assertEquals(cell.getActivationCounter(), TEST_VALUE);
    }

    /**
     * Test for {@link CoDiCell#getSpecificNeighborsPreviousInput(CoDiDirection)}.
     */
    @Test
    void testSetSpecificNeighborsDirection() {
        cell.setNeighborsPreviousInputDirection(DIRECTION, TEST_VALUE);
        Assert.assertTrue(cell.getNeighborsPreviousInput().containsKey(DIRECTION));
        Assert.assertEquals((int) cell.getSpecificNeighborsPreviousInput(DIRECTION).get(), TEST_VALUE);
    }

    /**
     * Test for {@link CoDiCell#getSpecificNeighborsPreviousInput(CoDiDirection)}.
     */
    @Test
    void testGetSpecificNeighborsDirection() {
        final var newEnumMap = cell.getNeighborsPreviousInput();
        newEnumMap.remove(DIRECTION);
        cell = builder.activationCounter(cell.getActivationCounter())
                .chromosome(cell.getChromosome())
                .gate(cell.getGate())
                .state(cell.getState())
                .neighborsPreviousInput(newEnumMap)
                .build();
        Assert.assertTrue(cell.getSpecificNeighborsPreviousInput(DIRECTION).isEmpty());
    }

}
