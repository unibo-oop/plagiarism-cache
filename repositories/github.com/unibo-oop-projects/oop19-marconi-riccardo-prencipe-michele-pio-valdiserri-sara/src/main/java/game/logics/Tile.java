package game.logics;

/**
 * This class describe how the tile is made.
 */

public class Tile implements TileI {

  private static final int TILE2 = 2;
  private static final int TILE4 = 4;
  private static final double LIMIT = 0.3;
  private final int value;
  private Pair<Integer, Integer> position;
  private boolean merged;

  /**
   * Constructor.
   */
  public Tile(final int value) {
    this.position = null;
    this.value = value;
    this.merged = false;
  }

  /**
   * This method return a Tile with value 0, this tile is empty.
   * 
   * @return Tile
   */
  public static Tile empty() {
    return new Tile(0);
  }

  /**
   * This method create a new Tile, with the lowest value of the powers of 2.
   * The value of the new tile is chosen randomly between 2 or 4.
   * 
   * @return Tile
   * 
   */
  public static Tile newRandomTile() {
    return new Tile(Math.random() < LIMIT ? TILE2 : TILE4);
  }

  @Override
  public int getValue() {
    return value;
  }

  @Override
  public Pair<Integer, Integer> getPosition() {
    return position;
  }

  @Override
  public void setPosition(final Pair<Integer, Integer> p) {
    this.position = p;
  }

  @Override
  public void setMerged(final boolean b) {
    merged = b;
  }

  @Override
  public boolean isMerged() {
    return merged;
  }
}
