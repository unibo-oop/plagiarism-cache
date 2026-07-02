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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.DietCreator;
import model.Diet;

/**
 * classe DietsScene, rappresenta tutte le diete del profilo corrente
 * estende Scene
 */
public class DietsScene extends Scene{
	
	private static Text text;
	private static TableView<Diet> table;
	private static BorderPane layout = new BorderPane();
	private static VBox vBox;
	private static ObservableList<Diet> list;
	
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
	public DietsScene(MainController controller, Parent root, double width, double height) throws Exception {
		super(init(controller), width, height);	
		
		vBox.getChildren().add(new TitleItem("Diets", false, controller));
		vBox.getChildren().add(table);
		vBox.getChildren().add(new MenuItem(DietCreator.ADDDIET, controller));
		vBox.getChildren().add(new MenuItem(DietCreator.VIEWDIET, controller));
		vBox.getChildren().add(new MenuItem(DietCreator.MODIFYDIET, controller));
		vBox.getChildren().add(new MenuItem(DietCreator.DELETEDIET, controller));
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
    private static BorderPane init(MainController controller) throws Exception{
		
		layout = new BorderPane();

		list =  FXCollections.observableArrayList(controller.getCurrentProfile().getDiets());
		list.forEach(p -> System.out.println(p.getName()));
		
		text = new Text("Diets");
		text.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
		
		//the columns
		TableColumn<Diet, String> column1 = new TableColumn<>("Name");
		column1.setCellValueFactory(new PropertyValueFactory<Diet, String>("name"));
		
		TableColumn<Diet, Double> column2 = new TableColumn<>("Target");
		column2.setCellValueFactory(new PropertyValueFactory<Diet, Double>("target"));	
		
		TableColumn<Diet, Double> column3 = new TableColumn<>("Initial Weight");
		column3.setCellValueFactory(new PropertyValueFactory<Diet, Double>("initialWeight"));
		
		TableColumn<Diet, String> column4 = new TableColumn<>("Final Weight");
		column4.setCellValueFactory(new PropertyValueFactory<Diet, String>("finalWeight"));

		table = new TableView<Diet>();
		table.setItems(list);
		table.setEditable(false);
		
				
		table.getColumns().addAll(column1, column2, column3, column4);
	    table.getColumns().forEach(c -> c.prefWidthProperty().bind(table.widthProperty().divide(4)));

		table.autosize();
		
		vBox = new VBox();
		
		layout.setCenter(vBox);
		
		return layout;

	}
	
}
