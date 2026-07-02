package it.unibo.modularcheckers.model.moveset;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import it.unibo.modularcheckers.model.Color;
import it.unibo.modularcheckers.model.Coordinate;
import it.unibo.modularcheckers.model.move.Step;
import it.unibo.modularcheckers.model.move.Tree;
import it.unibo.modularcheckers.model.piece.PieceType;

/**
 * Checkers implementation for AbstractStepTreeFilter.
 */
public class CheckersStepTreeFilter extends AbstractStepTreeFilter {

    // Coordinates
    private Map<Coordinate, Color> kings;

    /**
     * Sole constructor. Initialize Kings to HashSet.
     */
    public CheckersStepTreeFilter() {
        super();
        this.kings = new HashMap<>();
    }

    /**
     * {@inheritDoc}. In checkers, only the most convenient move is possible.
     */
    @Override
    protected void applyProperFilters() {
        calculateKingCoordinates();
        filterByHeight();
        filterKing();
        leaveOnlyKingEat();
        trimAllTrees();
    }

    /**
     * Removes all the trees that have a height < of the tree with max height. This
     * is the first filter.
     */
    private void filterByHeight() {
        if (getStepTrees().values().isEmpty()) {
            throw new IllegalStateException("No moves are possible anymore.");
        }
        int maxHeight = getStepTrees().values().stream().mapToInt(t -> t.height()).max().getAsInt();
        setStepTrees(getStepTrees().entrySet().stream().filter(e -> e.getValue().height() >= maxHeight)
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue())));
    }

    /**
     * Leave only Trees where the piece moving is a King.
     */
    private void filterKing() {
        if (kings.values().contains(this.getActualTurn())) {
            setStepTrees(getStepTrees().entrySet().stream().filter(e -> kings.keySet().contains(e.getKey()))
                    .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue())));
        }
    }

    /**
     * Check if some trees contains a Step where a King is eaten. if so, delete all
     * the others, then run leaveMajorKingEat method, else leave everything as it
     * is.
     */
    private void leaveOnlyKingEat() {
        // If there are opposing Kings.
        if (kings.values().stream().anyMatch(c -> !c.equals(getActualTurn()))) {
            Map<Coordinate, Tree<Step>> kingsEatean = getStepTrees().entrySet().stream()
                    .filter(e -> e.getValue().getAllValues().stream().anyMatch(s -> s.getDeadPiece().isPresent()))
                    .filter(e -> e.getValue().getAllValues().stream()
                            .anyMatch(s -> kings.containsKey(s.getDeadPiece().get().getX())))
                    .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
            if (!kingsEatean.isEmpty()) {
                setStepTrees(kingsEatean);
                leaveMajorKingEat();
            }
        }
    }

    /**
     * Called after leaveOnlyKingEat, leave only trees where the number of kings
     * eatable is max. Call
     */
    private void leaveMajorKingEat() {
        if (getStepTrees().keySet().size() > 1) {
            int max = Math.toIntExact(getStepTrees().values().stream()
                    .mapToLong(t -> t.getAllValues().stream().filter(s -> s.getDeadPiece().isPresent())
                            .filter(s -> s.getDeadPiece().get().getY().getType().equals(PieceType.KING)).count())
                    .max().getAsLong());
            setStepTrees(getStepTrees().entrySet().stream()
                    .filter(e -> e.getValue().getAllValues().stream().filter(s -> s.getDeadPiece().isPresent())
                            .filter(s -> s.getDeadPiece().get().getY().getType().equals(PieceType.KING)).count() >= max)
                    .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue())));
            leaveOnlyFirstKing();
        }
    }

    /**
     * Eliminate all the trees where the King appears farther than the others.
     */
    private void leaveOnlyFirstKing() {
        if (getStepTrees().keySet().size() > 1) {
            int level = getStepTrees().values().stream().mapToInt(t -> calculateFirstKingLevel(t)).min().getAsInt();
            setStepTrees(getStepTrees().entrySet().stream().filter(e -> calculateFirstKingLevel(e.getValue()) <= level)
                    .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue())));
        }
    }

    private int calculateFirstKingLevel(final Tree<Step> tree) {
        // TODO
        return 0;
    }

    /**
     * Place in the King set the coordinates of the kings.
     */
    private void calculateKingCoordinates() {
        this.kings = getBoard().getBlocks().keySet().stream()
                .filter(c -> getBoard().getBlock(c).getPiece().get().getType().equals(PieceType.KING))
                .collect(Collectors.toMap(c -> c, c -> getBoard().getBlock(c).getPiece().get().getColor()));
    }

    private boolean isKing(final Coordinate coord) {
        return kings.containsKey(coord);
    }

    /**
     * Delete all the branches shorter than maxHeight on every Tree.
     */
    private void trimAllTrees() {
        getStepTrees().values().stream().peek(t -> t.balanceToHeight(t.height()));
    }
}
