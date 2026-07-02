package model.players.ranking;

import java.util.Comparator;

import model.players.Player;

/**
 * Class to compare the score values to create the ranking.
 *
 * Andrea Serafini.
 */
public class TurnComparator implements Comparator<Player> {

    /**
     *
     */
    @Override
    public int compare(final Player score1, final Player score2) {

        final int sc1 = score1.getTurns();
        final int sc2 = score2.getTurns();

        if (sc1 < sc2) {
            return -1;
        } else if (sc1 > sc2) {
            return +1;
        } else {
            return 0;
        }
    }

}
