package view;

import controller.MainController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * classe EditDietStage, permette di modificare una dieta che si sta creando
 * estende Stage
 * 
 * 
 */
public class EditDietStage extends Stage{
	
	private GridPane grid;
	private Scene scene;
	private Text title;
	private Label name;
	private Label target;
	private Label targetLabel;
	private Label initWeight;
	private Label finWeight;
	private TextField nameTF;
	private Label initWeightL;
	private Label finWeightL;
	
	private Button saveButton;
	
	/**
     * costruttore
     * 
     * @param controller
     *     riferimento al controller
     *  
     */
	public EditDietStage(MainController controller){
	
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
        
        this.grid.setAlignment(Pos.CENTER);
        this.grid.setHgap(10);
        this.grid.setVgap(10);
        this.grid.setPadding(new Insets(25, 25, 25, 25));
        
        this.title = new Text("Edit Diet");
        this.title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.grid.add(title, 0, 0, 2, 1);
        
        this.name = new Label("Name:");
        grid.add(name, 0, 1);
        
        this.nameTF = new TextField();
        this.nameTF.setText(controller.getNewDiet().getName());
        grid.add(nameTF, 1, 1);
        
        this.target = new Label("Target:");
        grid.add(target, 0, 2);
        
        this.targetLabel = new Label(controller.getNewDiet().getTarget() + " WEIGHT");
        grid.add(targetLabel, 1, 2);
        
        this.initWeight = new Label("Initial Weight:");
        grid.add(initWeight, 0, 3);
        
        this.initWeightL = new Label(controller.getNewDiet().getInitialWeight() + "");
        grid.add(initWeightL, 1, 3);
        
        this.finWeight = new Label("Final Weight:");
        grid.add(finWeight, 0, 4);
        
        this.finWeightL = new Label(controller.getNewDiet().getInitialWeight() + "");
        grid.add(finWeightL, 1, 4);
                
        this.saveButton = new Button("Save");
        this.saveButton.setOnAction(e -> {
            controller.getNewDiet().setName(nameTF.getText());
            controller.getNewDiet().setFinalWeight(Double.parseDouble(finWeightL.getText()));
            this.close();
        });
        grid.add(saveButton, 1, 7);
        
        this.scene = new Scene(grid, 400, 300);
	    
	}
	
}
