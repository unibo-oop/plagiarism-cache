package view.gameview;
import constants.GameConstants;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import utilityclasses.Pair;

public class GridButton extends StackPane{
	
	private static final Image logo = new Image("grass.jpg");

	private Text text;
	public Pair<Integer,Integer> position;
	
	public GridButton(String name){
		
		text = new Text(name);

		text.setFill(Color.DEEPPINK);
		
		/* REALIZZAZIONE DI OGNI TILE */
		Rectangle bg = new Rectangle(GameConstants.buttonSize,GameConstants.buttonSize);
		bg.setOpacity(100);
		/* grass image */
		ImageView img = new ImageView(logo);
		img.setFitWidth(50);
		img.setFitHeight(50);
		bg.setFill(new ImagePattern(logo));
		
		
		  setAlignment(Pos.CENTER);
            setRotate(-0.5);
            getChildren().addAll(bg, text);

            setOnMouseEntered(event -> {
                text.setTranslateX(10);
                bg.setFill(Color.CYAN);
                text.setFill(Color.BLACK);
            });

            setOnMouseExited(event -> {
                bg.setTranslateX(0);
                text.setTranslateX(0);
                bg.setFill(new ImagePattern(logo));
                text.setFill(Color.DEEPPINK);
            });

            DropShadow drop = new DropShadow(50, Color.WHITE);
            drop.setInput(new Glow());

            setOnMousePressed(event -> setEffect(drop));
            setOnMouseReleased(event -> setEffect(null));  
	}
	
	public void setPosition(Pair<Integer, Integer> p){
		this.position=p;
	}
	
	public Pair<Integer, Integer> getPosition() {
		return this.position;
	}
}

