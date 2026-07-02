package view.windows;

import game.logics.AlternativeTile;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

/**
 * This class set the view of the alternative tile.
 */
public class AlternativeTileView extends Label {

  /**
   * Constructor.
   */
  public AlternativeTileView(final AlternativeTile at) {
    this.setMinSize(90, 90);
    this.setMaxSize(90, 90);
    this.setPrefSize(90, 90);
    this.setAlignment(Pos.CENTER);
    if (at.getValue() == 0) {
      this.getStyleClass().addAll("game-label", "game-blacktile");
      this.setText("x0");
    } else {
      this.getStyleClass().addAll("game-label", "game-greentile");
      this.setText("x2");
    }
  }
}
