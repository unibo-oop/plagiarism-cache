package view;

import controller.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.DietCreator;
import model.FoodOnDiet;

/**
 * classe AddMealStage, per aggiungere un pasto ad una dieta
 * estende Stage
 *  
 */
public class AddMealStage extends Stage{
	
	private Text title;
	private TableView<FoodOnDiet> table;
	private BorderPane layout = new BorderPane();
	private VBox vBox;
	private ObservableList<FoodOnDiet> list;
	private Scene scene;
	private Button refreshB;
	private Button saveB;
	
	/**
     * costruttore
     * 
     * @param controller
     *     riferimento al controller
     *  
     */
	public AddMealStage(MainController controller){
		
		this.buildScene(controller);
		this.setScene(scene);
		this.setResizable(false);
		this.show();
		
	}
	
	/**
     * costruisce la scene
     * 
     * @param controller
     *     riferimento al controller
     *  
     */
	@SuppressWarnings("unchecked")
    private void buildScene(MainController controller){
		
		layout = new BorderPane();

		list = FXCollections.observableArrayList(controller.getFODList());
		
		title = new Text("New Meal");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
		
		//the columns
		TableColumn<FoodOnDiet, Integer> column1 = new TableColumn<>("Name");
		column1.setCellValueFactory(new PropertyValueFactory<FoodOnDiet, Integer>("name"));
		
		TableColumn<FoodOnDiet, Double> column2 = new TableColumn<>("Carbohydrates");
		column2.setCellValueFactory(new PropertyValueFactory<FoodOnDiet, Double>("carbs"));	
		
		TableColumn<FoodOnDiet, Double> column3 = new TableColumn<>("Fats");
		column3.setCellValueFactory(new PropertyValueFactory<FoodOnDiet, Double>("fats"));
		
		TableColumn<FoodOnDiet, Double> column4 = new TableColumn<>("Proteins");
		column4.setCellValueFactory(new PropertyValueFactory<FoodOnDiet, Double>("prots"));
		
		TableColumn<FoodOnDiet, Double> column5 = new TableColumn<>("Fibers");
		column5.setCellValueFactory(new PropertyValueFactory<FoodOnDiet, Double>("fibers"));
		
		TableColumn<FoodOnDiet, Double> column6 = new TableColumn<>("Kcals");
		column6.setCellValueFactory(new PropertyValueFactory<FoodOnDiet, Double>("totKcals"));
		
		TableColumn<FoodOnDiet, Integer> column7 = new TableColumn<>("Quantity");
		column7.setCellValueFactory(new PropertyValueFactory<FoodOnDiet, Integer>("quantity"));
		
		this.table = new TableView<FoodOnDiet>();
		this.table.setItems(list);
		this.table.setEditable(false);
		
				
		this.table.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7);
		this.table.getColumns().forEach(c -> c.prefWidthProperty().bind(this.table.widthProperty().divide(7)));

				
		this.vBox = new VBox();
		
		this.refreshB = new Button("REFRESH");
		this.refreshB.setOnAction(e -> {
			this.buildScene(controller);
			this.setScene(scene);
		});
		StackPane sp1 = new StackPane();
		sp1.getChildren().add(refreshB);
		
		this.saveB = new Button("SAVE");
		this.saveB.setOnAction(e -> {
			if(controller.getFODList().size() == 0){
				controller.buildAlert("This meal has no foods");
			} else {
				controller.addMeal();
				this.close();
			}		
		});
		StackPane sp2 = new StackPane();
		sp2.getChildren().add(saveB);
		
		layout.setCenter(vBox);
		vBox.getChildren().add(title);
		vBox.getChildren().add(table);
		vBox.getChildren().add(new MenuItem(DietCreator.INSERTFOOD, controller));
		vBox.getChildren().add(sp1);
		vBox.getChildren().add(sp2);
		
		this.scene = new Scene(layout, DietCreator.WIDTH + 40, 300);
		
	}
	
}
