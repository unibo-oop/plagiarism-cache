package it.dpg.maingame.model.character;

import it.dpg.maingame.model.grid.CellType;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Set;

/**
 * Interface representing a character on the game grid
 * @author Davide Picchiotti
 * */

public interface Character {

    /**
     * @return character numeric id
     * */
    int getId();

    /**
     * @return the name assigned to the character
     * */
    String getName();

    /**
     * set turn order for the character
     * @param turn integer representing turn order
     * */
    void setTurn(int turn);

    /**
     * @return an integer representing turn order for the character
     * */
    int getTurn();

    /**
     * set an absolute position for the character on the grid
     * @param coordinates Pair of coordinates to set
     * @see Pair
     * */
    void setPosition(final Pair<Integer, Integer> coordinates);

    /**
     * @return position of the character in the grid
     * @see Pair
     * */
    Pair<Integer, Integer> getPosition();

    /**
     * @return Set of positions on the grid adjacent to the character, excluding backwards
     * @see Set
     * @see Pair
     * */
    Set<Pair<Integer, Integer>> getAdjacentPositions();

    /**
     * @return the type of cell the player is on
     * @see CellType
     * */
    CellType getCellType();

    /**
     * if no fork is detected and there are any remaining moves, move the character one position forward on the grid
     * @return if the character have any remaining moves after the movement
     * */
    boolean stepForward();

    /**
     * move the character one position backwards, without counting remaining moves
     * */
    void stepBackward();

    /**
     * move the character in the given adjacent direction
     * @param coordinates the coordinates to move the character to
     * @return if the character have any remaining moves after the movement
     * @see Pair
     * */
    boolean stepInDirection(final Pair<Integer, Integer> coordinates);

    /**
     * set the dice type used for dice throws
     * @param dice the dice to set
     * @see Dice
     * */
    void setDice(final Dice dice);

    /**
     * @return  the dice type used for dice throws
     * */
    Dice getDice();

    /**
     * throw the dice, returning the number
     * @return the number obtained from the throw
     * */
    int throwDice();

    /**
     * set the score obtained in the last played minigame
     * @param score the score to set
     * */
    void setMinigameScore(final int score);

    /**
     * @return the score obtained in the last played minigame
     * */
    int getMinigameScore();
}
