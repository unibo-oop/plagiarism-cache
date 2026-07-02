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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.DietCreator;
import model.Diet;
import model.FoodOnDiet;

/**
 * classe ViewDietStage, permette di selezionare e visionare una dieta creata
 * estende Stage
 *  
 */
public class ViewDietStage extends Stage{
	
	//Scene1
	private GridPane firstGrid;
	private Text firstTitle;
	private Label diets;
	private ComboBox<String> dietsCB;
	private ObservableList<String> dietsOList;
	private Button viewB;
	private Scene firstScene;
	
	//Scene2
	private Text secondTitle;
	private TableView<FoodOnDiet> fODTable;
	private ObservableList<FoodOnDiet> fODList;
	private Optional<Diet> dietToView;
	private BorderPane layout = new BorderPane();
	private VBox vBox;
	private Button nextB;
	private Button prevB;
	private int index;
	private Scene secondScene;

	/**
     * costruttore
     * 
     * @param controller
     *     riferimento al controller
     *  
     */
	public ViewDietStage(MainController controller){
		
		this.index = 0;
		this.buildFirstScene(controller);
		this.setScene(firstScene);
		this.show();
		
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
		
		this.dietsOList = FXCollections.observableArrayList();
		controller.getCurrentProfile().getDiets().stream().map(d -> d.getName()).forEach(s -> dietsOList.add(s));
		
		this.firstGrid.setAlignment(Pos.CENTER);
		this.firstGrid.setHgap(10);
		this.firstGrid.setVgap(10);
		this.firstGrid.setPadding(new Insets(25, 25, 25, 25));
		
		this.firstTitle = new Text("Select Diet to view");
		this.firstTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		this.firstGrid.add(firstTitle, 0, 0, 2, 1);
		
		this.diets = new Label("Diets:");
		firstGrid.add(diets, 0, 1);
		
		this.dietsCB = new ComboBox<String>();
		this.dietsCB.setItems(dietsOList);
		firstGrid.add(dietsCB, 1, 1);
		
		this.viewB = new Button("OK");
		this.viewB.setOnAction(e -> {
			this.dietToView = controller.getCurrentProfile().getDiets().stream().filter(d -> d.getName().equals(dietsCB.getValue())).findFirst();
			this.buildSecondScene(controller);
			this.setScene(secondScene);
		});
		firstGrid.add(viewB, 1, 2);
		
		this.firstScene = new Scene(firstGrid, 200, 150);
		
	}
	
	/**
     * costruisce la seconda scene
     * 
     * @param controller
     *     riferimento al controller
     *  
     */
	@SuppressWarnings("unchecked")
    private void buildSecondScene(MainController controller){
		
		this.layout = new BorderPane();

		this.fODList = FXCollections.observableArrayList(this.dietToView.get().getMealList().get(index).getFList());
		System.out.println(index);
		System.out.println(this.dietToView.get().getMealList().get(index).getFList().size());
		this.dietToView.get().getMealList().get(index).getFList().forEach(f -> System.out.println(f.getName() + "abc"));
		
		this.secondTitle = new Text("Meal" + this.dietToView.get().getMealList().get(index).getNumber());
		this.secondTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
		
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
		
		this.fODTable = new TableView<FoodOnDiet>();
		this.fODTable.setItems(fODList);
		this.fODTable.setEditable(false);
		
		this.fODTable.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7);
		this.fODTable.getColumns().forEach(c -> c.prefWidthProperty().bind(this.fODTable.widthProperty().divide(7)));
		
		this.fODTable.autosize();
		
		this.vBox = new VBox();
		
		this.nextB = new Button("NEXT");
		this.nextB.setOnAction(e -> {
			this.index++;
			this.buildSecondScene(controller);
			this.setScene(secondScene);
		});
		if(this.index +1 == this.dietToView.get().getMealList().size()){
			this.nextB.setVisible(false);
		}
		StackPane sp1 = new StackPane();
		sp1.getChildren().add(nextB);
		
		this.prevB = new Button("PREV");
		this.prevB.setOnAction(e -> {
			this.index--;
			this.buildSecondScene(controller);
			this.setScene(secondScene);
		});
		if(this.index == 0){
			this.prevB.setVisible(false);
		}
		StackPane sp2 = new StackPane();
		sp2.getChildren().add(prevB);
		
		layout.setCenter(vBox);
		vBox.getChildren().add(secondTitle);
		vBox.getChildren().add(fODTable);
		vBox.getChildren().add(sp1);
		vBox.getChildren().add(sp2);
		
		this.secondScene = new Scene(layout, DietCreator.WIDTH + 50, 200);
		
		
	}
	
}
