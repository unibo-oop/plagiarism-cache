package view.windows;

import game.logics.Tile;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

/**
 * This class describe the view of a tile according to the grid size.
 */
public class TileView extends Label {

  /**
   * Constructor.
   */
  public TileView(final Tile tile, final int squareSize, final int gridSize) {
    this.setMinSize(squareSize, squareSize);
    this.setMaxSize(squareSize, squareSize);
    this.setPrefSize(squareSize, squareSize);
    this.setAlignment(Pos.CENTER);
    final int value = tile.getValue();
    this.setText(Integer.toString(value));
    this.getStyleClass().addAll("game-label", "game-tile-" + value + "-" + gridSize);
  }
}
