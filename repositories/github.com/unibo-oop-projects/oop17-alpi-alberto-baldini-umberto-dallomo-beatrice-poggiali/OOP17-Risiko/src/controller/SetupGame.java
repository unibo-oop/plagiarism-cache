package controller;

import java.util.Map;

import players.CircList;
import players.PlayerImpl;

/**
 *
 * this class build the main object of the application, the game itself.
 */
public final class SetupGame {

    private SetupGame() { }

    /**
     * @param players is the list of player that are starting a game.
     * istance and connect others functional component.
     */
    public static void buildGame(final Map<String, Integer> players) {
        CircList<PlayerImpl> lista = new CircList<PlayerImpl>();
        players.forEach((a, b) -> lista.add(new PlayerImpl(a, b)));
        Controller.getController(lista);
    }
}
