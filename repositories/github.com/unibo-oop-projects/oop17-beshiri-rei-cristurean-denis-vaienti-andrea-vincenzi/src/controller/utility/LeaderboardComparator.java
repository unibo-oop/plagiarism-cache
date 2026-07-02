package controller.utility;

import java.util.Comparator;

/**
 * Class to sort a list of Score object.
 * @param <E> Score object.
 */
public class LeaderboardComparator<E extends Score> implements Comparator<Score> {

    /**
     * To compare two score object.
     */
    @Override
    public int compare(final Score s1, final Score s2) {
        return s2.compareTo(s1);
    }
}
