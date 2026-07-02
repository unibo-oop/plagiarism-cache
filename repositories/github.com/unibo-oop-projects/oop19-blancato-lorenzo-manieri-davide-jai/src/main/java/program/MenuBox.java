package program;
import javafx.scene.layout.VBox;

/**
 * Utility Class to set spacing between menu component and to add everything on the window
 * 
 *
 */
public  class MenuBox extends VBox{
	/**
	 * Constructor
	 * @param items to be placed on the menu
	 * 
	 */
	public MenuBox(MenuComponent...items) {
		setSpacing(2.0);
		getChildren().addAll(items);
	}
}