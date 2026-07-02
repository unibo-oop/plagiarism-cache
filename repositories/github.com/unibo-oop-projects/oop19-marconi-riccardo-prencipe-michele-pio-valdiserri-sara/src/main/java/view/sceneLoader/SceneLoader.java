package view.sceneLoader;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.utils.FXMLPath;
import view.utils.ImageViewObject;

/**
 * This class start and manage the main menu of the game.
 *
 */
public class SceneLoader extends Application {

  /**
   * This field is the main stage of the game.
   */
  private final Stage primaryStage = new Stage();

  @Override
  public void start(final Stage primaryStage) throws IOException {
    this.primaryStage.setTitle("2048");
    this.primaryStage.setResizable(false);
    this.primaryStage.getIcons().add(ImageViewObject.LOGO.getImage());
    showGame();
  }

  /**
   * This method show the menu of the game.
   * 
   * @throws IOException
   *           if the method fails to save the field.
   */
  @FXML
  private void showGame() throws IOException {
    Parent root;
    final FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(FXMLPath.MAINMENU.getPath()));
    root = loader.load();
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }
}
