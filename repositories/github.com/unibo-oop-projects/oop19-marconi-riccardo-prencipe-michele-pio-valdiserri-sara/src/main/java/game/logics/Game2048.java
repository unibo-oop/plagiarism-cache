package game.logics;

import controller.game.GameControllerImpl;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.utils.ImageViewObject;

/**
 * Window of the game.
 *
 */
public class Game2048 extends Stage {

  private GameControllerImpl gameController;
  private Scene scene;
  private static final int SCREEN_WIDTH = 914;
  private static final int SCREEN_HEIGTH = 737;

  /**
   * Constructor.
   */
  public Game2048() {
    this.setTitle("2048");
    this.setResizable(false);
    this.setHeight(SCREEN_HEIGTH);
    this.setWidth(SCREEN_WIDTH);
    this.centerOnScreen();
    this.getIcons().add(ImageViewObject.LOGO.getImage());
  }

  /**
   * This method start a game.
   */
  public void startGame(final int size, final GameMode mode) {
    this.gameController = new GameControllerImpl(size, mode);
    this.scene = new Scene(gameController);
    this.scene.getStylesheets().add("view/style/style.css");
    this.setScene(scene);
    this.show();
  }
}
