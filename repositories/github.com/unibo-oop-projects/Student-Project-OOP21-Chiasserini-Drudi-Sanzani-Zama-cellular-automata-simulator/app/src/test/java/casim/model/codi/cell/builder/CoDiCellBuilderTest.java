package casim.model.codi.cell.builder;

import java.util.EnumMap;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import casim.model.codi.cell.CoDiCell;
import casim.model.codi.cell.CoDiCellState;
import casim.model.codi.utils.CoDiUtils;
import casim.model.codi.utils.CoDiDirection;

/**
 * Test class for {@link CoDiCellBuilder}.
 */
class CoDiCellBuilderTest {

    private static final int CHROMOSOME_PROBABILITY = 50;
    private static final CoDiCellState STATE = CoDiCellState.BLANK;

    /**
     * Test for {@link CoDiCellBuilder#build()} to check if {@link CoDiCellBuilderImpl}
     * build without the right arguments.
     */
    @Test
    void testBuildWithoutArguments() {
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        Assert.assertThrows(IllegalArgumentException.class, () -> builder.build());
    }

    /**
     * Test for {@link CoDiCellBuilder#build()}.
     */
    @Test
    void testBuild() {
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        final EnumMap<CoDiDirection, Integer> neighborsPreviousInput = CoDiUtils.newFilledEnumMap(() -> 0);
        final EnumMap<CoDiDirection, Boolean> chromosome =
                CoDiUtils.newFilledEnumMap(() -> CoDiUtils.booleanWithSpecificProbability(CHROMOSOME_PROBABILITY));
        builder.state(STATE)
               .activationCounter(0)
               .chromosome(chromosome)
               .neighborsPreviousInput(neighborsPreviousInput);
        final CoDiCell cell = builder.build();
        Assert.assertTrue(cell.getGate().isEmpty());
        Assert.assertThrows(IllegalArgumentException.class, () -> builder.build());
    }

}
