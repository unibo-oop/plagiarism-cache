package controller.session;

import java.util.ArrayList;
import java.util.List;

import utility.Driver;
import utility.Pair;

/**
 * A possible implementation of QualifyingRank interface.
 */
public class QualifyingRankImpl implements QualifyingRank {

    private final List<Pair<Driver, Integer>> rank = new ArrayList<>();

    @Override
    public void addPlayer(final Driver drv, final int diceThrows) {
        if (rank.stream().anyMatch(x -> x.getX() == drv)) {
            throw new IllegalStateException();
        }
        int index = 0;
        boolean finished = false;
        while (index < rank.size() && !finished) {
            if (diceThrows < rank.get(index).getY()) {
                finished = true;
                rank.add(index, new Pair<>(drv, diceThrows));
            }
            index++;
        }
        if (!finished) {
            rank.add(new Pair<>(drv, diceThrows));
        }
    }

    @Override
    public List<Pair<Driver, Integer>> getRank() {
        return this.rank;
    }

}
