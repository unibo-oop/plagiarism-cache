package casim.model.codi.cell.builder.utils;

import java.util.EnumMap;
import java.util.function.Supplier;

import casim.model.codi.cell.CoDiCell;
import casim.model.codi.cell.CoDiCellState;
import casim.model.codi.cell.builder.CoDiCellBuilder;
import casim.model.codi.cell.builder.CoDiCellBuilderImpl;
import casim.model.codi.utils.CoDiUtils;
import casim.model.codi.utils.CoDiDirection;

/**
 * {@link CoDiCell} supplier  used to fill the initial grid with blank cells.
 */
public class CoDiCellSupplier implements Supplier<CoDiCell> {

    private static final int CHROMOSOME_PROBABILITY = 50;

    @Override
    public CoDiCell get() {
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        final EnumMap<CoDiDirection, Integer> neighborsPreviousInput = CoDiUtils.newFilledEnumMap(() -> 0);
        final EnumMap<CoDiDirection, Boolean> chromosome =
                CoDiUtils.newFilledEnumMap(() -> CoDiUtils.booleanWithSpecificProbability(CHROMOSOME_PROBABILITY));
        return builder.state(CoDiCellState.BLANK)
                    .activationCounter(0)
                    .chromosome(chromosome)
                    .neighborsPreviousInput(neighborsPreviousInput)
                    .build();
    }

}
