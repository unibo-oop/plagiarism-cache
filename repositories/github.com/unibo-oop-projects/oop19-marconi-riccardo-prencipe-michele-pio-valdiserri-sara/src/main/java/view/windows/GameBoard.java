package view.windows;

import game.logics.AlternativeTile;
import game.logics.Pair;
import game.logics.Tile;
import java.util.Map;

public interface GameBoard {

  /**
   * This method update the score of the game.
   */
  void updateScore(int score);

  /**
   * This method update the grid when the mode is set on classic.
   */
  void updateGridClassic(Map<Pair<Integer, Integer>, Tile> grid);

  /**
   * This method update the grid when the mode is set on alternative.
   */
  void updateGridAlternative(Map<Pair<Integer, Integer>, Tile> tiles,
      Map<Pair<Integer, Integer>, AlternativeTile> alternativeTiles);

}
