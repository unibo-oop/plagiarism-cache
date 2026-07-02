package view;

import java.util.List;

import controller.MainController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.DietCreator;
import model.Ectomorph;
import model.Endomorph;
import model.Mesomorph;
import model.Somatotype;

/**
 * classe HelpStage, aiuta l'utente a capire il proprio somatotipo durante la creazione di un profilo
 * estende stage
 * 
 */
public class HelpStage extends Stage{
	
	private Text title;
	private StackPane titlePane;
	private ImageView imageView;
	private BorderPane layout;
	private VBox imageBox;
	private VBox traitsBox;
	private StackPane buttonsPane;
	private GridPane buttonsGrid;
	private Button nextB;
	private Button prevB;
	private Scene scene;
	
	private int index;
	
	private Somatotype ecto = new Ectomorph();
	private Somatotype meso = new Mesomorph();
	private Somatotype endo = new Endomorph();
	private List<String> ectoTraits = ecto.getTraits();
	private List<String> mesoTraits = meso.getTraits();
	private List<String> endoTraits = endo.getTraits();
	
	/**
     * costruttore
     * 
     * @param controller
     *     riferimento al controller
     *  
     */
	public HelpStage(MainController controller){
		
		this.setTitle("Help");
		this.index = 0;
		this.buildSomatotypeScene(controller);
		this.setScene(scene);
		this.show();
		
	}
	
	/**
     * costruisce la scene relativa ai somatotipi
     * 
     * @param controller
     *     riferimento al controller
     *  
     */
	private void buildSomatotypeScene(MainController controller){
		
		this.layout = new BorderPane();
		this.imageBox = new VBox();
		this.imageBox.setAlignment(Pos.CENTER);
		this.traitsBox = new VBox();
		this.prevB = new Button("PREV");
		this.prevB.setOnAction(e -> {
			index--;
			this.buildSomatotypeScene(controller);
			this.setScene(scene);
		});
		this.nextB = new Button("NEXT");
		this.nextB.setOnAction(e -> {
			index++;
			this.buildSomatotypeScene(controller);
			this.setScene(scene);
		});
		
		
		this.title = new Text("");
		this.title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		this.titlePane = new StackPane();
		this.titlePane.getChildren().add(title);
		this.traitsBox.getChildren().add(titlePane);
		
		this.buttonsGrid = new GridPane();
		this.buttonsGrid.add(prevB, 0, 0);
		this.buttonsGrid.add(nextB, 2, 0);
		this.buttonsPane = new StackPane();
		this.buttonsGrid.setAlignment(Pos.CENTER);
		this.buttonsPane.getChildren().add(buttonsGrid);
		
		switch(index){
			case 0:
				this.imageView = new ImageView(new Image(getClass().getResourceAsStream(DietCreator.ECTOIMG), 400, 300, true, true));
				this.imageBox.getChildren().add(imageView);
				this.title.setText("Ectomorph");
				this.prevB.setVisible(false);
				this.ectoTraits.stream().forEach(s -> {
					StackPane pane = new StackPane();
					pane.getChildren().add(new Label(s));
					this.traitsBox.getChildren().add(pane);
				});
				break;
			case 1:
				this.imageView = new ImageView(new Image(getClass().getResourceAsStream(DietCreator.MESOIMG), 400, 300, true, true));
				this.imageBox.getChildren().add(imageView);
				this.title.setText("Mesomorph");
				this.mesoTraits.stream().forEach(s -> {
					StackPane pane = new StackPane();
					pane.getChildren().add(new Label(s));
					this.traitsBox.getChildren().add(pane);
				});
				break;
			case 2:
				this.imageView = new ImageView(new Image(getClass().getResourceAsStream(DietCreator.ENDOIMG), 400, 300, true, true));
				this.imageBox.getChildren().add(imageView);
				this.title.setText("Endomorph");
				this.nextB.setVisible(false);
				this.endoTraits.stream().forEach(s -> {
					StackPane pane = new StackPane();
					pane.getChildren().add(new Label(s));
					this.traitsBox.getChildren().add(pane);
				});
				break;
			default:
				break;
		}
		
		this.layout.setTop(imageBox);
		this.layout.setCenter(traitsBox);
		this.layout.setBottom(buttonsPane);
		this.scene = new Scene(layout, 400, 500);
		
	}
	
	
	
}
