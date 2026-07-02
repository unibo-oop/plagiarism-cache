package view;

import controller.MainController;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import main.DietCreator;

/**
 * classe MenuScene, rappresenta il menu principale dell'applicazione
 * estende Scene
 */
public class MenuScene extends Scene{
	
	private static Label title;
	private static BorderPane layout;
	private ImageView imageView  = new ImageView(new Image(getClass().getResourceAsStream(DietCreator.TITLEIMG), 300, 400, true, true));
	
	//the menu
	private static VBox options;
	
	//the title
	private static VBox vBox;
	
	/**
     * costruttore
     * 
     * @param controller
     *     riferimento al controller
     *  
     * @param root
     * 
     * @param width
     *      la larghezza
     * 
     * @param height
     *      l'altezza
     */
	public MenuScene(MainController controller, Parent root, double width, double height) {
		super(init(), width, height);
		vBox.getChildren().add(imageView);
		options.getChildren().add(new TitleItem("", true, controller));
		options.getChildren().add(new MenuItem("PROFILES", controller));
		options.getChildren().add(new MenuItem("DIETS", controller));
		options.getChildren().add(new MenuItem("FOODS", controller));

	}
	
	/**
     * metodo che inizializza il layout della scene
     * 
     * @param controller
     *     riferimento al controller
     *  
     */
	public static BorderPane init(){
		
		layout = new BorderPane();
		title = new Label();
		title.setText(DietCreator.TITLE);
		
		options = new VBox(20);
		
		options.setAlignment(Pos.CENTER);
		
		vBox = new VBox(20);
		vBox.setAlignment(Pos.CENTER);
		
		layout.setTop(vBox);
		layout.setCenter(options);
		
		return layout;

	}
	
}
