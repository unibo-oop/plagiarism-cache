package oop.lit.model;

import oop.lit.view.ViewRequests;

/**
 * A class used to build a game.
 */
public interface GameFactory {
    /**
     * Builds a game.
     * @param view
     *      the view that will be used.
     * @return
     *      the GameModel.
     */
    GameModel getGame(ViewRequests view);

    /**
     * @return
     *      the game name.
     */
    String getName();
}
