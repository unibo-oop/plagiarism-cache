package game.score;

import game.logics.GameMode;
import java.io.Serializable;

/**
 * This class describe an HighScore.
 */
public class HighScore implements Serializable {

  private static final long serialVersionUID = -8908872185743083323L;
  private static final int MAX_STRING = 10;
  private String name;
  private final int score;
  private final GameMode mode;
  private final int gridSize;

  /**
   * Constructor.
   */
  public HighScore(final String name, final int score, final GameMode gameMode, final int size) {
    if (name.length() > MAX_STRING) {
      this.name = name.substring(0, MAX_STRING - 1);
    } else {
      this.name = name;
    }
    this.score = score;
    this.gridSize = size;
    this.mode = gameMode;
  }

  /**
   * This method return the name of a player.
   * 
   * @return string
   */
  public String getName() {
    return this.name;
  }

  /**
   * This method return the score.
   * 
   * @return score
   */
  public int getScore() {
    return this.score;
  }

  /**
   * This method return the mode.
   * 
   * @return mode
   */
  public GameMode getMode() {
    return this.mode;
  }

  /**
   * This method return the size of the grid.
   * 
   * @return size
   */
  public int getSize() {
    return this.gridSize;
  }
}
