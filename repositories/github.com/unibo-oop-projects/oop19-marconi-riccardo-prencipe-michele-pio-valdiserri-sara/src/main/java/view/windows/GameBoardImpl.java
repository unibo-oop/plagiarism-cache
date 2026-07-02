package view.windows;

import controller.game.GameControllerImpl;
import game.logics.AlternativeTile;
import game.logics.GameMode;
import game.logics.Pair;
import game.logics.Tile;
import java.util.Map;
import java.util.Objects;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

/**
 * This class manage the view of the game.
 */
public class GameBoardImpl extends Pane implements GameBoard {

  private static final int SCREEN_WIDTH = 900;
  private static final int SCREEN_HEIGTH = 700;
  private static final int SIZE_4 = 4;
  private static final int SIZE_5 = 5;
  private static final int SIZE_6 = 6;
  private static final int SIZE_7 = 7;
  private final Label score;
  private final Label points;
  private final Label title;
  private final Label movePoints;
  private final Rectangle background;
  private Group gridGroup;
  private VBox verticalBox;
  private HBox horizontalBottom;
  private HBox scoreGroup;
  private final Button turnBack;
  private final Button reset;
  private AlternativeTileView alternativeTileV;
  private TileView tileV;
  private final int gridSize;
  private final GameControllerImpl gameController;
  private final GameMode mode;
  private int cellSize;
  private int width;
  private int squareSize;

  /**
   * Constructor.
   */
  public GameBoardImpl(final int size, final GameMode gameMode,
      final GameControllerImpl gameController) {

    gridSize = size;
    this.mode = gameMode;
    this.gameController = gameController;
    this.background = new Rectangle();
    this.score = new Label();
    this.points = new Label();
    this.title = new Label();
    this.movePoints = new Label();
    this.turnBack = new Button();
    this.reset = new Button();
    switch (gridSize) {
      case SIZE_4:
        this.cellSize = 105;
        this.squareSize = 90;
        this.width = 8;
        break;
      case SIZE_5:
        cellSize = 95;
        this.squareSize = 85;
        this.width = 5;
        break;
      case SIZE_6:
        cellSize = 80;
        this.squareSize = 73;
        this.width = 4;
        break;
      case SIZE_7:
        cellSize = 70;
        this.squareSize = 65;
        this.width = 3;
        break;
      default:
        break;
    }
    setBackgroundColor();
    setTitle();
    setScore();
    setMovePoints();
    createGrid();
    setButtons();
  }

  /**
   * This method create the background of the window game.
   */
  private void setBackgroundColor() {
    this.background.setLayoutX(0);
    this.background.setWidth(SCREEN_WIDTH);
    this.background.setHeight(SCREEN_HEIGTH);
    this.background.setFill(Color.ORANGE);
    this.background.setStroke(Paint.valueOf("#793101"));
    this.getChildren().add(this.background);
  }

  /**
   * This method set the buttons of the view.
   */
  private void setButtons() {
    this.turnBack.setPrefSize(80, 80);
    this.turnBack.setLayoutX(800);
    this.turnBack.setLayoutY(590);
    this.turnBack.setText("TURN\nBACK");
    this.turnBack.getStyleClass().add("turnback-button");
    this.turnBack.setOnAction(e -> gameController.pressTB());
    this.getChildren().add(this.turnBack);

    this.reset.setPrefSize(80, 80);
    this.reset.setLayoutX(800);
    this.reset.setLayoutY(490);
    this.reset.setText("RESET");
    this.reset.getStyleClass().add("reset-button");
    this.reset.setOnAction(e -> gameController.pressRT(e));
    this.getChildren().add(this.reset);
  }

  /**
   * This method set the move points.
   */
  private void setMovePoints() {
    this.movePoints.getStyleClass().add("game-points");
    this.movePoints.setLayoutY(94);
    this.movePoints.setAlignment(Pos.CENTER_RIGHT);
    this.movePoints.setPrefSize(100, 70);
    this.movePoints.setLayoutX(SCREEN_WIDTH / 2);
    this.getChildren().add(this.movePoints);
  }

  /**
   * This method set the score of the game.
   */
  private void setScore() {
    this.scoreGroup = new HBox();
    this.scoreGroup.setMinSize(240, 100);
    this.scoreGroup.setPrefSize(240, 100);
    this.scoreGroup.setMaxSize(240, 100);
    this.scoreGroup.setLayoutX(SCREEN_WIDTH / 2 - 145);
    this.scoreGroup.setLayoutY(100);
    this.score.setText("SCORE:");
    this.score.setAlignment(Pos.CENTER_RIGHT);
    this.score.setTextAlignment(TextAlignment.RIGHT);
    this.score.getStyleClass().add("game-score");
    this.score.setPrefSize(130, 50);
    this.getChildren().add(this.score);

    this.points.setText(Integer.toString(0));
    this.points.getStyleClass().add("game-points");
    this.points.setPrefSize(110, 50);
    this.points.setAlignment(Pos.CENTER_RIGHT);
    this.getChildren().add(this.points);
    this.scoreGroup.getChildren().add(score);
    this.scoreGroup.getChildren().add(points);
    this.getChildren().add(this.scoreGroup);
  }

  /**
   * This method set the title according to the game mode.
   */
  private void setTitle() {
    if (this.mode == GameMode.CLASSIC) {
      switch (gridSize) {
        case SIZE_4:
          this.title.setText("CLASSIC MODE");
          break;
        case SIZE_5:
          this.title.setText("5x5 MODE");
          break;
        case SIZE_6:
          this.title.setText("6x6 MODE");
          break;
        case SIZE_7:
          this.title.setText("7x7 MODE");
          break;
        default:
          this.title.setText("NO MODE");
          break;
      }
    } else {
      this.title.setText("ALTERNATIVE MODE");
    }
    this.title.setAlignment(Pos.CENTER);
    this.title.setPrefSize(700, 75);
    this.title.getStyleClass().add("game-title");
    this.title.setLayoutX(((SCREEN_WIDTH / 2) - 350));
    this.getChildren().add(this.title);
  }

  /**
   * This method return a cell to the grid.
   * @return
   */
  private Rectangle createCell(final int i, final int j) {
    final var cell = new Rectangle(i * cellSize, j * cellSize, cellSize, cellSize);
    switch (gridSize) {
      case SIZE_4:
        cell.getStyleClass().add("game-grid-cell4");
        break;
      case SIZE_5:
        cell.getStyleClass().add("game-grid-cell5");
        break;
      case SIZE_6:
        cell.getStyleClass().add("game-grid-cell6");
        break;
      case SIZE_7:
        cell.getStyleClass().add("game-grid-cell7");
        break;
      default:
        break;
    }
    return cell;
  }

  /**
   * This method create the grid.
   */
  private void createGrid() {
    this.gridGroup = new Group();
    this.verticalBox = new VBox();
    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        gridGroup.getChildren().add(createCell(j, i));
      }
    }
    this.gridGroup.setManaged(false);
    this.gridGroup.setLayoutX(5);
    this.gridGroup.setLayoutY(5);

    this.horizontalBottom = new HBox();
    this.horizontalBottom.setMinSize(450, 450);
    this.horizontalBottom.setPrefSize(450, 450);
    this.horizontalBottom.setMaxSize(450, 450);
    this.horizontalBottom.getChildren().add(gridGroup);

    this.verticalBox.getChildren().add(horizontalBottom);
    this.verticalBox.setLayoutX((SCREEN_WIDTH - cellSize * gridSize) / 2 - 6);

    switch (gridSize) {
      case SIZE_4:
        verticalBox.setLayoutY(236);
        break;
      case SIZE_5:
        verticalBox.setLayoutY(185);
        break;
      case SIZE_6:
        verticalBox.setLayoutY(180);
        break;
      case SIZE_7:
        verticalBox.setLayoutY(172);
        break;
      default:
        break;
    }
    this.getChildren().add(this.verticalBox);
  }

  /**
   * This method animate the score updating.
   */
  private void animateScore(final int score) {
    if (score == 0) {
      return;
    }
    final var timeline = new Timeline();
    this.movePoints.setText("+" + score);
    this.movePoints.setOpacity(1);

    final double posX = movePoints.localToScene(movePoints.getWidth() / 2d, 0).getX();
    this.movePoints.setTranslateX(0);
    this.movePoints.setTranslateX(movePoints.sceneToLocal(posX, 0)
        .getX() - movePoints.getWidth() / 2d);
    this.movePoints.setLayoutY(20);

    final var kvO = new KeyValue(movePoints.opacityProperty(), 0);
    final var kvY = new KeyValue(movePoints.layoutYProperty(), 100);

    final var animationDuration = Duration.millis(600);
    final KeyFrame kfO = new KeyFrame(animationDuration, kvO);
    final KeyFrame kfY = new KeyFrame(animationDuration, kvY);

    timeline.getKeyFrames().add(kfO);
    timeline.getKeyFrames().add(kfY);
    timeline.play();
    timeline.setOnFinished(e -> {
      this.movePoints.setText(Integer.toString(score));
    });
  }

  /**
   * This method add a tile in the grid.
   */
  private void addTile(final Tile tile) {
    final double layoutX = tile.getPosition().getX() * cellSize + width;
    final double layoutY = tile.getPosition().getY() * cellSize + width;
    this.tileV = new TileView(tile, squareSize, gridSize);
    this.tileV.setLayoutX(layoutX);
    this.tileV.setLayoutY(layoutY);
    this.gridGroup.getChildren().add(tileV);
  }

  /**
   * This method add an alternative tile in the grid.
   */
  private void addAlternativeTile(final AlternativeTile t) {
    final double layoutX = t.getPosition().getX() * cellSize + width;
    final double layoutY = t.getPosition().getY() * cellSize + width;
    this.alternativeTileV = new AlternativeTileView(t);
    this.alternativeTileV.setLayoutX(layoutX);
    this.alternativeTileV.setLayoutY(layoutY);
    this.gridGroup.getChildren().add(alternativeTileV);
  }

  @Override
  public void updateScore(final int score) {
    animateScore(score);
    final int myScore = Integer.parseInt(points.getText()) + score;
    this.points.setText(Integer.toString(myScore));
  }

  @Override
  public void updateGridClassic(final Map<Pair<Integer, Integer>, Tile> grid) {
    this.gridGroup.getChildren().clear();
    createGrid();
    grid.values().stream().filter(Objects::nonNull).forEach(t -> this.addTile(t));
  }

  @Override
  public void updateGridAlternative(final Map<Pair<Integer, Integer>, Tile> tiles,
      final Map<Pair<Integer, Integer>, AlternativeTile> alternativeTiles) {
    this.gridGroup.getChildren().clear();
    createGrid();
    alternativeTiles.values().stream().filter(Objects::nonNull)
    .forEach(t -> this.addAlternativeTile(t));
    tiles.values().stream().filter(Objects::nonNull).forEach(t -> this.addTile(t));
  }
}
