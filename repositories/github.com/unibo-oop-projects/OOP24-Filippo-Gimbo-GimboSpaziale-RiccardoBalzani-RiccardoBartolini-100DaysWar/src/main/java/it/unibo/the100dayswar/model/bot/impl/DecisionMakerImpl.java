package it.unibo.the100dayswar.model.bot.impl;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import it.unibo.the100dayswar.commons.utilities.impl.Score;
import it.unibo.the100dayswar.model.bot.api.BotPlayer;
import it.unibo.the100dayswar.model.bot.api.DecisionMaker;

/**
 * Implementation of the decision maker.
 */
public class DecisionMakerImpl implements DecisionMaker {
    private static final long serialVersionUID = 1L;
    private final Map<ActionType, Score> scoreMove;

    /**
     * Constructor of the class.
     */
    public DecisionMakerImpl() {
        this.scoreMove = new EnumMap<>(ActionType.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void evaluateMoves(final BotPlayer botPlayer) {
        scoreMove.clear();
        for (final ActionType actionType : ActionType.values()) {
            scoreMove.put(actionType, actionType.evaluate(botPlayer));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ActionType> getBestMoveType() {
        return scoreMove.entrySet().stream()
                .filter(entry -> entry.getValue().getValue() >= 0)
                .max(Comparator.<Map.Entry<ActionType, Score>>comparingInt(e -> e.getValue().getValue())
                        .thenComparing(Map.Entry::getKey))
                .map(Map.Entry::getKey);
    }
}
