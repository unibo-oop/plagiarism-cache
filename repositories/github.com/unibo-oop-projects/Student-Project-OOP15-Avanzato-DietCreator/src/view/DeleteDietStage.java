package view;

import java.util.Optional;

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
import model.Diet;

/**
 * classe DeleteDiet per eliminare una dieta
 * estende Stage
 *  
 */
public class DeleteDietStage extends Stage{
	
	private GridPane grid;
	private Text title;
	private Label diets;
	private ComboBox<String> dietsCB;
	private ObservableList<String> dietsOList;
	private Button okB;
	private Scene scene;
	private Optional<Diet> dietToDelete;
	
	/**
     * costruttore
     * 
     * @param controller
     *     riferimento al controller
     *  
     */
	public DeleteDietStage(MainController controller){
		
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
		
		this.dietsOList = FXCollections.observableArrayList();
		controller.getCurrentProfile().getDiets().stream().map(d -> d.getName()).forEach(s -> dietsOList.add(s));
		
		this.grid.setAlignment(Pos.CENTER);
		this.grid.setHgap(10);
		this.grid.setVgap(10);
		this.grid.setPadding(new Insets(25, 25, 25, 25));
		
		this.title = new Text("Select Diet to delete");
		this.title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		this.grid.add(title, 0, 0, 2, 1);
		
		this.diets = new Label("Diets:");
		grid.add(diets, 0, 1);
		
		this.dietsCB = new ComboBox<String>();
		this.dietsCB.setItems(dietsOList);
		grid.add(dietsCB, 1, 1);
		
		this.okB = new Button("OK");
		this.okB.setOnAction(e -> {
			if(controller.buildConfirmDialog()){
				this.dietToDelete = controller.getCurrentProfile().getDiets().stream()
						.filter(d -> d.getName().equals(dietsCB.getValue()))
						.findFirst();
				try {
					controller.deleteDiet(this.dietToDelete.get());
					controller.changeScene(DietCreator.DIETS);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
				this.close();
			}
		});
		grid.add(okB, 1, 2);
		
		this.scene = new Scene(grid, 220, 150);
		
	}
	
}
