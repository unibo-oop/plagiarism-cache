package it.unibo.the100dayswar.model.bot.api;

import java.io.Serializable;

/**
 * Defines the strategy of a bot player which can 
 * calculate the best move to make.
 */
public interface BotStrategy extends Serializable {
    /**
     * Apply the strategy to the bot player.
     * 
     * @param botPlayer the bot to apply the strategy to
     */
    void apply(BotPlayer botPlayer);
}
