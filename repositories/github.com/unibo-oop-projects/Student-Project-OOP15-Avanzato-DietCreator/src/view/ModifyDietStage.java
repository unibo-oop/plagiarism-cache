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
import model.Diet;

/**
 * classe ModifyDietStage, permette di selezionare ed aggiornare alcuni dati di una dieta precedentemente salvata
 * estende Stage
 *  
 */
public class ModifyDietStage extends Stage{
	
	//scene1
	private GridPane firstGrid;
    private Scene firstScene;
	private Text firstTitle;
	private Label diets;
	private ComboBox<String> dietsCB;
	private ObservableList<String> dietsOList;
	private Button nextB;
	private Optional<Diet> dietToModify;
	private Optional<Diet> modifiedDiet;;
	
	//scene2
	private GridPane secondGrid;
	private Text secondTitle;
	private Label name;
	private Label target;
	private Label targetLabel;
	private Label initWeight;
	private Label finWeight;
	private TextField nameTF;
	private Label initWeightLabel;
	private TextField finWeightTF;
	private Button saveButton;
	private Scene secondScene;
	
	/**
     * costruttore
     * 
     * @param controller
     *     riferimento al controller
     *  
     */
	public ModifyDietStage(MainController controller){
		
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
		
		this.firstTitle = new Text("Select Diet to modify");
		this.firstTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		this.firstGrid.add(firstTitle, 0, 0, 2, 1);
		
		this.diets = new Label("Diets:");
		firstGrid.add(diets, 0, 1);
		
		this.dietsCB = new ComboBox<String>();
		this.dietsCB.setItems(dietsOList);
		firstGrid.add(dietsCB, 1, 1);
		
		this.nextB = new Button("NEXT");
		this.nextB.setOnAction(e -> {
			this.dietToModify = controller.getCurrentProfile().getDiets().stream()
					.filter(d -> d.getName().equals(dietsCB.getValue()))
					.findFirst();
			this.modifiedDiet = controller.getCurrentProfile().getDiets().stream()
					.filter(d -> d.getName().equals(dietsCB.getValue()))
					.findFirst();
			this.buildSecondScene(controller);
			this.setScene(secondScene);
		});
		firstGrid.add(nextB, 1, 2);
		
		this.firstScene = new Scene(firstGrid, 220, 150);
		
	}
	
	/**
     * costruisce la seconda scene
     * 
     * @param controller
     *     riferimento al controller
     *  
     */
	private void buildSecondScene (MainController controller){
		
		this.secondGrid = new GridPane();
		
		this.secondGrid.setAlignment(Pos.CENTER);
		this.secondGrid.setHgap(10);
		this.secondGrid.setVgap(10);
		this.secondGrid.setPadding(new Insets(25, 25, 25, 25));
		
		this.secondTitle = new Text("Edit Diet");
		this.secondTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		this.secondGrid.add(secondTitle, 0, 0, 2, 1);
		
		this.name = new Label("Name:");
		secondGrid.add(name, 0, 1);
		
		this.nameTF = new TextField();
		this.nameTF.setText(this.dietToModify.get().getName());
		secondGrid.add(nameTF, 1, 1);
		
		this.target = new Label("Target:");
		secondGrid.add(target, 0, 2);
		
		this.targetLabel = new Label(this.dietToModify.get().getTarget() + " WEIGHT");
		secondGrid.add(targetLabel, 1, 2);
		
		this.initWeight = new Label("Initial Weight:");
		secondGrid.add(initWeight, 0, 3);
		
		this.initWeightLabel = new Label();
		this.initWeightLabel.setText(this.dietToModify.get().getInitialWeight() + "");
		secondGrid.add(initWeightLabel, 1, 3);
		
		this.finWeight = new Label("Final Weight:");
		secondGrid.add(finWeight, 0, 4);
		
		this.finWeightTF = new TextField();
		this.finWeightTF.setText(this.dietToModify.get().getFinalWeight() + "");
		secondGrid.add(finWeightTF, 1, 4);
				
		this.saveButton = new Button("Save");
		this.saveButton.setOnAction(e -> {
			if(controller.checkEmptyField(this.nameTF.getText()) || 
			        controller.checkEmptyField(this.finWeightTF.getText()) ||
			        controller.checkValue(this.finWeightTF.getText())){
				controller.buildAlert("Fill all fields");
			} else if(controller.checkIfPresent(this.nameTF.getText(), DietCreator.DIETNAME) &&
			        !this.dietToModify.get().getName().equals(this.nameTF.getText())){
				controller.buildAlert(DietCreator.DIETNAME + DietCreator.TAKEN);
			}else {
				try {
					this.modifiedDiet.get().setName(this.nameTF.getText());
					this.modifiedDiet.get().setFinalWeight(Double.parseDouble(this.finWeightTF.getText()));
					controller.modifyDiet(this.dietToModify.get(), this.modifiedDiet.get());
					controller.changeScene(DietCreator.DIETS);
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
