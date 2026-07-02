package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import game.logics.Direction;
import game.logics.GameManagerImpl;
import game.logics.Pair;
import game.logics.Tile;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * This type of class is dedicated to verifying the correct functioning of the
 * relative logical class.
 *
 */
public class GameManagerTest {

  private Map<Pair<Integer, Integer>, Tile> grid = new HashMap<>();
  private GameManagerImpl gm = new GameManagerImpl(4);
  private static final int SCORE_TO_WIN = 2048;

  /**
   * This method check the initialize grid.
   */
  @Test
  public void testInitializeBoardProducesTwoTiles() {

    gm.initGrid();
    grid = gm.getGrid();
    gm.startGame();
    int numOfTilesFound = 0;
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        if (grid.get(new Pair<>(j, i)) != null) {
          numOfTilesFound++;
        }
      }
    }
    assertEquals(2, numOfTilesFound);
  }

  /**
   * This method test the move of the tile.
   */
  @Test
  public void testMove() {
    grid.clear();
    gm.initGrid();
    gm.startGame();
    gm.moveTiles(Direction.UP);
    gm.moveTiles(Direction.LEFT);
    grid = gm.getGrid();
    assertTrue(grid.get(new Pair<>(0, 0)) != null);
  }

  /**
   * This method test the score.
   */
  @Test
  public void testScore() {
    grid = gm.getGrid();
    gm.initGrid();
    final int score = gm.getScore();
    assertEquals(0, score);
  }

  /**
   * This method test the merge between two tiles into a new tile with the
   * following power of two.
   */
  @Test
  public void testMerge() {
    grid.clear();
    gm.initGrid();
    grid = gm.getGrid();
    final var tile1 = new Tile(2);
    tile1.setPosition(new Pair<>(0, 1));
    grid.put(tile1.getPosition(), tile1);
    final Tile tile = new Tile(2);
    tile.setPosition(new Pair<>(0, 0));
    grid.put(tile.getPosition(), tile);
    assertTrue(gm.isMergeable(tile1.getPosition(), tile.getPosition()));
  }

  /**
   * This method is for test the case if the user win the game.
   */
  @Test
  public void testWin() {
    grid.clear();
    gm.initGrid();
    grid = gm.getGrid();
    final var tile1 = new Tile(1024);
    tile1.setPosition(new Pair<>(0, 0));
    grid.put(tile1.getPosition(), tile1);
    final var tile2 = new Tile(1024);
    tile2.setPosition(new Pair<>(0, 1));
    grid.put(tile2.getPosition(), tile2);
    gm.moveTiles(Direction.DOWN);
    assertTrue(gm.winControl());
  }

  /**
   * This method is for test the case if the user lost the game.
   */
  @Test
  public void testLost() {
    grid.clear();
    gm.initGrid();
    grid = gm.getGrid();
    int value = 2;
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        final var tile = new Tile(value);
        grid.put(new Pair<>(i, j), tile);
        tile.setPosition(new Pair<>(i, j));
        value = value * 2;
        if (value == SCORE_TO_WIN) {
          value = 2;
        }
      }
    }
    assertTrue(gm.lostControl());
  }
}
