package game.logics;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class is the "mind" of the game, it deals with logic, how to move the
 * tiles, updating the score.
 *
 */
public class GameManagerImpl implements GameManager {

  private static final int MAX_VALUE_TO_WIN = 2048;

  /**
   * This field is the grid of the game.
   */
  protected Map<Pair<Integer, Integer>, Tile> grid;

  /**
   * This field count the score of the game.
   */
  protected int myScore;

  /**
   * This contains the free positions of the game.
   */
  protected Set<Pair<Integer, Integer>> positions = new HashSet<>();

  private final int gridSize;
  private List<Integer> traversalX;
  private List<Integer> traversalY;
  private GameManager.GameStatus status;
  private int mergePossible;

  /**
   * Constructor.
   */
  public GameManagerImpl(final int gridSize) {
    this.status = GameManager.GameStatus.PLAYING;
    this.gridSize = gridSize;
  }

  @Override
  public void initGrid() {
    this.grid = new HashMap<>();
    this.traversalX = IntStream.range(0, gridSize).boxed().collect(Collectors.toList());
    this.traversalY = IntStream.range(0, gridSize).boxed().collect(Collectors.toList());
    grid.clear();
    positions.clear();
    traversalX.forEach(x -> {
      traversalY.forEach(y -> {
        positions.add(new Pair<>(x, y));
        grid.put(new Pair<>(x, y), null);
      });
    });
  }

  @Override
  public void startGame() {
    final Pair<Integer, Integer> position0 = findRandomFreePosition();
    final Tile tile0;
    final Tile tile1;
    Pair<Integer, Integer> position1 = findRandomFreePosition();
    while (position0.equals(position1)) {
      position1 = findRandomFreePosition();
    }
    tile0 = Tile.newRandomTile();
    tile1 = Tile.newRandomTile();

    tile0.setPosition(position0);
    tile1.setPosition(position1);
    Arrays.asList(tile0, tile1).stream()
    .filter(Objects::nonNull).forEach(t -> grid.put(t.getPosition(), t));
  }

  @Override
  public void addNewTile(final Pair<Integer, Integer> randomPosition) {
    final var tile = Tile.newRandomTile();
    tile.setPosition(randomPosition);
    grid.put(randomPosition, tile);
  }

  /**
   * Sort traverse if Direction is RIGHT or DOWN.
   */
  private void sortGrid(final Direction direction) {
    Collections.sort(traversalX, direction.equals(Direction.RIGHT) 
        ? Collections.reverseOrder() : Integer::compareTo);
    Collections.sort(traversalY, direction.equals(Direction.DOWN) 
        ? Collections.reverseOrder() : Integer::compareTo);
  }

  @Override
  public void moveTiles(final Direction direction) {
    myScore = 0;
    sortGrid(direction);
    traversalX.forEach(x -> {
      traversalY.forEach(y -> {
        final Pair<Integer, Integer> thisPos = new Pair<>(x, y);
        final Optional<Tile> opTile = optionalTile(thisPos);
        final Pair<Integer, Integer> farthest = findFarthestPosition(thisPos, direction);
        final Pair<Integer, Integer> nextPosition = farthest.offset(direction);
        final Optional<Tile> opNext = optionalTile(nextPosition);
        if (opNext.isPresent() && opTile.isPresent() 
            && isMergeable(thisPos, nextPosition) && !(opNext.get().isMerged())) {
          final Tile tile = merge(opTile.get().getValue(), 
              optionalTile(thisPos).get().getValue(), nextPosition);
          grid.replace(thisPos, null);
          myScore += tile.getValue();
          if (tile.getValue() == MAX_VALUE_TO_WIN) {
            this.status = GameManager.GameStatus.WIN;
          }
        } else if (opTile.isPresent() && !farthest.equals(thisPos)) {
          final Tile tile = opTile.get();
          grid.put(farthest, tile);
          grid.replace(thisPos, null);
          tile.setPosition(farthest);
        }
      });
    });
    grid.values().stream().filter(Objects::nonNull).forEach(t -> t.setMerged(false));
  }

  @Override
  public boolean lostControl() {
    if (findRandomFreePosition() == null) {
      if (!isDirectionMergePossible(Direction.LEFT) && !isDirectionMergePossible(Direction.RIGHT)
          && !isDirectionMergePossible(Direction.UP) && !isDirectionMergePossible(Direction.DOWN)) {
        this.status = GameManager.GameStatus.LOST;
        return true;
      }
    }
    return false;
  }

  /**
   * This method return true if there is merging possible.
   */
  private boolean isDirectionMergePossible(final Direction direction) {
    mergePossible = 0;
    sortGrid(direction);
    traversalX.forEach(x -> {
      traversalY.forEach(y -> {
        final Pair<Integer, Integer> thisPos = new Pair<>(x, y);
        final Optional<Tile> opTile = optionalTile(thisPos);
        final Pair<Integer, Integer> nextPosition = thisPos.offset(direction);
        if (optionalTile(nextPosition).isPresent() 
            && opTile.isPresent() && isMergeable(thisPos, nextPosition)) {
          mergePossible++;
        }
      });
    });
    return mergePossible != 0;
  }

  /**
   * This method return new tile merged with another.
   * @return tile
   */
  private Tile merge(final int value1, final int value2, 
      final Pair<Integer, Integer> nextPosition) {
    final Tile tile = new Tile(value1 + value2);
    tile.setMerged(true);
    grid.put(nextPosition, tile);
    tile.setPosition(nextPosition);
    return tile;
  }

  @Override
  public boolean isMergeable(final Pair<Integer, Integer> thisPos, 
      final Pair<Integer, Integer> nextPosition) {
    final Tile first = optionalTile(thisPos).get();
    final Tile second = optionalTile(nextPosition).get();
    return first.getValue() == second.getValue();
  }

  /**
   * This method return if a position is valid in the grid.
   */
  private boolean isValidPosition(final Pair<Integer, Integer> pos) {
    return pos.getX() >= 0 && pos.getX() < gridSize && pos.getY() >= 0 && pos.getY() < gridSize;
  }

  private Optional<Tile> optionalTile(final Pair<Integer, Integer> pos) {
    return Optional.ofNullable(grid.get(pos));
  }

  @Override
  public Pair<Integer, Integer> findRandomFreePosition() {
    final Random rnd;
    rnd = new Random();
    final List<Pair<Integer, Integer>> freePositions = 
        positions.stream().filter(l -> grid.get(l) == null)
        .collect(Collectors.toList());
    if (freePositions.isEmpty()) {
      return null;
    }
    return freePositions.get(rnd.nextInt(freePositions.size()));
  }

  /**
   * This method return the farthest location from position in that direction.
   * @return position
   */
  private Pair<Integer, Integer> findFarthestPosition(final Pair<Integer, Integer> pos, 
      final Direction direction) {
    Pair<Integer, Integer> farthest;
    Pair<Integer, Integer> position = pos;
    do {
      farthest = position;
      position = farthest.offset(direction);
    } while (isValidPosition(position) && !optionalTile(position).isPresent());

    return farthest;
  }

  @Override
  public int getScore() {
    return this.myScore;
  }

  @Override
  public Map<Pair<Integer, Integer>, Tile> getGrid() {
    return this.grid;
  }

  @Override
  public GameStatus getGameStatus() {
    return this.status;
  }

  @Override
  public void quitGame() {
    System.exit(0);
  }

  @Override
  public boolean winControl() {
    return status == GameManager.GameStatus.WIN;
  }
}
