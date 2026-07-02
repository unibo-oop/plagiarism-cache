package game.logics;

import java.util.Map;

/**
 * This is the interface for gameManager.
 *
 */
public interface GameManager {

  /**
   * Enumeration for the game status.
   *
   */
  enum GameStatus {
    EXIT, PLAYING, WIN, LOST,
  }

  /**
   * This method initialize a new grid with its positions.
   */
  void initGrid();

  /**
   * This method start a new game with two new tile in two random positions.
   */
  void startGame();

  /**
   * This method deals with add a new tile in random position.
   */
  void addNewTile(Pair<Integer, Integer> randomPosition);

  /**
   * This method moves the tiles from a starting position towards its farthest
   * square in the direction with the necessary checks.
   */
  void moveTiles(Direction direction);

  /**
   * This method the score update.
   * 
   * @return score
   */
  int getScore();

  /**
   * This method grid with updated positions.
   * 
   * @return grid
   */
  Map<Pair<Integer, Integer>, Tile> getGrid();

  /**
   * This method return the game status.
   * 
   * @return gameStatus
   */
  GameStatus getGameStatus();

  /**
   * This method return a new random free position.
   * 
   * @return position
   */
  Pair<Integer, Integer> findRandomFreePosition();

  /**
   * This method allow to quit the game.
   */
  void quitGame();

  /**
   * This method if a tile is merge able.
   * 
   * @return boolean
   */
  boolean isMergeable(Pair<Integer, Integer> thisPos, Pair<Integer, Integer> nextPosition);

  /**
   * This method return true if you have won.
   * 
   * @return boolean
   */
  boolean winControl();

  /**
   * This method return true if you have lost.
   * 
   * @return boolean
   */
  boolean lostControl();

}
