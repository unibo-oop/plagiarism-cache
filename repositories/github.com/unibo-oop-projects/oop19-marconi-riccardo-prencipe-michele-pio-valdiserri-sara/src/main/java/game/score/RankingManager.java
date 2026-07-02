package game.score;

import game.logics.GameMode;
import java.util.List;

/**
 * This is interface to memorize data in a file.
 *
 */
public interface RankingManager {

  /**
   * This method add a score.
   */
  void addScore(String name, int score, GameMode gameMode, int size);

  /**
   * This method return the name at position i.
   * 
   * @return name
   */
  String getNameAtPosition(int i);

  /**
   * This method return the score at position i.
   * 
   * @return score
   */
  int getScoreAtPosition(int i);

  /**
   * Clear the file.
   */
  void clear();

  /**
   * This method return true if the file is present.
   * 
   * @return boolean
   */
  boolean isPresent();

  /**
   * This method return the array list of all scores.
   * 
   * @return
   */
  List<HighScore> getHighscore();
}
