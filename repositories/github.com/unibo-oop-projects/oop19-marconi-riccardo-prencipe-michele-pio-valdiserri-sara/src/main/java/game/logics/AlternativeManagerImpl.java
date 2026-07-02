package game.logics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class manage the alternative game mode, extending the Game Manager
 * class.
 */
public class AlternativeManagerImpl extends GameManagerImpl {

  private Map<Pair<Integer, Integer>, AlternativeTile> alternativeTilesGrid;
  private List<Integer> traversalX;
  private List<Integer> traversalY;
  private final int size;
  private int value;

  /**
   * Constructor.
   */
  public AlternativeManagerImpl(final int gridSize) {
    super(gridSize);
    this.size = gridSize;
    initTilesGrid();
  }

  /**
   * This method initialize the alternative tile grid.
   */
  private void initTilesGrid() {
    this.alternativeTilesGrid = new HashMap<>();
    this.traversalX = IntStream.range(0, size).boxed().collect(Collectors.toList());
    this.traversalY = IntStream.range(0, size).boxed().collect(Collectors.toList());
    alternativeTilesGrid.clear();
    positions.clear();
    traversalX.forEach(x -> {
      traversalY.forEach(y -> {
        positions.add(new Pair<>(x, y));
        alternativeTilesGrid.put(new Pair<>(x, y), null);
      });
    });
  }

  /**
   * This method add an alternative tile in the grid.
   */
  public void addAlternativeTile(final int value, final Pair<Integer, Integer> randomPosition) {
    final AlternativeTile alternativeTile;
    alternativeTile = new AlternativeTile(value, randomPosition);
    alternativeTile.setPosition(randomPosition);
    alternativeTilesGrid.put(randomPosition, alternativeTile);
  }

  /**
   * This method control if a tile is in the same position of an alternative
   * tile. If a tile is on a alternative tile x0 it remove the tile else if is
   * on a x2 tile it create a new tile with the old value multiplied by two.
   */
  public void controlAlternativePos() {
    traversalX.forEach(x -> {
      traversalY.forEach(y -> {
        final var pos = new Pair<Integer, Integer>(x, y);
        if (this.grid.get(pos) != null && alternativeTilesGrid.get(pos) != null) {
          this.value = grid.get(pos).getValue() * alternativeTilesGrid.get(pos).getValue();
          if (this.value == 0) {
            grid.replace(pos, null);
          } else {
            final var tile = new Tile(this.value);
            tile.setPosition(pos);
            this.grid.replace(pos, tile);
            this.myScore = myScore + this.value;
          }
          alternativeTilesGrid.remove(pos);
        }
      });
    });
  }

  /**
   * This method return the alternative tile grid.
   * 
   * @return AlternativeGrid
   */
  public Map<Pair<Integer, Integer>, AlternativeTile> getAlternativeGrid() {
    return this.alternativeTilesGrid;
  }

}
