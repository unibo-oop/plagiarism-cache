package model.intelligence;

import model.match.PlaygroundBattle;
import model.util.Pair;

/**
 * Represents the abstract model for all kind of artificial intelligences.
 * 
 */
public abstract class ArtificialIntelligence {

    private IntelligenceComputation intelligence;

    /**
     * Initializes a specific AI implementation.
     * 
     * @param intelligence
     *          the specific intelligence computation.
     */
    public ArtificialIntelligence(final IntelligenceComputation intelligence) {
        this.intelligence = intelligence;
    }

    protected final IntelligenceComputation getIntelligenceComputation() {
        return this.intelligence;
    }

    /**
     * Method that positions ships on a playground.
     * 
     * @return
     *      Returns a populated playground.
     */
    public abstract PlaygroundBattle initShipsOnGrid();

    /**
     * Method that calculates a coordinate in which set the attack to the opponent.
     * 
     * @return
     *      Returns a coordinate point in which set the attack.
     */
    public abstract Pair<Integer, Integer> setNextHitPoint();

}
