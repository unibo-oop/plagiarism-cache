package controller.scene;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import view.utils.FXMLPath;
import view.windows.RankingView;

/**
 * This class manage the main menu scene of the game.
 */
public class MenuController {

  /**
   * Field of the scene's panel.
   */
  @FXML
  private Pane rootPane;

  /**
   * This method allows to turn to how to play window.
   */
  @FXML
  private void pressHowToPlay(final ActionEvent event) throws IOException {
    this.rootPane.getChildren().clear();
    final Parent root = FXMLLoader.load(getClass().getResource(FXMLPath.HOWTOPLAY.getPath()));
    this.rootPane.getChildren().add(root);
  }

  /**
   * This method allows to turn to start game window.
   */
  @FXML
  private void pressModeSelection(final ActionEvent event) throws IOException {
    this.rootPane.getChildren().clear();
    final Parent root = FXMLLoader.load(getClass().getResource(FXMLPath.MODE.getPath()));
    this.rootPane.getChildren().add(root);
  }

  /**
   * This method allows to turn to ranking window.
   */
  @FXML
  private void pressRanking(final ActionEvent event) throws IOException {
    this.rootPane.getChildren().clear();
    final Parent root = new RankingView();
    this.rootPane.getChildren().add(root);
  }
}
