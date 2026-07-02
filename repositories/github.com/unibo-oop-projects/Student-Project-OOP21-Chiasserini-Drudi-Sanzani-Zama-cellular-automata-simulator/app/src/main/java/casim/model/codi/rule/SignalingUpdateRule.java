package casim.model.codi.rule;

import java.util.EnumMap;
import java.util.List;
import java.util.function.BiFunction;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.abstraction.rule.AbstractUpdateRule;
import casim.model.codi.cell.CoDiCell;
import casim.model.codi.cell.builder.CoDiCellBuilder;
import casim.model.codi.cell.builder.CoDiCellBuilderImpl;
import casim.model.codi.utils.CoDiUtils;
import casim.model.codi.utils.CoDiDirection;
import casim.utils.coordinate.Coordinates3D;
import casim.utils.grid.Grid;

/**
 * Implementation of the {@link AbstractUpdateRule} for CoDi automaton.
 */
public class SignalingUpdateRule extends AbstractUpdateRule<Coordinates3D<Integer>, CoDiCell> {

    private static final int NEURON_ACTIVATION_VALUE = 31;
    private static final int SIGNAL_LIMIT = 2;

    /**
     * Constructor of {@link SignalingUpdateRule}.
     * 
     * @param neighborsFunction it takes as input a pair {@link Coordinates3D}+{@link CoDiCell} 
     * and the {@link Grid} of the automaton and return a list of pair {@link Coordinates3D}+{@link CoDiCell}
     * of all the neighbors of the cell taken as input.
     */
    public SignalingUpdateRule(final BiFunction<Pair<Coordinates3D<Integer>, CoDiCell>,
            Grid<Coordinates3D<Integer>, CoDiCell>, List<Pair<Coordinates3D<Integer>, CoDiCell>>> neighborsFunction) {
        super(neighborsFunction);
    }

    @Override
    protected CoDiCell nextCell(final Pair<Coordinates3D<Integer>, CoDiCell> cellPair,
            final List<Pair<Coordinates3D<Integer>, CoDiCell>> neighborsPairs) {
        final CoDiCell cell = cellPair.getRight();
        CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        switch (cell.getState()) {
            case BLANK:
                builder.activationCounter(cell.getActivationCounter());
                builder.neighborsPreviousInput(CoDiUtils.newFilledEnumMap(() -> 0));
                break;
            case NEURON:
                builder = this.neuronSignal(cell);
                break;
            case AXON:
                builder = this.axonSignal(cell);
                break;
            case DENDRITE:
                builder = this.dendriteSignal(cell);
                break;
            default:
                break;
            }
        return builder.state(cell.getState())
                      .gate(cell.getGate())
                      .chromosome(cell.getChromosome())
                      .build();
    }

    private CoDiCellBuilder neuronSignal(final CoDiCell cell) {
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        final EnumMap<CoDiDirection, Integer> neighborsPreviousInput = CoDiUtils.newFilledEnumMap(() -> 0);
        int inputSum = 1 + CoDiUtils.sumEnumMapValues(cell.getNeighborsPreviousInput())
            - cell.getNeighborsPreviousInput().get(cell.getGate().get());
        if (cell.getNeighborsPreviousInput().containsKey(cell.getOppositeToGate().get())) {
            inputSum -= cell.getNeighborsPreviousInput().get(cell.getOppositeToGate().get());
        }
        if (cell.getActivationCounter() + inputSum > NEURON_ACTIVATION_VALUE) {
            neighborsPreviousInput.put(cell.getGate().get(), 1);
            neighborsPreviousInput.put(cell.getOppositeToGate().get(), 1);
            builder.activationCounter(0);
        } else {
            builder.activationCounter(cell.getActivationCounter() + inputSum);
        }
        return builder.neighborsPreviousInput(neighborsPreviousInput);
    }

    private CoDiCellBuilder axonSignal(final CoDiCell cell) {
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        final int gateValue = cell.getNeighborsPreviousInput().get(cell.getGate().get());
        final EnumMap<CoDiDirection, Integer> neighborsPreviousInput =
                CoDiUtils.newFilledEnumMap(() -> gateValue);
        builder.activationCounter((gateValue != 0) ? 1 : 0);
        return builder.neighborsPreviousInput(neighborsPreviousInput);
    }

    private CoDiCellBuilder dendriteSignal(final CoDiCell cell) {
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        final EnumMap<CoDiDirection, Integer> neighborsPreviousInput = CoDiUtils.newFilledEnumMap(() -> 0);
        int inputSum = CoDiUtils.sumEnumMapValues(cell.getNeighborsPreviousInput());
        inputSum = inputSum > SIGNAL_LIMIT ? SIGNAL_LIMIT : inputSum; 
        neighborsPreviousInput.put(cell.getGate().get(), inputSum);
        builder.activationCounter((inputSum != 0) ? 1 : 0);
        return builder.neighborsPreviousInput(neighborsPreviousInput);
    }

}
