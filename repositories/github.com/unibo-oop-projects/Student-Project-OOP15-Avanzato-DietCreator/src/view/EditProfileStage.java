package view;

import controller.MainController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.DietCreator;
import model.Ectomorph;
import model.Endomorph;
import model.Mesomorph;
import model.Profile;

/**
 * classe EditProfileStage, permette di modificare i dati del profilo corrente
 * estende Stage
 *  
 */
public class EditProfileStage extends Stage{
	
	//the new profile
	private Profile profile;
	
	private GridPane grid;
    private Scene scene;	
	private Text title;
	private Label name;
	private Label maintainingKcals;
	private Label somatotype;
	private Label weight;
	private Label nameLabel;
	private TextField maintainingKcalsTF;
	private TextField weightTF;
	private ToggleGroup group;
	private RadioButton ectoRB;
	private RadioButton mesoRB;
	private RadioButton endoRB;
	
	private Button saveButton;
	
	/**
     * costruttore
     * 
     * @param controller
     *     riferimento al controller
     *  
     */
	public EditProfileStage(MainController controller){
		
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
        
        this.title = new Text("Edit Profile");
        this.title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.grid.add(title, 0, 0, 2, 1);
        
        this.name = new Label("Name:");
        grid.add(name, 0, 1);
        
        this.nameLabel = new Label(controller.getCurrentProfile().getName());
        grid.add(nameLabel, 1, 1);
        
        this.maintainingKcals = new Label("Maintaining KCals:");
        grid.add(maintainingKcals, 0, 2);
        
        this.maintainingKcalsTF = new TextField();
        this.maintainingKcalsTF.setText(controller.getCurrentProfile().getMaintainingKCals() + "");
        grid.add(maintainingKcalsTF, 1, 2);
        
        this.weight = new Label("Weight:");
        grid.add(weight, 0, 3);
        
        this.weightTF = new TextField();
        this.weightTF.setText(controller.getCurrentProfile().getWeight() + "");
        grid.add(weightTF, 1, 3);
        
        this.somatotype = new Label("Somatotype:");
        grid.add(somatotype, 0, 4);
        
        this.group = new ToggleGroup();
        this.ectoRB = new RadioButton("Ectomorph");
        if(controller.getCurrentProfile().getSomatotype().getName().equals("Ectomorph")){
            this.ectoRB.setSelected(true);
        }
        this.ectoRB.setToggleGroup(group);
        grid.add(ectoRB, 1, 4);
        this.mesoRB = new RadioButton("Mesomorph");
        if(controller.getCurrentProfile().getSomatotype().getName().equals("Mesomorph")){
            this.mesoRB.setSelected(true);
        }
        this.mesoRB.setToggleGroup(group);
        grid.add(mesoRB, 1, 5);
        this.endoRB = new RadioButton("Endomorph");
        if(controller.getCurrentProfile().getSomatotype().getName().equals("Endomorph")){
            this.endoRB.setSelected(true);
        }
        this.endoRB.setToggleGroup(group);
        grid.add(endoRB, 1, 6);
                
        this.saveButton = new Button("Save");
        this.saveButton.setOnAction(e -> {
            if(controller.checkEmptyField(this.weightTF.getText()) ||
                    controller.checkEmptyField(this.maintainingKcalsTF.getText())){
                controller.buildAlert("FILL");
            } else if(controller.checkValue(this.maintainingKcalsTF.getText()) ||
                    controller.checkValue(this.weightTF.getText())){
                 controller.buildAlert("Incorrect values!");
            } else {
                createProfile();
                try {
                    controller.editProfile(profile);
                    controller.changeScene(DietCreator.PROFILES);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                this.close();
            }
        });
        grid.add(saveButton, 1, 7);
        
        this.scene = new Scene(grid, 400, 300);
	    
	}
	
	/**
     * prende i dati dalla form e crea il nuovo profilo da passare poi al controller
     *  
     */
	private void createProfile(){
		String n = this.nameLabel.getText();
		double mKc = (double) Double.parseDouble(this.maintainingKcalsTF.getText());
		double weight = (double) Double.parseDouble(this.weightTF.getText());
		if(this.ectoRB.isSelected()){
			this.profile = new Profile(n, mKc, new Ectomorph(), weight);
		}
		if(this.mesoRB.isSelected()){
			this.profile = new Profile(n, mKc, new Mesomorph(), weight);
		}
		if(this.endoRB.isSelected()){
			this.profile = new Profile(n, mKc, new Endomorph(), weight);
		}
	}
}
