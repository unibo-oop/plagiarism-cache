package model.intelligence;

import java.util.LinkedList;
import java.util.List;

import model.match.PlaygroundBattle;
import model.util.Pair;

/**
 * This class represents a specific kind of {@link ArtificialIntelligence} and
 * its implementation is related to the {@link IntelligenceComputation}.
 * 
 */
public class BasicArtificialIntelligence extends ArtificialIntelligence {

    private List<Pair<Integer, Integer>> attackGrid;

    public BasicArtificialIntelligence(final IntelligenceComputation intelligence) {
        super(intelligence);
    }

    @Override
    public final PlaygroundBattle initShipsOnGrid() {
        this.attackGrid = new LinkedList<Pair<Integer, Integer>>();
        return this.getIntelligenceComputation().initShips();
    }

    @Override
    public final Pair<Integer, Integer> setNextHitPoint() {
        final Pair<Integer, Integer> nextPoint = this.getIntelligenceComputation().setNextToHit(this.attackGrid);
        this.updateGrids(nextPoint);
        return nextPoint;
    }

    private void updateGrids(final Pair<Integer, Integer> nextPoint) {
        this.attackGrid.add(nextPoint);
    }
}
