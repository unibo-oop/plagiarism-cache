package giocoscudetto.model.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import giocoscudetto.model.api.Table;
import giocoscudetto.model.api.match.Club;

/**
 * Implementation of Table interface.
 */
public class TableImpl implements Table {

    private final List<Club> clubRank = new LinkedList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAllClubs(final List<Club> clubs) {
        this.clubRank.addAll(clubs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateClubRank() {
        clubRank.sort(
            Comparator.comparingInt(Club::getPoints)
                  .thenComparingInt(Club::getNetDiff)
                  .thenComparing(Club::getName)
                  .reversed());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Club> showPosition() {
        return Collections.unmodifiableList(this.clubRank);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.clubRank.clear();
    }

}
