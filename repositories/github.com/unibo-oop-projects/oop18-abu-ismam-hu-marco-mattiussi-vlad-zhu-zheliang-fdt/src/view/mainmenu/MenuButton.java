package view.mainmenu;
import constants.GameConstants;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MenuButton extends StackPane {
	
	private Text text;
	
	public MenuButton(String name) {
		
		text = new Text(name);
		text.setFont(Font.loadFont("file:res/JOJO____.ttf", GameConstants.buttonSize/2));
		text.setFill(Color.DEEPPINK);
		
		Rectangle bg = new Rectangle(300,70);
		bg.setOpacity(0.8);
		bg.setFill(Color.CYAN);
		
		  setAlignment(Pos.CENTER);
            setRotate(-0.5);
            getChildren().addAll(bg, text);

            setOnMouseEntered(event -> {
                bg.setTranslateX(10);
                text.setTranslateX(10);
                bg.setFill(Color.WHITE);
                text.setFill(Color.BLACK);
            });

            setOnMouseExited(event -> {
                bg.setTranslateX(0);
                text.setTranslateX(0);
                bg.setFill(Color.CYAN);
                text.setFill(Color.DEEPPINK);
            });

            DropShadow drop = new DropShadow(50, Color.WHITE);
            drop.setInput(new Glow());

            setOnMousePressed(event -> setEffect(drop));
            setOnMouseReleased(event -> setEffect(null));  
	}
}

