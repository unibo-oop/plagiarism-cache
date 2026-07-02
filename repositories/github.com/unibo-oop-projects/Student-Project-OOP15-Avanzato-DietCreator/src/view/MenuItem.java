package view;

import controller.MainController;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * classe MenuItem, rappresenta una voce menu
 * estende StackPane
 */
public class MenuItem extends StackPane {

	private Text text;
	
	/**
     * costruttore
     * 
     * @param name
     *      il nome della voce menu
     *      
     * @param controller
     *     riferimento al controller
     *  
     */
	public MenuItem(String name, MainController controller){
		
		//the text of the menu item
		this.text = new Text(name);
		this.text.setFill(Color.BLACK);
		this.text.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD, 22));
		
		setOnMouseEntered(e -> text.setFill(Color.RED));
		
		setOnMouseExited(e -> text.setFill(Color.BLACK));
		
		setOnMousePressed(e -> {
			try {
				controller.changeScene(name);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		this.getChildren().add(text);
	}
	
}

