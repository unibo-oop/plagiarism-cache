package it.dpg.maingame.model.character;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Interface representing a cpu that controls a character on the grid
 * @see Character
 * @author Davide Picchiotti
 * */

public interface Cpu {
    /**
     * @return the character controlled by this cpu
     * @see Character
     * */
    Character getControlledCharacter();

    /**
     * @return the difficulty of this cpu
     * @see Difficulty
     * */
    Difficulty getDifficulty();

    /**
     * @return  a random direction adjacent to the character controlled by this cpu
     * @see Pair
     * */
    Pair<Integer, Integer> getRandomDirection();
}
