package it.unibo.monoopoly.model.gameboard.api;

/**
 * Represents a company cell.
 */
public interface Company extends Buyable {

    /**
     * Calculate the amount to pay according to the roll of the dices.
     */
    void rollAndCalculate();

}
