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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import main.DietCreator;
import model.Profile;

/**
 * classe ProfilesScene, rappresenta tutte i profili salvati
 * estende Scene
 */
public class ProfilesScene extends Scene{
	
    private static StackPane tablePane;
	private static TableView<Profile> table;
	private static BorderPane layout = new BorderPane();
	private static VBox vBox;
	private static ObservableList<Profile> list;
	
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
	public ProfilesScene(MainController controller, Parent root, double width, double height) throws Exception {
		super(init(controller), width, height);	
		
		vBox.getChildren().add(new TitleItem("Profiles", true, controller));
		vBox.getChildren().add(tablePane);
		vBox.getChildren().add(new MenuItem(DietCreator.ADDPROFILE, controller));
		vBox.getChildren().add(new MenuItem(DietCreator.EDITPROFILE, controller));
		vBox.getChildren().add(new MenuItem(DietCreator.DELETEPROFILE, controller));
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

		list =  FXCollections.observableArrayList(controller.getPList());
		list.forEach(p -> System.out.println(p.getName()));
		
		tablePane = new StackPane();
		
		//the columns
		TableColumn<Profile, String> column1 = new TableColumn<>("Name");
		column1.setCellValueFactory(new PropertyValueFactory<Profile, String>("name"));
		
		TableColumn<Profile, Double> column2 = new TableColumn<>("Maintaining Kcals");
		column2.setCellValueFactory(new PropertyValueFactory<Profile, Double>("maintainingKCals"));	
		
		TableColumn<Profile, Double> column3 = new TableColumn<>("Kg");
		column3.setCellValueFactory(new PropertyValueFactory<Profile, Double>("weight"));
		
		TableColumn<Profile, String> column4 = new TableColumn<>("Somatotype");
		column4.setCellValueFactory(new PropertyValueFactory<Profile, String>("somatotypeName"));

		table = new TableView<Profile>();
		table.setItems(list);
		table.setEditable(false);
		
				
		table.getColumns().addAll(column1, column2, column3, column4);
	    table.getColumns().forEach(c -> c.prefWidthProperty().bind(table.widthProperty().divide(4)));

		tablePane.getChildren().add(table);
				
		vBox = new VBox();
		
		layout.setCenter(vBox);
		
		return layout;
		
	}
	
}
