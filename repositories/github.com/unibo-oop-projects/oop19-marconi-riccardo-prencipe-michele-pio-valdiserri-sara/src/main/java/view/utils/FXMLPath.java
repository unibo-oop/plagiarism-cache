package view.utils;

/**
 * This class create the string path to upload the scene of the game.
 *
 */
public enum FXMLPath {

  /**
   * Main menu.
   */
  MAINMENU("MainMenu"),

  /**
   * How To Play menu.
   */
  HOWTOPLAY("HowToPlay"),

  /**
   * Mode selection menu.
   */
  MODE("ModeSelection"),

  /**
   * Custom mode selection menu.
   */
  CUSTOM("CustomModeSelection");

  /**
   * This fields save the generic path of the scene.
   */
  private final String name;
  private final String path = "/view/scenes/";
  private final String extension = ".fxml";

  /**
   * Constructor of the class, save the name of the scene.
   * 
   * @param nameScene
   *          of the scene to upload.
   */
  FXMLPath(final String nameScene) {
    this.name = nameScene;
  }

  /**
   * Create the true path of the scene.
   * 
   * @return path of the scene to upload.
   */
  public String getPath() {
    return this.path + this.name + this.extension;
  }
}
