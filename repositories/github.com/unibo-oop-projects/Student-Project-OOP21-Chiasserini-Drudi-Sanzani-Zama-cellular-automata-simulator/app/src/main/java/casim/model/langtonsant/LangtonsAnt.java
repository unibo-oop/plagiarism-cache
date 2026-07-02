package casim.model.langtonsant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import casim.model.abstraction.automaton.AbstractAutomaton;
import casim.utils.Direction;
import casim.utils.coordinate.Coordinates2D;
import casim.utils.coordinate.CoordinatesUtil;
import casim.utils.grid.Grid2D;
import casim.utils.grid.WrappingGrid;

/**
 * Langton's Ant automaton, composed of a {@link Grid2D} of
 * {@link LangtonsAntCell}s and a list of {@link Ant}.
 */
public class LangtonsAnt extends AbstractAutomaton<LangtonsAntCellState, LangtonsAntCell> {
    /**
     * Minimum number of ants in the simulation.
     */
    public static final int MIN_ANTS = 10;
    /**
     * Maximum number of ants in the simulation.
     */
    public static final int MAX_ANTS = 20;

    private final List<Ant> ants = new ArrayList<>();
    private final Grid2D<LangtonsAntCell> state;
    private final Random rng = new Random();

    /**
     * Constructs a new Langont's Ant automaton.
     * 
     * @param state a {@link Grid2D} of {@link LangtonsAntCellState}
     * @param wrapping if true ants will warp to opposite side of
     *              the grid instead of dying when reaching edge.
     * representing the initial state of the Automaton.
     */
    public LangtonsAnt(final Grid2D<LangtonsAntCellState> state, final boolean wrapping) {
        final Grid2D<LangtonsAntCell> tmpState = state.map(x -> new LangtonsAntCell(x));
        this.state = wrapping ? new WrappingGrid<>(tmpState) : tmpState;
    }

    /**
     * Constructs a new Langton's Ant automaton.
     * 
     * @param state a {@link Grid2D} of {@link LangtonsAntCellState}
     *          representing the initial state of the Automaton.
     * @param ants a {@link List} of {@link Ant} 
     *          representing the initial ants in the Automaton.
     * @param wrapping if true ants will warp to opposite side of
     *              the grid instead of dying when reaching edge.
     */
    public LangtonsAnt(final Grid2D<LangtonsAntCellState> state, final List<Ant> ants, final boolean wrapping) {
        this(state, wrapping);
        this.ants.addAll(ants);
    }

    /**
     * Constructs a new Langton's Ant automaton.
     * 
     * @param state a {@link Grid2D} of {@link LangtonsAntCellState}
     *          representing the initial state of the Automaton.
     * @param antNumber the number of ants that will be randomly
     *          generated and will populate the Automaton.
     * @param wrapping if true ants will warp to opposite side of
     *              the grid instead of dying when reaching edge.
     */
    public LangtonsAnt(final Grid2D<LangtonsAntCellState> state, final int antNumber, final boolean wrapping) {
        this(state, wrapping);
        IntStream.range(0, antNumber).forEach((x) -> this.addAnt());
    }

    @Override
    public boolean hasNext() {
        return !this.ants.isEmpty();
    }

    @Override
    protected Grid2D<LangtonsAntCell> doStep() {
        this.ants.stream()
            .forEach(x -> this.antStep(x));
        this.removeAnts();
        return this.state;
    }

    private void antStep(final Ant ant) {
        ant.turn(this.state.get(ant.getPosition()).getState());
        this.state.set(ant.getPosition(), new LangtonsAntCell(
                (this.state.get(ant.getPosition()).getState() == LangtonsAntCellState.OFF)
                        ? LangtonsAntCellState.ON : LangtonsAntCellState.OFF));
        ant.move();
    }

    private void removeAnts() {
        this.ants.removeIf(x -> !this.state.isCoordValid(x.getPosition()));
    }

    /**
     * Adds an {@link Ant} at a specified position with a specified direction.
     * 
     * @param direction the {@link Direction} of the {@link Ant} to be added.
     * @param position the {@link Coordinates2D} representing the position
     * of the and to be added.
     * @return True if the {@link Coordinates2D} given as argument are valid,
     * False otherwise.
     */
    private boolean addAnt(final Direction direction, final Coordinates2D<Integer> position) {
        if (this.state.isCoordValid(position)) {
            this.ants.add(new Ant(direction, position));
            return true;
        }
        return false;
    }

    /**
     * Adds an {@link Ant} at a random position with a random {@link Direction}.
     */
    private void addAnt() {
        final var position = CoordinatesUtil.random(state.getWidth(), state.getHeight());
        final var direction = Direction.values()[this.rng.nextInt(Direction.values().length)];
        this.addAnt(direction, position);
    }

    @Override
    public Grid2D<LangtonsAntCell> getGrid() {
        return this.state;
    }

}
