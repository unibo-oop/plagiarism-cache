package view.windows;

import game.score.HighScore;
import game.score.RankingManager;
import game.score.RankingManagerImpl;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import view.utils.FXMLPath;

/**
 * This class manage the ranking view.
 */
public class RankingView extends Pane {

  private static final int SCREEN_WIDTH = 900;
  private static final int SCREEN_HEIGTH = 700;
  private static final int CELL_SIZE = 200;
  private static final int GRID_X = 650;
  private static final int GRID_Y = 500;
  private static final int N_OF_COLS = 5;
  private static final int N_OF_ROWS = 11;
  private final Rectangle background;
  private final RankingManager rankingM;
  private final Button turnBack;
  private final Label title;
  private GridPane gridPane;
  private final List<HighScore> score;

  /**
   * Constructor.
   */
  public RankingView() {
    this.rankingM = new RankingManagerImpl();
    this.background = new Rectangle();
    this.turnBack = new Button();
    this.title = new Label();
    this.gridPane = new GridPane();
    this.score = rankingM.getHighscore();
    setBackgroundColor();
    setTitle();
    createGrid();
    setButtons();
  }

  /**
   * This method set title of the ranking view.
   */
  private void setTitle() {
    this.title.setText("RANKING");
    this.title.setAlignment(Pos.CENTER);
    this.title.setPrefSize(500, 75);
    this.title.getStyleClass().add("game-title");
    this.title.setLayoutX(SCREEN_WIDTH / 2 - 250);
    this.getChildren().add(this.title);
  }

  /**
   * This method create the grid of the ranking.
   */
  private void createGrid() {
    this.gridPane = new GridPane();
    this.gridPane.setPrefSize(GRID_X, GRID_Y);
    this.gridPane.setLayoutX(SCREEN_WIDTH / 2 - GRID_X / 2);
    this.gridPane.setLayoutY(150);
    this.gridPane.setAlignment(Pos.CENTER);
    for (int rows = 0; rows < N_OF_ROWS; rows++) {
      for (int cols = 0; cols < N_OF_COLS; cols++) {
        this.gridPane.add(createCell(rows, cols, this.score), cols, rows);
      }
    }
    this.getChildren().add(gridPane);
  }

  /**
   * This method create the cell of the grid.
   * @return label
   */
  private Label createCell(final int rows, final int cols, final List<HighScore> score) {
    final Label cell = new Label();
    int r = rows;
    cell.setAlignment(Pos.CENTER);
    cell.setPrefSize(CELL_SIZE, CELL_SIZE);
    switch (r) {
      case 0:
        cell.getStyleClass().add("ranking-tables");
        switch (cols) {
          case 0:
            cell.setText("N.");
            break;
          case 1:
            cell.setText("SCORE");
            break;
          case 2:
            cell.setText("NAME");
            break;
          case 3:
            cell.setText("MODE");
            break;
          case 4:
            cell.setText("GRID SIZE");
            break;
          default:
            break;
        }
        break;
      default:
        cell.getStyleClass().add("ranking-cell");
        r = r - 1;
        switch (cols) {
          case 0:
            cell.setText(Integer.toString(r + 1));
            break;
          case 1:
            cell.setText(Integer.toString(score.get(r).getScore()));
            break;
          case 2:
            cell.setText(score.get(r).getName());
            break;
          case 3:
            cell.setText(score.get(r).getMode().toString());
            break;
          case 4:
            cell.setText(Integer.toString(score.get(r).getSize()));
            break;
          default:
            break;
        }
        break;
    }
    return cell;
  }

  /**
   * This method set the background of the window.
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
    this.turnBack.setOnAction(e -> pressTB());
    this.getChildren().add(this.turnBack);
  }

  /**
   * This method allow to return to the main menu window.
   */
  private void pressTB() {
    this.getChildren().clear();
    Parent root;
    try {
      root = FXMLLoader.load(getClass().getResource(FXMLPath.MAINMENU.getPath()));
      this.getChildren().add(root);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
