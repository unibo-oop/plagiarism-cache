package it.dpg.maingame.controller.grid;

import org.apache.commons.lang3.tuple.Pair;

public interface GridObserver {

    /**
     * choosen Path handler
     */
    void choosePathHandler(Pair<Integer, Integer> path);

    /**
     * dice throw handler
     */
    void throwDiceHandler();

    /**
     * Enter Key Press Handler
     */
    void KeyPressHandler();

}
