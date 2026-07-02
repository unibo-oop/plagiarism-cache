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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.DietCreator;
import model.FoodValues;

/**
 * classe EditFoodValuesStage, permette di modificare i valori nutrizionali di un alimento salvato
 * estende Stage
 *  
 */
public class EditFoodValuesStage extends Stage{

		
	//the new food
	private Optional<FoodValues> selectedFood;
	private FoodValues food;
	
	//scene 1
	private GridPane firstGrid;
	private Scene firstScene;
	private Text firstTitle;
	private Label foods;
	private ComboBox<String> foodsCB;
	private ObservableList<String> foodNames;
	private Button okB;
	
	//scene 2
	private GridPane secondGrid;
	private Scene secondScene;
	private Text secondTitle;
	private Label name;
	private Label carbs;
	private Label fats;
	private Label proteins;
	private Label fibers;
	private Label nameLabel;
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
	public EditFoodValuesStage(MainController controller){
		
		this.buildFirstScene(controller);
		this.setScene(firstScene);
		this.show();
		
	}
	
	/**
     * prende i dati dalla form e crea il nuovo alimento da passare poi al controller
     *  
     */
	private void createFood(){
		String name = nameLabel.getText();
		double carbs = (double) Double.parseDouble(carbsTF.getText());
		double fats = (double) Double.parseDouble(fatsTF.getText());
		double prots = (double) Double.parseDouble(protsTF.getText());
		double fibers = (double) Double.parseDouble(fibersTF.getText());
		
		food = new FoodValues(name, carbs, fats, prots, fibers);
	}
	
	/**
     * seleziona il cibo da modificare
     * 
     * @param controller
     *      riferimento al controller
     *  
     */
	private FoodValues setSelectedFood(MainController controller){
		this.selectedFood = controller.getFVList().stream().filter(fv -> fv.getName().equals(this.foodsCB.getValue())).findFirst();
		return this.selectedFood.get();
	}
	
	/**
     * costruisce la prima scene
     * 
     * @param controller
     *     riferimento al controller
     *  
     */
	private void buildFirstScene(MainController controller){
		this.firstGrid = new GridPane();
		
		this.foodNames = FXCollections.observableArrayList();
		controller.getFVList().stream().map(fv -> fv.getName()).forEach(s -> foodNames.add(s));
		
		this.firstGrid.setAlignment(Pos.CENTER);
		this.firstGrid.setHgap(10);
		this.firstGrid.setVgap(10);
		this.firstGrid.setPadding(new Insets(25, 25, 25, 25));
		
		this.firstTitle = new Text("Select Food to edit");
		this.firstTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		this.firstGrid.add(firstTitle, 0, 0, 2, 1);
		
		this.foods = new Label("Foods:");
		firstGrid.add(foods, 0, 1);
		
		this.foodsCB = new ComboBox<String>();
		this.foodsCB.setItems(foodNames);
		firstGrid.add(foodsCB, 1, 1);
		
		this.okB = new Button("OK");
		this.okB.setOnAction(e -> {
			this.setSelectedFood(controller);
			this.buildSecondScene(controller);
			this.setScene(secondScene);
		});
		firstGrid.add(okB, 1, 2);
		
		this.firstScene = new Scene(firstGrid, 200, 150);
	}
	
	/**
     * costruisce la seconda
     * 
     * @param controller
     *     riferimento al controller
     *  
     */
	private void buildSecondScene(MainController controller){
		this.secondGrid = new GridPane();
		
		this.secondGrid.setAlignment(Pos.CENTER);
		this.secondGrid.setHgap(10);
		this.secondGrid.setVgap(10);
		this.secondGrid.setPadding(new Insets(25, 25, 25, 25));
			
		this.secondTitle = new Text("New Food");
		this.secondTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		this.secondGrid.add(secondTitle, 0, 0, 2, 1);
			
		this.name = new Label("Name:");
		secondGrid.add(name, 0, 1);
			
		this.nameLabel = new Label(this.selectedFood.get().getName());
		secondGrid.add(nameLabel, 1, 1);
			
		this.carbs = new Label("Carbohydrates:");
		secondGrid.add(carbs, 0, 2);
		
		this.carbsTF = new TextField();
		this.carbsTF.setText(this.selectedFood.get().getCarbs() + "");
		secondGrid.add(carbsTF, 1, 2);
		
		this.fats = new Label("Fats:");
		secondGrid.add(fats, 0, 3);
		
		this.fatsTF = new TextField();
		this.fatsTF.setText(this.selectedFood.get().getFats() + "");
		secondGrid.add(fatsTF, 1, 3);
		
		this.proteins = new Label("Proteins:");
		secondGrid.add(proteins, 0, 4);
		
		this.protsTF = new TextField();
		this.protsTF.setText(this.selectedFood.get().getProts() + "");
		secondGrid.add(protsTF, 1, 4);
		
		this.fibers = new Label("Fibers:");
		secondGrid.add(fibers, 0, 5);
		
		this.fibersTF = new TextField();
		this.fibersTF.setText(this.selectedFood.get().getFibers() + "");
		secondGrid.add(fibersTF, 1, 5);
		
		this.saveButton = new Button("Save");
		this.saveButton.setOnAction(e -> {
			if(controller.checkEmptyField(this.carbsTF.getText()) ||
					controller.checkEmptyField(this.fatsTF.getText()) ||
					controller.checkEmptyField(this.protsTF.getText()) ||
					controller.checkEmptyField(this.fibersTF.getText()) ||
					controller.checkValue(this.carbsTF.getText()) ||
	                controller.checkValue(this.fatsTF.getText()) ||
	                controller.checkValue(this.protsTF.getText()) ||
	                controller.checkValue(this.fibersTF.getText())){
				controller.buildAlert("Insert correct values");
			} else {
				try {
					this.createFood();
					controller.editFoodValues(selectedFood, food);
					controller.changeScene(DietCreator.FOODS);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				this.close();
			}
		});
		secondGrid.add(saveButton, 1, 7);
		
		this.secondScene = new Scene(secondGrid, 400, 300);
	}
}
