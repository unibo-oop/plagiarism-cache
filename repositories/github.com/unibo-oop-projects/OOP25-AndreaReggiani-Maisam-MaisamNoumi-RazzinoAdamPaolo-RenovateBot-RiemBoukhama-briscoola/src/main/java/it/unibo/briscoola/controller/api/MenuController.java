package it.unibo.briscoola.controller.api;

import it.unibo.briscoola.controller.impl.utils.Pair;
import it.unibo.briscoola.model.api.attributes.Difficulty;
import it.unibo.briscoola.view.api.View;

import java.util.List;
import it.unibo.briscoola.model.api.leaderboard.Leaderboard;

/**
 * Manages the initial menu and setup of the Briscola game.
 *
 * @author Andrea Reggiani
 */
public interface MenuController {

    /**
     * Officially start game by configuring the model and view
     * based on the number of players selected.
     * 
     * @param namePlayer the name of the player
     * @param difficulty the selected difficulty level
     * @param view the view instance to interact with
     */
    void startGame(String namePlayer, Difficulty difficulty, View view);

    /**
     * Method that retrieves the leaderboard from the {@link Leaderboard} and
     * manipulates it to return a {@link List} of {@link Pair} of {@link String}.
     *
     * @return a {@link List} of pairs of strings matching each player with the highscore
     */
    List<Pair<String, String>> getLeaderboardData();
}
