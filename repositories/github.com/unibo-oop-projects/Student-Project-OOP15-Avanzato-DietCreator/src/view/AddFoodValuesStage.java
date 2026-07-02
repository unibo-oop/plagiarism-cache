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
import main.DietCreator;
import model.FoodValues;

/**
 * classe che permette di aggiungere un alimento e i relativi valori nutrizionali
 * estende Stage
 * 
 */
public class AddFoodValuesStage extends Stage{
	
	private GridPane grid;
	private Scene scene;
		
	//the new food 
	private FoodValues food;
		
	private Text title;
	private Label name;
	private Label carbs;
	private Label fats;
	private Label proteins;
	private Label fibers;
	private TextField nameTF;
	private TextField carbsTF;
	private TextField fatsTF;
	private TextField protsTF;
	private TextField fibersTF;
		
	private Button saveButton;
	
	/**
     * costruttore
     * 
     * @param controller
     *     riferimento al controller
     *  
     */
	public AddFoodValuesStage(MainController controller){

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
            
        this.title = new Text("New Food");
        this.title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.grid.add(title, 0, 0, 2, 1);
            
        this.name = new Label("Name:");
        this.grid.add(name, 0, 1);
            
        this.nameTF = new TextField();
        this.grid.add(nameTF, 1, 1);
            
        this.carbs = new Label("Carbohydrates:");
        this.grid.add(carbs, 0, 2);
        
        this.carbsTF = new TextField();
        this.grid.add(carbsTF, 1, 2);
        
        this.fats = new Label("Fats:");
        this.grid.add(fats, 0, 3);
        
        this.fatsTF = new TextField();
        this.grid.add(fatsTF, 1, 3);
        
        this.proteins = new Label("Proteins:");
        this.grid.add(proteins, 0, 4);
        
        this.protsTF = new TextField();
        this.grid.add(protsTF, 1, 4);
        
        this.fibers = new Label("Fibers:");
        this.grid.add(fibers, 0, 5);
        
        this.fibersTF = new TextField();
        this.grid.add(fibersTF, 1, 5);
        
        this.saveButton = new Button("Save");
        this.saveButton.setOnAction(e -> {
            if(controller.checkEmptyField(this.nameTF.getText()) || 
                controller.checkEmptyField(this.carbsTF.getText()) || 
                controller.checkEmptyField(this.protsTF.getText()) || 
                controller.checkEmptyField(this.fatsTF.getText()) || 
                controller.checkEmptyField(this.fibersTF.getText()) ||
                controller.checkValue(this.carbsTF.getText()) ||
                controller.checkValue(this.fatsTF.getText()) ||
                controller.checkValue(this.protsTF.getText()) ||
                controller.checkValue(this.fibersTF.getText())) {
                controller.buildAlert("Not all fields are filled");
            } else if(controller.checkIfPresent(this.nameTF.getText(), DietCreator.FOODNAME)){
                controller.buildAlert(DietCreator.FOODNAME + DietCreator.TAKEN);
            }else {
                try {
                    this.createFood();
                    controller.saveFoodValues(food);
                    controller.changeScene(DietCreator.FOODS);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                this.close();
            }
        });
        this.grid.add(saveButton, 1, 7);
        
        this.scene = new Scene(grid, 400, 300);
	}
	
	/**
     * prende i dati dalla form e crea il nuovo alimento da passare poi al controller
     *  
     */
	private void createFood(){
		String name = nameTF.getText();
		double carbs = (double) Double.parseDouble(carbsTF.getText());
		double fats = (double) Double.parseDouble(fatsTF.getText());
		double prots = (double) Double.parseDouble(protsTF.getText());
		double fibers = (double) Double.parseDouble(fibersTF.getText());
		
		food = new FoodValues(name, carbs, fats, prots, fibers);
	}
}
