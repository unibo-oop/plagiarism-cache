package it.unibo.the100dayswar.model.bot.impl;

import java.util.Optional;

import it.unibo.the100dayswar.model.bot.api.BotPlayer;
import it.unibo.the100dayswar.model.bot.api.BotStrategy;
import it.unibo.the100dayswar.model.bot.api.DecisionMaker;

/**
 * An implementation of the BotStrategy interface that represents a simple
 * strategy
 * that evaluate the best move assigning a score to each possible move and
 * after this it will choose the move with the highest score.
 */
public class SimpleBotStrategy implements BotStrategy {
    private static final long serialVersionUID = 1L;

    private final DecisionMaker decisionMaker;
    /**
     * Constructor of the class.
     */
    public SimpleBotStrategy() {
        this.decisionMaker = new DecisionMakerImpl();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void apply(final BotPlayer botPlayer) {
        decisionMaker.evaluateMoves(botPlayer);
        final Optional<ActionType> bestMove = decisionMaker.getBestMoveType();
        if (bestMove.isPresent()) {
            bestMove.get().execute(botPlayer);
        }
    }
}
