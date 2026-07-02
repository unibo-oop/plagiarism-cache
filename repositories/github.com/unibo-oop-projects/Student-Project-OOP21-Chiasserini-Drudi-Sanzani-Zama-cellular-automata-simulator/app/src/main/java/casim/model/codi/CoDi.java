package casim.model.codi;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.abstraction.automaton.AbstractAutomaton;
import casim.model.abstraction.utils.NeighborsFunctions;
import casim.model.abstraction.utils.stats.Stats;
import casim.model.codi.cell.CoDiCell;
import casim.model.codi.cell.CoDiCellState;
import casim.model.codi.cell.builder.CoDiCellBuilder;
import casim.model.codi.cell.builder.CoDiCellBuilderImpl;
import casim.model.codi.cell.builder.utils.StateToCellFunction;
import casim.model.codi.rule.GrowthUpdateRule;
import casim.model.codi.rule.SignalingUpdateRule;
import casim.model.codi.utils.CoDiDirection;
import casim.model.codi.utils.stats.CoDiStatsImpl;
import casim.utils.coordinate.Coordinates3D;
import casim.utils.coordinate.CoordinatesUtil;
import casim.utils.grid.Grid2D;
import casim.utils.grid.Grid2DImpl;
import casim.utils.grid.Grid3D;
import casim.utils.grid.Grid3DImpl;
import casim.utils.grid.GridUtils;
import casim.utils.range.Ranges;

/**
 * Implementation of CoDi Automaton.
 */
public class CoDi extends AbstractAutomaton<CoDiCellState, CoDiCell> {

    private static final int RIGHT_SLIDE = 1;
    private static final int LEFT_SLIDE = -1;
    private static final int RANDOM_ACTIVATION_COUNTER = 32;

    private int outputLayer;
    private boolean changed; 
    private Grid3D<CoDiCell> state;
    private boolean hasSetupSignaling;
    private final Random rng = new Random();
    private final GrowthUpdateRule growthUpdateRule;
    private final SignalingUpdateRule signalingUpdateRule;


    /**
     * Constructor of {@link CoDi}.
     * 
     * @param state the grid of {@link CoDiCellState} used to initialize the automaton.
     */
    public CoDi(final Grid3D<CoDiCellState> state) {
        this.changed = true;
        this.outputLayer = 0;
        this.hasSetupSignaling = false;
        final Function<CoDiCellState, CoDiCell> cellFunction = new StateToCellFunction();
        this.state = state.map(s -> cellFunction.apply(s));
        this.growthUpdateRule = new GrowthUpdateRule(NeighborsFunctions::neighbors3DFunction);
        this.signalingUpdateRule = new SignalingUpdateRule(NeighborsFunctions::neighbors3DFunction);
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    protected Grid2D<CoDiCell> doStep() {
        if (changed) {
            this.changed = false;
            return growthStep();
        } else {
            if (!hasSetupSignaling) {
                signalingSetup();
            }
            return signalStep();
        }
    }

    private Grid2D<CoDiCell> growthStep() { 
        final var newState = new Grid3DImpl<CoDiCell>(this.state.getHeight(), this.state.getWidth(), this.state.getDepth());
        for (final var coord: this.visitGrid()) {
            CoDiCell cell = this.state.get(coord);
            final CoDiCellState oldCellState = cell.getState();
            cell = this.growthUpdateRule.getNextCell(Pair.of(coord, cell), this.state); 
            if (oldCellState != cell.getState()) {
                this.changed = true;
            }
            newState.set(coord, cell);
        }
        return this.computeNewState(newState);
    }

    private Grid2D<CoDiCell> signalStep() {
        final var newState = new Grid3DImpl<CoDiCell>(this.state.getHeight(), this.state.getWidth(), this.state.getDepth());
        for (final var coord: this.visitGrid()) {
            final CoDiCell cell = this.signalingUpdateRule.getNextCell(Pair.of(coord, this.state.get(coord)), this.state);
            newState.set(coord, cell);
        }
        return this.computeNewState(newState);
    }

    private Grid2D<CoDiCell> computeNewState(final Grid3D<CoDiCell> newState) {
        this.state = newState;
        this.kicking();
        return this.getGrid();
    }

    private void signalingSetup() {
        this.hasSetupSignaling = true;
        for (final var coord: this.visitGrid()) {
            final CoDiCell cell = this.state.get(coord);
            if (cell.getState().equals(CoDiCellState.NEURON)) { 
                cell.setActivationCounter(this.rng.nextInt(RANDOM_ACTIVATION_COUNTER));
            } else {
                cell.setActivationCounter(0);
            }
        }
    }

    private List<Coordinates3D<Integer>> visitGrid() {
        return GridUtils.get3dCoordStream(this.state.getWidth(), this.state.getHeight(), this.state.getDepth())
                .collect(Collectors.toList());
    }

    /**
     * Change the output layer of a delta variation.
     * 
     * @param delta the variation of the output layer.
     * @return the {@link Grid2D} representing the new layer selected.
     */
    private Grid2D<CoDiCell> changeOutputLayer(final int delta) {
        if (this.outputLayer + delta >= 0 && this.outputLayer + delta < this.state.getWidth()) {
            this.outputLayer += delta;
        }
        return this.getGrid();
    }

    /** 
     * Do a right shift of the output layer.
     * 
     * @return the {@link Grid2D} representing the new layer.
     */
    public Grid2D<CoDiCell> outputLayerRightShift() {
        return this.changeOutputLayer(RIGHT_SLIDE);
    }

    /**
     * Do a left shift of the output layer.
     * 
     * @return the {@link Grid2D} representing the new layer.
     */
    public Grid2D<CoDiCell> outputLayerLeftShift() {
        return this.changeOutputLayer(LEFT_SLIDE);
    }

    @Override
    public Grid2D<CoDiCell> getGrid() {
        final int x = this.outputLayer;
        final Grid2D<CoDiCell> gridLayer = new Grid2DImpl<>(this.state.getWidth(), this.state.getDepth());
        for (final var z: Ranges.of(0, this.state.getDepth())) {
            for (final var y: Ranges.of(0, this.state.getHeight())) {
                final var coord2D = CoordinatesUtil.of(y, z);
                final var coord3D = CoordinatesUtil.of(x, y, z);
                CoDiCell cell = this.state.get(coord3D);
                if ((cell.getState() == CoDiCellState.AXON || cell.getState() == CoDiCellState.DENDRITE) 
                        && cell.getActivationCounter() != 0) {
                    cell = this.getActiveCell(cell);
                }
                gridLayer.set(coord2D, cell);
            }
        }
        return gridLayer;
    }

    private CoDiCell getActiveCell(final CoDiCell cell) {
        final CoDiCellBuilder builder = new CoDiCellBuilderImpl();
        return builder.gate(cell.getGate())
                      .activationCounter(cell.getActivationCounter())
                      .chromosome(cell.getChromosome())
                      .neighborsPreviousInput(cell.getNeighborsPreviousInput())
                      .state((cell.getState() == CoDiCellState.AXON) ? CoDiCellState.ACTIVATE_AXON : CoDiCellState.ACTIVATE_DENDRITE)
                      .build();
    }

    private void kicking() {
        this.kickPositiveDirections();
        this.kickNegativeDirections();
    }

    private void kickPositiveDirections() {
        for (int z = 0; z < this.state.getDepth(); z++) {
            for (int y = 0; y < this.state.getHeight(); y++) {
                for (int x = 0; x < this.state.getWidth(); x++) {
                    final Coordinates3D<Integer> coord = CoordinatesUtil.of(x, y, z);
                    this.setStateValueWithCheck(coord, CoordinatesUtil.of(x, y, z + 1), CoDiDirection.NORTH);
                    this.setStateValueWithCheck(coord, CoordinatesUtil.of(x, y + 1, z), CoDiDirection.TOP);
                    this.setStateValueWithCheck(coord, CoordinatesUtil.of(x + 1, y, z), CoDiDirection.EAST);
                }
            }
        }
    }

    private void kickNegativeDirections() {
        for (int z = this.state.getDepth() - 1; z >= 0; z--) {
            for (int y = this.state.getHeight() - 1; y >= 0; y--) {
                for (int x = this.state.getWidth() - 1; x >= 0; x--) {
                    final Coordinates3D<Integer> coord = CoordinatesUtil.of(x, y, z);
                    this.setStateValueWithCheck(coord, CoordinatesUtil.of(x, y, z - 1), CoDiDirection.SOUTH);
                    this.setStateValueWithCheck(coord, CoordinatesUtil.of(x, y - 1, z), CoDiDirection.BOTTOM);
                    this.setStateValueWithCheck(coord, CoordinatesUtil.of(x - 1, y, z), CoDiDirection.WEST);
                }
            }
        }
    }

    private void setStateValueWithCheck(final Coordinates3D<Integer> coord, final Coordinates3D<Integer> neighbourCoord,
            final CoDiDirection direction) {
        if (this.state.isCoordValid(neighbourCoord) 
                &&  this.state.get(coord).getNeighborsPreviousInput().containsKey(direction)) {
            this.state.get(coord).setNeighborsPreviousInputDirection(direction,
                    this.state.get(neighbourCoord).getSpecificNeighborsPreviousInput(direction).get());
        } else {
            this.state.get(coord).setNeighborsPreviousInputDirection(direction, 0);
        }
    }

    @Override
    public Stats<CoDiCellState> getStats() {
        return new CoDiStatsImpl(this.getIterationCounter(), this.createStatesMap(), this.outputLayer);
    }

}
