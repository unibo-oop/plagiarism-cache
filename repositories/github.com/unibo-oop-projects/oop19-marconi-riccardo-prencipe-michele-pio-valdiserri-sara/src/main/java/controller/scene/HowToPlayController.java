package controller.scene;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import view.utils.FXMLPath;

/**
 * This class manage the how to play scene of the game.
 */
public class HowToPlayController {

  /**
   * Field of the scene's panel.
   */
  @FXML
  private BorderPane rootPane;

  /**
   * This method allows to turn back to main menu.
   */
  @FXML
  private void pressTurnBackHowToPlay(final ActionEvent event) throws IOException {
    this.rootPane.getChildren().clear();
    final Parent root = FXMLLoader.load(getClass().getResource(FXMLPath.MAINMENU.getPath()));
    this.rootPane.getChildren().add(root);
  }
}
