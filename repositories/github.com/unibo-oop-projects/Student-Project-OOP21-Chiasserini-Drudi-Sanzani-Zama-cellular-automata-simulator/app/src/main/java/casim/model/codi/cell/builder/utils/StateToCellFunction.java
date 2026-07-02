package casim.model.codi.cell.builder.utils;

import java.util.EnumMap;
import java.util.function.Function;

import casim.model.codi.cell.CoDiCell;
import casim.model.codi.cell.CoDiCellState;
import casim.model.codi.cell.builder.CoDiCellBuilder;
import casim.model.codi.cell.builder.CoDiCellBuilderImpl;
import casim.model.codi.utils.CoDiUtils;
import casim.model.codi.utils.CoDiDirection;

/**
 * {@link Function} used to map a {@link CoDiCellState} to a new {@link CoDiCell} with that state.
 */
public class StateToCellFunction implements Function<CoDiCellState, CoDiCell> {

    private static final int CHROMOSOME_PROBABILITY = 50;

    @Override
    public CoDiCell apply(final CoDiCellState state) {
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        final EnumMap<CoDiDirection, Integer> neighborsPreviousInput = CoDiUtils.newFilledEnumMap(() -> 0);
        final EnumMap<CoDiDirection, Boolean> chromosome =
                CoDiUtils.newFilledEnumMap(() -> CoDiUtils.booleanWithSpecificProbability(CHROMOSOME_PROBABILITY));
        return builder.state(state)
            .activationCounter(0)
            .chromosome(chromosome)
            .neighborsPreviousInput(neighborsPreviousInput)
            .build();
    }

}
