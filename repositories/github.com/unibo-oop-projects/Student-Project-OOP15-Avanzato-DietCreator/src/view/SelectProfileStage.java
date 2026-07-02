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
 * classe SelectProfileStage, permette di selezionare il profilo corrente
 * estende Stage
 *  
 */
public class SelectProfileStage extends Stage{
	
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
	public SelectProfileStage(MainController controller){
		
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
        grid.add(profiles, 0, 1);
        
        this.profilesCB = new ComboBox<String>();
        this.profilesCB.setItems(names);
        grid.add(profilesCB, 1, 1);
        
        this.okB = new Button("OK");
        this.okB.setOnAction(e -> {
            if(!this.names.contains(this.profilesCB.getValue())){
                controller.buildAlert("No profile selected!");
            } else {
                controller.setCurrentProfile((String)this.profilesCB.getValue());
                try {
                    controller.changeScene(DietCreator.MENU);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                this.close();
            }
        });
        grid.add(okB, 1, 2);

        this.scene = new Scene(grid, 200, 150);
	    
	}
	
}
