package it.dpg.maingame.view.grid;

import it.dpg.maingame.model.character.Dice;
import it.dpg.maingame.view.View;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;
import java.util.Set;

public interface GridView extends View {
    /**
     * Sets the name of who's currently playing in the main text
     */
    void setCurrentPlayerName(String name);

    /**
     * Sets the remaining moves in a specific text bubble
     */
    void setRemainingMoves(int moves);

    /**
     * Changes the main Text in the game
     */
    void showText(String text);

    /**
     * removes main Text in the game
     */
    void removeText();

    /**
     * enables direction choice by enabling two buttons corresponding to the possible choices
     */
    void enableDirectionChoice(Set<Pair<Integer, Integer>> cells);

    /**
     * disables the direction choice buttons and removes them
     */
    void disableDirectionChoice();

    /**
     * Updates the Players positions in the Grid
     */
    void updatePlayers(Map<Integer, Pair<Integer,Integer>> players);

    /**
     * enables the dice button
     */
    void enableDiceThrow(Dice dice);

    /**
     * disables the dice button
     */
    void disableDiceThrow();
}
