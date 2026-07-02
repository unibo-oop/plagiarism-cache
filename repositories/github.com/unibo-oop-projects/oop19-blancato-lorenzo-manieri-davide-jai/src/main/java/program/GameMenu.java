package program;

import java.io.IOException;
import java.io.InputStream;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class GameMenu extends Application{
	
	public static Stage stageWindow;
	private static int paneWidth = 1050;
	private static int paneHeight = 600;
	
	
	/**
	 * create menu pane and set every item in fixed position
	 * @return root pane with menu component 
	 * 
	 */
	public static Parent createMainPane() {
		Pane root = new Pane();
		
		root.setPrefSize(paneWidth, paneHeight);
		
		
		try(InputStream is = ClassLoader.getSystemResourceAsStream("SfondoMenu.jpg")){
			ImageView img = new ImageView(new Image(is));
			img.setFitWidth(root.getPrefWidth());
			img.setFitHeight(root.getPrefHeight());
			root.getChildren().add(img);
		}
		catch(IOException e) {
			System.out.println("Couldn't load image");
		}
		
		MenuTitle title = new MenuTitle ("JALIEN INVASION");
		title.setTranslateX(root.getPrefWidth() / 4);
		title.setTranslateY(root.getPrefHeight() / 10);

		
		MenuBox vbox = new MenuBox(
				new MenuComponent(MenuComponent.START),
				new MenuComponent(MenuComponent.HIGHSCORES),
				new MenuComponent(MenuComponent.EXIT));
		vbox.setTranslateX(root.getPrefWidth() / 10);
		vbox.setTranslateY(root.getPrefHeight() / 2);
		
		root.getChildren().addAll(title, vbox);
		
		return root;
		
	}

	
	@Override
	public void start(Stage primaryStage) throws Exception{
		Scene scene = new Scene(createMainPane());
		stageWindow = primaryStage;
		primaryStage.setResizable(false);
		primaryStage.setTitle("JALIEN INVASION");
		primaryStage.setScene(scene);
		Game.setMenuScene(scene);
		Game.setStage(primaryStage);
        primaryStage.centerOnScreen();
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}