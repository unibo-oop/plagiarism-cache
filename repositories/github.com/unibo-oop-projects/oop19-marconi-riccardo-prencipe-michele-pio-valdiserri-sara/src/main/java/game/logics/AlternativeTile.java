package game.logics;

/**
 * This class describe the alternative tile of the game.
 */
public class AlternativeTile implements TileI {

  private final int value;
  private Pair<Integer, Integer> position;

  /**
   * Constructor.
   */
  public AlternativeTile(final int valueI, final Pair<Integer, Integer> positionI) {
    this.value = valueI;
    this.position = positionI;
  }

  @Override
  public int getValue() {
    return this.value;
  }

  @Override
  public Pair<Integer, Integer> getPosition() {
    return this.position;
  }

  @Override
  public void setPosition(final Pair<Integer, Integer> p) {
    this.position = p;
  }

  @Override
  public void setMerged(final boolean b) {
  }

  @Override
  public boolean isMerged() {
    return false;
  }

}
