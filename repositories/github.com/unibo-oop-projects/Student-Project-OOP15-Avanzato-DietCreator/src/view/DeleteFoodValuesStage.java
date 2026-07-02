package view;

import controller.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.DietCreator;

/**
 * classe DeleteFoodValuesStage, per selezionare ed eliminare un alimento salvato in precedenza
 * 
 *  
 */
public class DeleteFoodValuesStage extends Stage {
	
	private GridPane grid;
	private Text title;
	private Label foods;
	private ComboBox<String> foodsCB;
	private ObservableList<String> foodNames;
	private Button okB;
	private Scene scene;
	
	/**
     * costruttore
     * 
     * @param controller
     *     riferimento al controller
     *  
     */
	public DeleteFoodValuesStage(MainController controller) {
		
		this.buildScene(controller);
		this.setScene(scene);
		this.show();
		
	}
	
	/**
     * costruisce la scene
     * 
     * @param controller
     *     riferimento al controller
     *  
     */
	private void buildScene(MainController controller){
		
		this.grid = new GridPane();
		
		this.foodNames = FXCollections.observableArrayList();
		controller.getFVList().stream().map(fv -> fv.getName()).forEach(s -> foodNames.add(s));
		
		this.grid.setAlignment(Pos.CENTER);
		this.grid.setHgap(10);
		this.grid.setVgap(10);
		this.grid.setPadding(new Insets(25, 25, 25, 25));
		
		this.title = new Text("Select Food to delete");
		this.title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		this.grid.add(title, 0, 0, 2, 1);
		
		this.foods = new Label("Foods:");
		this.grid.add(foods, 0, 1);
		
		this.foodsCB = new ComboBox<String>();
		this.foodsCB.setItems(foodNames);
		this.grid.add(foodsCB, 1, 1);
		
		this.okB = new Button("OK");
		this.okB.setOnAction(e -> {
			if(controller.buildConfirmDialog()){
				try {
					controller.deleteFoodValues(foodsCB.getValue());
					controller.changeScene(DietCreator.FOODS);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				this.close();
			}
		});
		this.grid.add(okB, 1, 2);
		
		this.scene = new Scene(grid, 220, 150);
		
	}
	
}
