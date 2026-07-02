package view;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import controller.MainController;
import controller.MainControllerImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.DietCreator;
import model.Meal;

/**
 * La classe estende Stage
 * 
 * 
 */
public class AddDietStage extends Stage{
	
	private List<Double> kcals;
	private ObservableList<Double> okcals;
	private String selTarget;
	
	//Scene1
	private Text firstTitle;
	private ToggleGroup group;
	private RadioButton loseRB;
	private RadioButton maintainRB;
	private RadioButton gainRB;
	private Button firstB;
	private GridPane firstGrid;
	private Scene firstScene;
	
	//Scene2
	private Text secondTitle;
	private ComboBox<Double> cBox;
	private Button secondB;
	private Button infoB;
	private GridPane secondGrid;
	private Scene secondScene;
	
	//Scene 3
	private TableView<Meal> table;
	private BorderPane layout = new BorderPane();
	private VBox vBox;
	private Button refreshB;
	private Button saveB;
	private ObservableList<Meal> list;
	private Scene thirdScene;
	
	//Help panel
	private GridPane gridHelp;
	private Text element;
	private Text actualValue;
	private Text targetValue;
	private Label carbs;
	private Label fats;
	private Label prots;
	private Label kc;
	private Label targetCarbs;
	private Label targetFats;
	private Label targetProts;
	private Label targetKcals;
	private Label gramsCarbs;
	private Label gramsFats;
	private Label gramsProts;
	private Label kcalsLabel;
	
	/**
	 * costruttore
	 * 
	 * @param controller
	 *     riferimento al controller
	 *  
	 */
	public AddDietStage(MainController controller){
		
		this.buildFirstScene(controller);
		this.setScene(firstScene);
		this.setResizable(false);
		this.show();
		
	}
	
	/**
	 * costruisce la prima scena
	 * 
	 * @param controller
	 *        riferimento al controller
	 * 
	 */
	private void buildFirstScene(MainController controller){
		this.firstGrid = new GridPane();
		
		this.firstGrid.setAlignment(Pos.CENTER);
		this.firstGrid.setHgap(10);
		this.firstGrid.setVgap(10);
		this.firstGrid.setPadding(new Insets(25, 25, 25, 25));
		
		this.firstTitle = new Text("What's your target?");
		this.firstTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		this.firstGrid.add(firstTitle, 0, 0, 2, 1);

		
		this.group = new ToggleGroup();
		this.loseRB = new RadioButton(DietCreator.LOSE);
		this.loseRB.setSelected(true);
		this.loseRB.setToggleGroup(group);
		this.firstGrid.add(loseRB, 0, 1);
		this.maintainRB = new RadioButton(DietCreator.MAINTAIN);
		this.maintainRB.setToggleGroup(group);
		this.firstGrid.add(maintainRB, 0, 2);
		this.gainRB = new RadioButton(DietCreator.GAIN);
		this.gainRB.setToggleGroup(group);
		this.firstGrid.add(gainRB, 0, 3);
		
		this.firstB = new Button("NEXT");
		this.firstB.setOnAction(e -> {
			if(this.loseRB.isSelected()){
				this.selTarget = loseRB.getText();
				this.buildSecondScene(controller);
				this.setScene(secondScene);
			}
			if(this.maintainRB.isSelected()){
				this.selTarget = maintainRB.getText();
				controller.dietTargetKcals(selTarget, 0);
				controller.addDiet();
				this.buildThirdScene(controller);
				this.setScene(thirdScene);
			}
			if(this.gainRB.isSelected()){
				this.selTarget = gainRB.getText();
				this.buildSecondScene(controller);
				this.setScene(secondScene);
			}
			System.out.println(selTarget + "target");
		});
		this.firstGrid.add(firstB, 1, 4);
		
		this.firstScene = new Scene(firstGrid, 250, 200);
	}
	
	/**
     * costruisce la seconda scena
     * 
     * @param controller
     *        riferimento al controller
     * 
     */
	private void buildSecondScene(MainController controller){
		this.secondGrid = new GridPane();
		this.secondGrid.setAlignment(Pos.CENTER);
		this.secondGrid.setHgap(10);
		this.secondGrid.setVgap(10);
		this.secondGrid.setPadding(new Insets(25, 25, 25, 25));
		
		if(this.selTarget == DietCreator.LOSE){
			this.secondTitle = new Text("How much kcals less?");
			this.secondTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			this.secondGrid.add(secondTitle, 0, 0, 2, 1);
		} 
		if(this.selTarget == DietCreator.GAIN){
			this.secondTitle = new Text("How much kcals extra?");
			this.secondTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			this.secondGrid.add(secondTitle, 0, 0, 2, 1);
		} 
		
		this.kcals = Arrays.asList(200.0, 400.0, 600.0, 800.0, 1000.0); 
		this.okcals = FXCollections.observableArrayList(kcals);
		this.cBox = new ComboBox<>();
		this.cBox.setItems(this.okcals);
		this.secondGrid.add(cBox, 0, 1);
		
		this.infoB = new Button("?");
        this.infoB.setOnAction(e -> {
            controller.buildAlert("To " + this.selTarget.toLowerCase() + " weight, your maintaining kcals need to change.");
        });
        this.secondGrid.add(infoB, 1, 1);
		
		this.secondB = new Button("NEXT");
		this.secondB.setOnAction(e -> {
			double diffKcals = (double)(this.cBox.getValue());
			controller.dietTargetKcals(selTarget, diffKcals);
			controller.addDiet();
			this.buildThirdScene(controller);
			this.setScene(thirdScene);
		});
		this.secondGrid.add(secondB, 1, 2);
		
		this.secondScene = new Scene(secondGrid, 250, 200);
	}
	
	/**
     * costruisce la terza scena
     * 
     * @param controller
     *        riferimento al controller
     * 
     */
	@SuppressWarnings("unchecked")
	private void buildThirdScene(MainController controller){
		this.layout = new BorderPane();
		
		this.list = FXCollections.observableArrayList(controller.getMList());
		
		//the columns
		TableColumn<Meal, Integer> column1 = new TableColumn<>("N°");
		column1.setCellValueFactory(new PropertyValueFactory<Meal, Integer>("number"));
		
		TableColumn<Meal, Double> column2 = new TableColumn<>("Carbohydrates");
		column2.setCellValueFactory(new PropertyValueFactory<Meal, Double>("totCarbs"));	
		
		TableColumn<Meal, Double> column3 = new TableColumn<>("Fats");
		column3.setCellValueFactory(new PropertyValueFactory<Meal, Double>("totFats"));
		
		TableColumn<Meal, Double> column4 = new TableColumn<>("Proteins");
		column4.setCellValueFactory(new PropertyValueFactory<Meal, Double>("totProts"));
		
		TableColumn<Meal, Double> column5 = new TableColumn<>("Fibers");
		column5.setCellValueFactory(new PropertyValueFactory<Meal, Double>("totFibers"));
		
		TableColumn<Meal, Double> column6 = new TableColumn<>("Kcals");
		column6.setCellValueFactory(new PropertyValueFactory<Meal, Double>("totKcals"));

		this.table = new TableView<Meal>();
		this.table.setItems(list);
		this.table.setEditable(false);
		
				
		this.table.getColumns().addAll(column1, column2, column3, column4, column5, column6);
	    this.table.getColumns().forEach(c -> c.prefWidthProperty().bind(this.table.widthProperty().divide(6)));

		this.table.autosize();
		
		this.vBox = new VBox();
		
		this.layout.setCenter(vBox);

		this.refreshB = new Button("REFRESH");
		this.refreshB.setOnAction(e -> {
			this.buildThirdScene(controller);
			this.setScene(thirdScene);
		});
		StackPane sp1 = new StackPane();
		sp1.getChildren().add(refreshB);
		
		this.saveB = new Button("SAVE DIET");
		this.saveB.setOnAction(e -> {
			if(controller.checkEmptyField(controller.getNewDiet().getName())){
				controller.buildAlert("Insert a name for your diet");
			} else if(controller.checkIfPresent(controller.getNewDiet().getName(), DietCreator.DIETNAME)){
				controller.buildAlert(DietCreator.DIETNAME + DietCreator.TAKEN);
			} else if(controller.getNewDiet().getMealList().size() == 0){
				controller.buildAlert("This diet has no meals");
			} else {
				try {
					controller.saveDiet();
					controller.changeScene(DietCreator.DIETS);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				this.close();
			}
		});
		StackPane sp2 = new StackPane();
		sp2.getChildren().add(saveB);
		
		this.buildHelpPanel((MainControllerImpl) controller);
		
		this.vBox.getChildren().add(new TitleItem("New Diet", false, controller));
		this.vBox.getChildren().add(table);
		this.vBox.getChildren().add(this.gridHelp);
		this.vBox.getChildren().add(sp1);
		this.vBox.getChildren().add(sp2);
		
		this.thirdScene = new Scene(layout, DietCreator.WIDTH + 30, DietCreator.HEIGHT);
	}
	
	private void buildHelpPanel(MainControllerImpl controller) {
		
		this.gridHelp = new GridPane();
		this.gridHelp.setAlignment(Pos.CENTER);
		this.gridHelp.setHgap(10);
		this.gridHelp.setVgap(10);
		this.gridHelp.setPadding(new Insets(25, 25, 25, 25));
		
		this.element = new Text("Element");
		element.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		this.gridHelp.add(element, 0, 0);
		this.actualValue = new Text("Actual");
		actualValue.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		this.gridHelp.add(actualValue, 1, 0);
		this.targetValue = new Text("Target");
		targetValue.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		this.gridHelp.add(targetValue, 2, 0);
		
		this.carbs = new Label("Carbohydrates");
		this.gridHelp.add(carbs, 0, 1);
		this.fats = new Label("Fats");
		this.gridHelp.add(fats, 0, 2);
		this.prots = new Label("Proteins");
		this.gridHelp.add(prots, 0, 3);
		this.kc = new Label("Kcals");
		this.gridHelp.add(kc, 0, 4);
		
		this.carbs = new Label(round(controller.getNewDiet().getTotCarbs(), 2) + "");
		this.gridHelp.add(carbs, 1, 1);
		this.fats = new Label(round(controller.getNewDiet().getTotFats(), 2) + "");
		this.gridHelp.add(fats, 1, 2);
		this.prots = new Label(round(controller.getNewDiet().getTotProts(), 2) + "");
		this.gridHelp.add(prots, 1, 3);
		this.kc = new Label(round(controller.getNewDiet().getTotKcals(), 2) + "");
		this.gridHelp.add(kc, 1, 4);
		
		this.checkActualTargetValues(controller);
		
		this.targetCarbs = new Label(round(controller.getMinCarbs(), 2) + " - " + round(controller.getMaxCarbs(), 2));
		this.gridHelp.add(targetCarbs, 2, 1);
		this.targetFats = new Label(round(controller.getMinFats(),2) + " - " + round(controller.getMaxFats(), 2));
		this.gridHelp.add(targetFats, 2, 2);
		this.targetProts = new Label(round(controller.getMinProts(), 2) + " - " + round(controller.getMaxProts(), 2));
		this.gridHelp.add(targetProts, 2, 3);
		this.targetKcals = new Label(controller.getTargetKcals() + "");
		this.gridHelp.add(targetKcals, 2, 4);
		
		this.gramsCarbs = new Label("g");
		this.gridHelp.add(gramsCarbs, 3, 1);
		this.gramsFats = new Label("g");
		this.gridHelp.add(gramsFats, 3, 2);
		this.gramsProts = new Label("g");
		this.gridHelp.add(gramsProts, 3, 3);
		this.kcalsLabel = new Label("Kcals");
		this.gridHelp.add(kcalsLabel, 3, 4);
		
		this.gridHelp.add(new MenuItem(DietCreator.EDITDIET, controller), 5, 1);
		this.gridHelp.add(new MenuItem(DietCreator.ADDMEAL, controller), 5, 2);
		this.gridHelp.add(new MenuItem(DietCreator.DELETEMEAL, controller), 5, 3);
		
	}
	
	/**
     * controlla la rappresentazione dei valori attuali di una dieta
     * 
     * @param controller
     *        riferimento al controller
     * 
     */
	private void checkActualTargetValues(MainControllerImpl controller){
		
		if(controller.getNewDiet().getTotCarbs() < controller.getMinCarbs() || controller.getNewDiet().getTotCarbs() > controller.getMaxCarbs()){
			this.carbs.setTextFill(Color.RED);
		} else {
			this.carbs.setTextFill(Color.GREEN);
		}
		if(controller.getNewDiet().getTotFats() < controller.getMinFats() || controller.getNewDiet().getTotFats() > controller.getMaxFats()){
			this.fats.setTextFill(Color.RED);
		} else {
			this.fats.setTextFill(Color.GREEN);
		}
		if(controller.getNewDiet().getTotProts() < controller.getMinProts() || controller.getNewDiet().getTotProts() > controller.getMaxProts()){
			this.prots.setTextFill(Color.RED);
		} else {
			this.prots.setTextFill(Color.GREEN);
		}
		if(controller.getNewDiet().getTotKcals() < controller.getTargetKcals() -50 || controller.getNewDiet().getTotKcals() > controller.getTargetKcals() + 50) {
			this.kc.setTextFill(Color.RED);
		} else {
			this.kc.setTextFill(Color.GREEN);
		}
		
	}
	
	/**
     * limita il numero di cifre di un valore double
     * 
     * @param value
     *        il valore da arrotondare
     * 
     * @param decimal
     *        il numero di valori decimali mostrati
     */
	private static double round(double value, int decimal) {
	    if (decimal < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(decimal, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

}
