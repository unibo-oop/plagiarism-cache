package game.logics;

public interface TileI {

  /**
   * This method return the value of the tile.
   * 
   * @return value
   */
  int getValue();

  /**
   * This method return the position of the Tile.
   * 
   * @return Position
   */
  Pair<Integer, Integer> getPosition();

  /**
   * This method sets the position of the tile inside the grid.
   */
  void setPosition(Pair<Integer, Integer> p);

  /**
   * This method set the merged status of the tile.
   */
  void setMerged(boolean b);

  /**
   * This method return the status of the merge, if is merged or not.
   * 
   * @return boolean
   */
  boolean isMerged();

}
