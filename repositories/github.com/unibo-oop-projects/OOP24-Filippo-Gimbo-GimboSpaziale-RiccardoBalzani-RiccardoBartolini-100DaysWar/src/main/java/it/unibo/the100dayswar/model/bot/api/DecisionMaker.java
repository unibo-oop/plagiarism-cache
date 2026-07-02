package it.unibo.the100dayswar.model.bot.api;

import java.io.Serializable;
import java.util.Optional;

import it.unibo.the100dayswar.model.bot.impl.ActionType;

/** 
 * A utility object that helps the evaluation of the best move 
 * in the strategy for the bot.
 */
public interface DecisionMaker extends Serializable {
    /**
     * Evaluates the possible moves.
     * 
     * @param botPlayer the bot player
     */
    void evaluateMoves(BotPlayer botPlayer);
    /**
     * Selects the best move based on the highest score.
     * This method should be called after the evaluateMoves method.
     * 
     * @return an optional of the best move type or empty if no move is performable
     */
    Optional<ActionType> getBestMoveType();
}
