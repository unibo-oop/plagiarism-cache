package view;

import controller.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import main.DietCreator;
import model.FoodValues;

/**
 * classe FoodValuesScene, rappresenta tutti gli alimenti e i loro valori nutrizionali salvati per 100 g di alimento
 * estende Scene
 */
public class FoodValuesScene extends Scene{
	
	private static TableView<FoodValues> table;
	private static BorderPane layout;
	private static VBox vBox;
	
	private  static ObservableList<FoodValues> list;
	
	/**
     * costruttore
     * 
     * @param controller
     *     riferimento al controller
     *  
     * @param root
     * 
     * @param width
     *      la larghezza
     * 
     * @param height
     *      l'altezza
     */
	public FoodValuesScene(MainController controller, Parent root, double width, double height) throws Exception {
		super(init(controller), width, height);
		
		vBox.getChildren().add(new TitleItem("Foods Values", false, controller));
		vBox.getChildren().add(table);
		vBox.getChildren().add(new MenuItem(DietCreator.ADDFOOD, controller));
		vBox.getChildren().add(new MenuItem(DietCreator.EDITFOOD, controller));
		vBox.getChildren().add(new MenuItem(DietCreator.DELETEFOOD, controller));
		vBox.getChildren().add(new MenuItem(DietCreator.MENU, controller));
	}
	
	/**
     * metodo che inizializza il layout della scene
     * 
     * @param controller
     *     riferimento al controller
     *  
     */
	@SuppressWarnings("unchecked")
    private static BorderPane init(MainController controller){
		layout = new BorderPane();

		list =  FXCollections.observableArrayList(controller.getFVList());
		list.forEach(p -> System.out.println(p.getName()));
				
		//the columns
		TableColumn<FoodValues, String> column1 = new TableColumn<>("Name");
		column1.setCellValueFactory(new PropertyValueFactory<FoodValues, String>("name"));
		
		TableColumn<FoodValues, Double> column2 = new TableColumn<>("Carbohydrates");
		column2.setCellValueFactory(new PropertyValueFactory<FoodValues, Double>("carbs"));	
		
		TableColumn<FoodValues, Double> column3 = new TableColumn<>("Fats");
		column3.setCellValueFactory(new PropertyValueFactory<FoodValues, Double>("fats"));
		
		TableColumn<FoodValues, Double> column4 = new TableColumn<>("Proteins");
		column4.setCellValueFactory(new PropertyValueFactory<FoodValues, Double>("prots"));
		
		TableColumn<FoodValues, Double> column5 = new TableColumn<>("Fibers");
		column5.setCellValueFactory(new PropertyValueFactory<FoodValues, Double>("fibers"));
		
		TableColumn<FoodValues, Double> column6 = new TableColumn<>("Kcals");
		column6.setCellValueFactory(new PropertyValueFactory<FoodValues, Double>("totKcals"));

		table = new TableView<FoodValues>();
		table.setItems(list);
		table.setEditable(false);
		
				
		table.getColumns().addAll(column1, column2, column3, column4, column5, column6);
	    table.getColumns().forEach(c -> c.prefWidthProperty().bind(table.widthProperty().divide(6)));

		table.autosize();
		
		vBox = new VBox();
		
		layout.setCenter(vBox);
		
		return layout;
	}
}
