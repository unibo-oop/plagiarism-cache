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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * classe che permette di aggiungere un alimento a un pasto di una dieta
 * estende Stage
 * 
 */
public class AddFoodOnDietStage extends Stage{
	
	private GridPane grid;
	private Scene scene;
	private Text title;
	private Label foods;
	private Label quantity;
	private ComboBox<String> foodsCB;
	private TextField quantityTF;
	private Button okB;
	
	private ObservableList<String> names;
	
	/**
     * costruttore
     * 
     * @param controller
     *        riferimento al controller
     * 
     */
	public AddFoodOnDietStage(MainController controller){
		
		this.buildScene(controller);
		this.setScene(scene);
		this.show();
		
	}
	
	/**
     * costruisce la scena
     * 
     * @param controller
     *        riferimento al controller
     * 
     */
	private void buildScene(MainController controller){
		
		this.grid = new GridPane();
		
		this.names = FXCollections.observableArrayList();
		controller.getFVList().stream().map(fv -> fv.getName()).forEach(s -> names.add(s));
		
		this.grid.setAlignment(Pos.CENTER);
		this.grid.setHgap(10);
		this.grid.setVgap(10);
		this.grid.setPadding(new Insets(25, 25, 25, 25));
		
		this.title = new Text("Select Food");
		this.title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		this.grid.add(title, 0, 0, 2, 1);
		
		this.foods = new Label("Foods:");
		grid.add(foods, 0, 1);
		
		this.foodsCB = new ComboBox<String>();
		this.foodsCB.setItems(names);
		grid.add(foodsCB, 1, 1);
		
		this.quantity = new Label("Quantity:");
		grid.add(quantity, 0, 2);
		
		this.quantityTF = new TextField();
		grid.add(quantityTF, 1, 2);
		
		this.okB = new Button("INSERT");
		this.okB.setOnAction(e -> {
			if(controller.checkEmptyField(this.quantityTF.getText()) || 
			        controller.checkValue(this.quantityTF.getText())){
				controller.buildAlert("Insert correct quantity value");
			} else {
				String name = this.foodsCB.getValue();
				System.out.println(name);
				Integer quantity = Integer.parseInt(this.quantityTF.getText());
				controller.addFoodOnDiet(name, quantity);
				this.close();
			}
		});
		grid.add(okB, 1, 3);

		this.scene = new Scene(grid, 200, 150);
		
	}
	
}
