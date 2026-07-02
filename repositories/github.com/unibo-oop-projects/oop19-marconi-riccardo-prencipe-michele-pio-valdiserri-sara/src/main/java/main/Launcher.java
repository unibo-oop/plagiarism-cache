package main;

import javafx.application.Application;

/**
 * This class start the application.
 */
public class Launcher {

  /**
   * This method start the game.
   */
  public static void main(final String[] args) {
    Application.launch(view.sceneLoader.SceneLoader.class, args);
  }
}
