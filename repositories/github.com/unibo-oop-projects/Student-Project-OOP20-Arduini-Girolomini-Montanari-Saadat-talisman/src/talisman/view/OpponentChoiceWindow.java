package talisman.view;

import java.util.List;

import talisman.EventEndedListener;

/**
 * Interface of the view used to select an opponent to fight.
 * 
 * @author Alice Girolomini
 *
 */
public interface OpponentChoiceWindow {
    /**
     * Shows the window used to select an opponent to fight.
     * 
     * @param players             - the indexes of the players in the same cell
     * @param battleEndedListener - a listener to call for when the battle ends.
     */
    static void show(final List<Integer> players, final EventEndedListener battleEndedListener) {
        new OpponentChoiceWindowImpl(players, battleEndedListener);
    }
}
