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
 * classe DeleteProfileStage per selezionare ed eliminare un profilo salvato in precedenza
 * estende Stage 
 */
public class DeleteProfileStage extends Stage{
	
	private GridPane grid;
	private Scene scene;
	private Text title;
	private Label profiles;
	private ComboBox<String> profilesCB;
	private Button okB;
	private ObservableList<String> names;
	
	/**
     * costruttore
     * 
     * @param controller
     *     riferimento al controller
     *  
     */
	public DeleteProfileStage(MainController controller) {
		
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
	private void buildScene(MainController controller) {
		
		this.grid = new GridPane();
		
		this.names = FXCollections.observableArrayList();
		controller.getPList().stream().map(p -> p.getName()).forEach(s -> names.add(s));
		
		this.grid.setAlignment(Pos.CENTER);
		this.grid.setHgap(10);
		this.grid.setVgap(10);
		this.grid.setPadding(new Insets(25, 25, 25, 25));
		
		this.title = new Text("Select Profile");
		this.title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		this.grid.add(title, 0, 0, 2, 1);
		
		this.profiles = new Label("Profiles:");
		this.grid.add(profiles, 0, 1);
		
		this.profilesCB = new ComboBox<String>();
		this.profilesCB.setItems(names);
		this.grid.add(profilesCB, 1, 1);
		
		this.okB = new Button("OK");
		this.okB.setOnAction(e -> {
			if(controller.buildConfirmDialog()){
				try {
					controller.deleteProfile(this.profilesCB.getValue());
					controller.changeScene(DietCreator.PROFILES);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				this.close();
			}
		});
		this.grid.add(okB, 1, 2);
		this.scene = new Scene(grid, 200, 150);
	
	}
	
}
