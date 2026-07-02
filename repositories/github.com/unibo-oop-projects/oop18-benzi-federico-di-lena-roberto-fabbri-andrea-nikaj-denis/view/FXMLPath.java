package view;

/**
 * The enum is used to make the path of the different scenes
 * @author Andrea
 *
 */
public enum FXMLPath {

	/**
	 * The field used to manage the scenes of the application
	 */
	MENU("MenuGUI"), 
	HIGHSCORE("HighScorePage"), 
	HELP("Help"), 
	LOGIN("Login"),
	GAMEOVER("GameOver");
	
	private final String name;
	private final String path = "/view/scenes/";
	private final String extension = ".fxml";
	
	/**
	 * The constructor of the class, save the name of the scene
	 * @param nameScene
	 */
	FXMLPath(final String nameScene) {
		this.name = nameScene;
	}
	 
	/**
	 * 
	 * @return the path of the scene to upload
	 */
	public String getPath() {
		return this.path + this.name + this.extension;
	}
}
