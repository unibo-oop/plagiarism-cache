package it.unibo.game.app.model.leaderb;

import it.unibo.game.Pair;
import java.util.List;
import java.util.Optional;

/**
 * interface of the class that saves informations of leaderboard in a file.
 */
public interface LeaderBoard {

  /**
   * method that let the user to save his points.
   * 
   * @param name     of player
   * @param passWord
   * @param points   totalize by the player
   * @param levelId  in which player totalize his points
   */
  void updatePoints(String name, String passWord, Integer points, Integer levelId);

  /**
   * method to get best five players.
   * 
   * @return an ordered list that contains name and points of best five players
   */
  List<Pair<String, Integer>> getBestFive();

  /**
   * method to get the points totaliza by a specific player.
   * 
   * @param usr  player
   * @param pass password of player
   * @return optional empty if user is absent otherwise an optional with his
   *         points
   */
  Optional<Integer> getPoints(String usr, String pass);
}
