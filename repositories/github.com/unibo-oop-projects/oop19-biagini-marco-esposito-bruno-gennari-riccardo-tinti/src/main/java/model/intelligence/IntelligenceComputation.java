package model.intelligence;

import java.util.List;

import model.match.PlaygroundBattle;
import model.util.Pair;

/**
 * Represents the implementation of the {@link ArtificialIntelligence} abstract object.
 *
 */
public interface IntelligenceComputation {

    /**
     * Method that positions ships on a playground.
     * 
     * @return
     *      Return an initialized playground.
     */
    PlaygroundBattle initShips();

    /**
     * Method that calculates the new coordinate in which set the attack.
     * 
     * @param attackGrid
     *          a list of coordinates of the points already computed.
     * @return
     *      Returns the new calculated point.
     */
    Pair<Integer, Integer> setNextToHit(List<Pair<Integer, Integer>> attackGrid);

}
