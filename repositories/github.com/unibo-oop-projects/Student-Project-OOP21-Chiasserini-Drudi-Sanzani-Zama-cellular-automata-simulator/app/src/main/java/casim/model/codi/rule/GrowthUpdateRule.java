package casim.model.codi.rule;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.abstraction.rule.AbstractUpdateRule;
import casim.model.codi.cell.CoDiCell;
import casim.model.codi.cell.CoDiCellState;
import casim.model.codi.cell.builder.CoDiCellBuilder;
import casim.model.codi.cell.builder.CoDiCellBuilderImpl;
import casim.model.codi.utils.CoDiUtils;
import casim.model.codi.utils.CoDiDirection;
import casim.utils.coordinate.Coordinates3D;
import casim.utils.coordinate.CoordinatesUtil;
import casim.utils.grid.Grid;

/**
 * Implementation of the {@link AbstractUpdateRule} for CoDi growth phase.
 */
public class GrowthUpdateRule extends AbstractUpdateRule<Coordinates3D<Integer>, CoDiCell> {

    private static final int NEURON_GENERATION_PROBABILITY = 5;

    /**
     * Constructor of {@link SignalingUpdateRule}.
     * 
     * @param neighborsFunction it takes as input a pair {@link Coordinates3D}+{@link CoDiCell}
     * and the {@link Grid} of the automaton and return a list of pair {@link Coordinates3D}+{@link CoDiCell}
     * of all the neighbors of the cell taken as input.
     */
    public GrowthUpdateRule(final BiFunction<Pair<Coordinates3D<Integer>, CoDiCell>,
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
                builder = this.blankCellGrowth(cell, cellPair.getKey(), neighborsPairs);
                break;
            case NEURON:
                builder = this.neuronCellGrowth(cell);
                break;
            case AXON:
                builder = this.axonCellGrowth(cell); 
                break;
            case DENDRITE:
                builder = this.dendriteCellGrowth(cell);
                break;
            default:
                break;
        }
        return builder.chromosome(cell.getChromosome())
                      .activationCounter(cell.getActivationCounter())
                      .build();
    }

    private CoDiCellBuilder blankCellGrowth(final CoDiCell cell, final Coordinates3D<Integer> cellCoord,
            final List<Pair<Coordinates3D<Integer>, CoDiCell>> neighborsPairs) {
        if (this.isNeuronSeed(cellCoord)) {
            return this.blankToNeuron(cellCoord, neighborsPairs);
        }
        int inputSum = CoDiUtils.sumEnumMapValues(cell.getNeighborsPreviousInput());
        if (inputSum == 0) {
            return this.blankCell();
        }
        final EnumMap<CoDiDirection, Integer> neighborsPreviousInput = cell.getNeighborsPreviousInput();
        inputSum = CoDiUtils.sumEnumMapSpecificValues(neighborsPreviousInput, CoDiSignal.AXON_SIGNAL.getValue());
        if (inputSum == CoDiSignal.AXON_SIGNAL.getValue()) {
            return this.blankToAxon(cell);
        }
        if (inputSum > CoDiSignal.AXON_SIGNAL.getValue()) {
            return this.blankCell();
        }
        inputSum = CoDiUtils.sumEnumMapSpecificValues(neighborsPreviousInput, CoDiSignal.DENDRITE_SIGNAL.getValue());
        if (inputSum == CoDiSignal.DENDRITE_SIGNAL.getValue()) {
            return this.blankToDendrite(cell);
        }
        return this.blankCell();
    }

    private CoDiCellBuilder blankToNeuron(final Coordinates3D<Integer> cellCoord,
            final List<Pair<Coordinates3D<Integer>, CoDiCell>> neighborsPairs) {
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        final List<Coordinates3D<Integer>> neighborsCoordinates = neighborsPairs.stream()
                .map(p -> p.getLeft()).collect(Collectors.toList());
        final CoDiDirection gate = this.getNeuronGate(cellCoord, neighborsCoordinates); 
        final EnumMap<CoDiDirection, Integer> neighborsPreviousInput =
                CoDiUtils.newFilledEnumMap(() -> CoDiSignal.DENDRITE_SIGNAL.getValue());
        neighborsPreviousInput.put(gate, CoDiSignal.AXON_SIGNAL.getValue());
        neighborsPreviousInput.put(gate.getOpposite(), CoDiSignal.AXON_SIGNAL.getValue());
        return builder.neighborsPreviousInput(neighborsPreviousInput)
                .state(CoDiCellState.NEURON)
                .gate(Optional.of(gate));
    }

    private CoDiCellBuilder blankToAxon(final CoDiCell cell) {
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        final CoDiDirection direction = this.findSignalDirection(cell, CoDiSignal.AXON_SIGNAL).get();
        final EnumMap<CoDiDirection, Integer> neighborsPreviousInput =
                CoDiUtils.conditionalFillNeighborsPreviosInput(cell, CoDiSignal.AXON_SIGNAL.getValue(), 0);
        return builder.neighborsPreviousInput(neighborsPreviousInput)
                .state(CoDiCellState.AXON)
                .gate(Optional.of(direction));
    }

    private CoDiCellBuilder blankToDendrite(final CoDiCell cell) {
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        final CoDiDirection direction = this.findSignalDirection(cell, CoDiSignal.DENDRITE_SIGNAL).get();
        final EnumMap<CoDiDirection, Integer> neighborsPreviousInput =
                CoDiUtils.conditionalFillNeighborsPreviosInput(cell, CoDiSignal.DENDRITE_SIGNAL.getValue(), 0);
        return builder.neighborsPreviousInput(neighborsPreviousInput)
                .state(CoDiCellState.DENDRITE)
                .gate(Optional.of(direction.getOpposite()));
    }

    private CoDiCellBuilder blankCell() {
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        final EnumMap<CoDiDirection, Integer> neighborsPreviousInput = CoDiUtils.newFilledEnumMap(() -> 0);
        return builder.neighborsPreviousInput(neighborsPreviousInput)
                .state(CoDiCellState.BLANK);
    }

    private CoDiCellBuilder neuronCellGrowth(final CoDiCell cell) {
        final EnumMap<CoDiDirection, Integer> neighborsPreviousInput =
                CoDiUtils.newFilledEnumMap(() -> CoDiSignal.DENDRITE_SIGNAL.getValue());
        neighborsPreviousInput.put(cell.getGate().get(), CoDiSignal.AXON_SIGNAL.getValue());
        neighborsPreviousInput.put(cell.getOppositeToGate().get(), CoDiSignal.AXON_SIGNAL.getValue());
        return this.buildStateGateNeighborsInput(cell, neighborsPreviousInput);
    }

    private CoDiCellBuilder axonCellGrowth(final CoDiCell cell) {
        final EnumMap<CoDiDirection, Integer> neighborsPreviousInput =
                CoDiUtils.conditionalFillNeighborsPreviosInput(cell, CoDiSignal.AXON_SIGNAL.getValue(), 0);
        return this.buildStateGateNeighborsInput(cell, neighborsPreviousInput);
    }

    private CoDiCellBuilder dendriteCellGrowth(final CoDiCell cell) {
        final EnumMap<CoDiDirection, Integer> neighborsPreviousInput =
                CoDiUtils.conditionalFillNeighborsPreviosInput(cell, CoDiSignal.DENDRITE_SIGNAL.getValue(), 0);
        return this.buildStateGateNeighborsInput(cell, neighborsPreviousInput);
    }

    private CoDiCellBuilder buildStateGateNeighborsInput(final CoDiCell cell,
            final EnumMap<CoDiDirection, Integer> neighborsPreviousInput) {
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        return builder.neighborsPreviousInput(neighborsPreviousInput)
                .state(cell.getState())
                .gate(cell.getGate());
    }

    private Optional<CoDiDirection> findSignalDirection(final CoDiCell cell, final CoDiSignal signal) {
        return cell.getNeighborsPreviousInput().entrySet().stream().
                filter(e -> e.getValue() == signal.getValue()).
                map(Map.Entry::getKey).
                findFirst();
    }

    private CoDiDirection getNeuronGate(final Coordinates3D<Integer> cellCoord,
            final List<Coordinates3D<Integer>> neighborsCoordinates) {
        CoDiDirection direction = CoDiUtils.getRandomDirection();
        while (direction == CoDiDirection.EAST || direction == CoDiDirection.WEST
                || !neighborsCoordinates.contains(CoordinatesUtil.sumInt(cellCoord, direction.getDirectionOffset()))) {
            direction = CoDiUtils.getRandomDirection();
        }
        return direction;
    }

    private boolean isNeuronSeed(final Coordinates3D<Integer> coord) {
        return CoDiUtils.booleanWithSpecificProbability(NEURON_GENERATION_PROBABILITY) 
                    && coord.getY() % 2 + coord.getZ() % 2 == 0;
    }

}
