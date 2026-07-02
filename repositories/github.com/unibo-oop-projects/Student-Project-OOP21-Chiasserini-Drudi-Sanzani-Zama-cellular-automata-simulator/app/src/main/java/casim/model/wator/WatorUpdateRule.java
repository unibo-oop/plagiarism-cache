package casim.model.wator;

import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.abstraction.rule.AbstractUpdateRule;
import casim.utils.coordinate.Coordinates2D;
import casim.utils.grid.Grid;

/**
 * Predators and Preys {@link AbstractUpdateRule} implementation.
 */
//Package-private
class WatorUpdateRule extends AbstractUpdateRule<Coordinates2D<Integer>, WatorCell> {

    private static final int DEAD_HEALTH = 0;
    private static final String UNKNOWN_STATE = "Unknown state.";

    private final Random rng = new Random();

    //Package-private
    WatorUpdateRule(
            final BiFunction<
                Pair<Coordinates2D<Integer>, WatorCell>,
                Grid<Coordinates2D<Integer>, WatorCell>,
                List<Pair<Coordinates2D<Integer>, WatorCell>>> neighborsFunction) {
        super(neighborsFunction);
    }

    @Override
    protected WatorCell nextCell(final Pair<Coordinates2D<Integer>, WatorCell> cellPair,
            final List<Pair<Coordinates2D<Integer>, WatorCell>> neighborsPairs) {
        final var currCell = cellPair.getValue();
        final List<WatorCell> neighborsList = neighborsPairs.stream()
                .map(Pair::getRight)
                .collect(Collectors.toList());
        switch (currCell.getState()) {
            case PREY:
                return this.preyStep(currCell, neighborsList);
            case PREDATOR:
                final var preyNeighbors = this.getFilteredNeighbors(neighborsList, WatorCellState.PREY);
                final var deadNeighbors = this.getFilteredNeighbors(neighborsList, WatorCellState.DEAD);
                if (preyNeighbors.size() > 0) {
                    return this.predatorStep(currCell, preyNeighbors, WatorCell::heal);
                } else if (deadNeighbors.size() > 0) {
                    return this.predatorStep(currCell, deadNeighbors, WatorCell::starve);
                }
                currCell.starve();
                currCell.setMoved();
                this.applyDeath(currCell);
                return currCell;
            case DEAD:
                return currCell;
            default:
                throw new IllegalStateException(UNKNOWN_STATE);
        }
    }

    private WatorCell predatorStep(final WatorCell currentCell, final List<WatorCell> neighbors,
            final Consumer<WatorCell> movementAction) {
        if (this.applyDeath(currentCell) || currentCell.hasMoved()) {
            return currentCell;
        }
        currentCell.setMoved();
        final var toChange = neighbors.get(this.rng.nextInt(neighbors.size()));
        toChange.clone(currentCell);
        movementAction.accept(toChange);
        toChange.setMoved();
        return toChange.reproduce();
    }

    private boolean applyDeath(final WatorCell currentCell) {
        if (!currentCell.getState().equals(WatorCellState.DEAD) && currentCell.isDead()) {
            final var dead = new WatorCell(WatorCellState.DEAD, DEAD_HEALTH);
            currentCell.clone(dead);
            return true;
        } else {
            return false;
        }
    }

    private WatorCell preyStep(final WatorCell currentCell, final List<WatorCell> neighborsList) {
        if (currentCell.hasMoved()) {
            return currentCell;
        }
        currentCell.setMoved();
        final var chosenNeighbor = neighborsList.get(this.rng.nextInt(neighborsList.size()));
        if (chosenNeighbor.getState().equals(WatorCellState.DEAD)) {
            chosenNeighbor.clone(currentCell);
            chosenNeighbor.setMoved();
            chosenNeighbor.heal();
            return currentCell.reproduce();
        }
        return currentCell;
    }

    private List<WatorCell> getFilteredNeighbors(final List<WatorCell> neighbors, final WatorCellState filter) {
        return neighbors.stream()
                .filter(x -> x.getState().equals(filter))
                .collect(Collectors.toList());
    }
}
