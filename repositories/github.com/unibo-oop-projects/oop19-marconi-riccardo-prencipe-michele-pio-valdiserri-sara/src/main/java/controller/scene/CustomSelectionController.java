package controller.scene;

import game.logics.Game2048;
import game.logics.GameMode;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import view.utils.FXMLPath;

/**
 * This class manage the custom mode selection scene of the game.
 */
public class CustomSelectionController {

  private static final int BASE_SIZE = 4;
  private static final int SIZE_5 = 5;
  private static final int SIZE_6 = 6;
  private static final int SIZE_7 = 7;

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  /**
   * Field of the scene's panel.
   */
  @FXML
  private AnchorPane rootPane;

  /**
   * This method start a new classic game with a random grid size.
   */
  @FXML
  private void pressRandom(final ActionEvent event) {
    final Random r;
    r = new Random();
    final Button btn = (Button) event.getSource();
    btn.getScene().getWindow().hide();
    new Game2048().startGame(r.nextInt(4) + BASE_SIZE, GameMode.CLASSIC);
  }

  /**
   * This method start a new classic game with a 5x5 size grid.
   */
  @FXML
  private void press5X5(final ActionEvent event) {
    final Button btn = (Button) event.getSource();
    btn.getScene().getWindow().hide();
    new Game2048().startGame(SIZE_5, GameMode.CLASSIC);
  }

  /**
   * This method start a new classic game with a 6x6 size grid.
   */
  @FXML
  private void press6X6(final ActionEvent event) {
    final Button btn = (Button) event.getSource();
    btn.getScene().getWindow().hide();
    new Game2048().startGame(SIZE_6, GameMode.CLASSIC);
  }

  /**
   * This method start a new classic game with a 7x7 size grid.
   */
  @FXML
  private void press7X7(final ActionEvent event) {
    final Button btn = (Button) event.getSource();
    btn.getScene().getWindow().hide();
    new Game2048().startGame(SIZE_7, GameMode.CLASSIC);
  }

  /**
   * This method allows to turn to mode selection window.
   */
  @FXML
  private void pressTurnBackCustom(final ActionEvent event) throws IOException {
    this.rootPane.getChildren().clear();
    final Parent root = FXMLLoader.load(getClass().getResource(FXMLPath.MODE.getPath()));
    this.rootPane.getChildren().add(root);
  }
}
