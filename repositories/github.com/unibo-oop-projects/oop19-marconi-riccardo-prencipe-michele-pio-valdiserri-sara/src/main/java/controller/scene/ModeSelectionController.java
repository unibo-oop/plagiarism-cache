package controller.scene;

import game.logics.Game2048;
import game.logics.GameMode;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import view.utils.FXMLPath;

/**
 * This class manage the mode selection scene of the game.
 */
public class ModeSelectionController {

  /**
   * Fields to allow user to move window of the game.
   */
  @FXML
  private BorderPane rootPane;

  private static final int CLASSIC_SIZE = 4;

  /**
   * This method allows to turn back to main menu window.
   */
  @FXML
  private void pressTurnBackCM(final ActionEvent event) throws IOException {
    this.rootPane.getChildren().clear();
    Parent root = FXMLLoader.load(getClass().getResource(FXMLPath.MAINMENU.getPath()));
    this.rootPane.getChildren().add(root);
  }

  /**
   * This method start a new alternative game with a 4x4 size grid.
   */
  @FXML
  private void pressAlternative(final ActionEvent event) throws IOException {
    final Button btn = (Button) event.getSource();
    btn.getScene().getWindow().hide();
    new Game2048().startGame(CLASSIC_SIZE, GameMode.ALTERNATIVE);
  }

  /**
   * This method start a new classic game with a 4x4 size grid.
   */
  @FXML
  private void pressClassic(final ActionEvent event) throws IOException {
    final Button btn = (Button) event.getSource();
    btn.getScene().getWindow().hide();
    new Game2048().startGame(CLASSIC_SIZE, GameMode.CLASSIC);
  }

  /**
   * This method allows to turn to custom mode selection window.
   */
  @FXML
  private void pressCustom(final ActionEvent event) throws IOException {
    this.rootPane.getChildren().clear();
    final Parent root = FXMLLoader.load(getClass().getResource(FXMLPath.CUSTOM.getPath()));
    this.rootPane.getChildren().add(root);
  }
}
