package program;


import javafx.geometry.Pos;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;



public class MenuComponent extends StackPane{
	
	public static String START = "START GAME";
	public static String HIGHSCORES = "HIGH SCORES";
	public static String EXIT = "EXIT TO DESKTOP";
	
	/**
	 * Constructor used to create a menu component using specified colors
	 * @param name name of menu component 
	 */
	public MenuComponent(String name) {
		LinearGradient gradient = new LinearGradient(0, 0, 0, 0, true, CycleMethod.NO_CYCLE, new Stop[] { 
			new Stop(0, Color.BLACK),
			new Stop(0.1, Color.DARKSLATEBLUE),
			new Stop(0.9, Color.DARKSLATEBLUE),
			new Stop(1, Color.BLACK)
			
		});
		
		Polygon bg = new Polygon(
                0, 0,
                200, 0,
                215, 15,
                200, 30,
                0, 30
        );
		bg.setOpacity(0.9);
		bg.setEffect(new InnerShadow());
		
		
		Text text = new Text(name);
		text.setFill(Color.DARKGREY);
		
		
		final Font f = Font.loadFont(ClassLoader.getSystemResourceAsStream("Nebullium.ttf"), 15);
		text.setFont(f);
		   
		
		
		setAlignment(Pos.CENTER);
		getChildren().addAll(bg, text);
		
		setOnMouseEntered(event -> {
			bg.setFill(gradient);
			text.setFill(Color.DEEPSKYBLUE);
			
		});
		
		setOnMouseExited(event -> {
			bg.setFill(Color.BLACK);
			text.setFill(Color.DARKGREY);
		});
		
		setOnMousePressed(event -> {
			bg.setFill(Color.color(0.34, 0.70, 0.83, 0.70));
			
		});
		
		setOnMouseReleased(event -> {
			bg.setFill(gradient);
		});
		
		setOnMouseClicked(event ->{
			if(name.equals(START)) {
				UsernameSetter.display("Username");
			}
			else if (name.equals(HIGHSCORES)) {
				try {
					ScoreHistory.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
				//funzione per visualizzare gli score
			}
			else if (name.equals(EXIT)) {
				Game.stage.close();
				System.exit(0);
			}
			
		});
		
		
	}
}

