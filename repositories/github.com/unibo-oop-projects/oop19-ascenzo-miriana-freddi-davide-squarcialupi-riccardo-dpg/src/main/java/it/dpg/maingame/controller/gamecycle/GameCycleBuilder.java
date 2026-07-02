package it.dpg.maingame.controller.gamecycle;

import it.dpg.maingame.model.character.Dice;
import it.dpg.maingame.model.character.Difficulty;

public interface GameCycleBuilder {

    /**
     * set the number of turns for the gamecycle
     */
    GameCycleBuilder setNTurns(final int nTurns);

    /**
     * set the default dice, used in the first turn or if there aren't enough reward dices for everyone
     */
    GameCycleBuilder setDefaultDice(final Dice defaultDice);

    /**
     * adds a reward dice, the order of insertion determines the value of the reward,
     * the first dice is assigned to highest score, the second to the second highest ecc...
     */
    GameCycleBuilder addRewardDice(final Dice rewardDice);

    /**
     * adds a human player
     *
     * @param name player's name
     */
    GameCycleBuilder addHumanPlayer(final String name);

    /**
     * adds a cpu
     *
     * @param name       cpu name
     * @param difficulty cpu's difficulty, determines the minigame scores
     */
    GameCycleBuilder addCpu(final String name, final Difficulty difficulty);

    /**
     * builds the gamecycle
     */
    GameCycle build();
}
