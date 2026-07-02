package view.start;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.*;

import enumeration.Characters;
import enumeration.Dice;

public class Gui implements Initializable {
	ObservableList<Integer> num = FXCollections.observableArrayList(1,2,3,4);
	ObservableList<Integer> numdice = FXCollections.observableArrayList(1,2,3);
	ObservableList<Characters> cha = FXCollections.observableArrayList(Arrays.asList(Characters.values()));
	ObservableList<Dice> dic = FXCollections.observableArrayList(Arrays.asList(Dice.values()));
	List<Text> TextList=new ArrayList<>();
	List<ChoiceBox<Characters>> PawnList=new ArrayList<>();
	List<ChoiceBox<Dice>> DiceList=new ArrayList<>();

	@FXML
	private Text Text1;
	@FXML
	private Text TextP1;
	@FXML
	private Text TextP2;
	@FXML
	private Text TextP3;
	@FXML
	private Text TextP4;
	@FXML
	private Text PawnSelection;
	@FXML
	private Text DiceSelection;
	@FXML
	private ImageView Logo;
	@FXML
	private Button GoToPlayerSelect;
	@FXML 
	private ChoiceBox<Integer> numPlayer;
	@FXML
	private Button Exit;
	@FXML
	private Button Back;
	@FXML
	private Button GoToDiceSelect;
	@FXML
	private Button Update;
	@FXML
	private Button Update1;
	@FXML
	private Button StartGame;
	@FXML
	private ChoiceBox<Characters> PawnP1;
	@FXML
	private ChoiceBox<Characters> PawnP2;
	@FXML
	private ChoiceBox<Characters> PawnP3;
	@FXML
	private ChoiceBox<Characters> PawnP4;
	@FXML
	private ChoiceBox<Integer> DiceNumber;
	@FXML
	private ChoiceBox<Dice> Dice1;
	@FXML
	private ChoiceBox<Dice> Dice2;
	@FXML
	private ChoiceBox<Dice> Dice3;
	@FXML
	private TextField FaceN1;
	@FXML
	private TextField FaceN2;
	@FXML
	private TextField FaceN3;
	
	public void SelectPawns() {
		int numPlayers = (int) numPlayer.getValue();
		for (int i=0;i<numPlayers;i++) {
			switch (i) {
				case 0: this.TextList.add(TextP1);
						this.PawnList.add(PawnP1);
						break;
				case 1: this.TextList.add(TextP2);
						this.PawnList.add(PawnP2);
						break;
				case 2: this.TextList.add(TextP3);
						this.PawnList.add(PawnP3);
						break;
				case 3: this.TextList.add(TextP4);
						this.PawnList.add(PawnP4);
						break;
			}
		}
		System.out.println(this.TextList.size());
		this.TextList.forEach(e->e.setVisible(true));
		this.PawnList.forEach(e->e.setVisible(true));
		numPlayer.setVisible(false);
		GoToPlayerSelect.setVisible(false);
		Logo.setVisible(false);
		Text1.setVisible(false);
	 	GoToDiceSelect.setVisible(true);
		PawnSelection.setVisible(true);

		
	}
	public void Exit() {
		System.exit(0);
	}
	public void Update() {
		int numDices = (int) DiceNumber.getValue();
		System.out.println(numDices);
		Dice2.setVisible(false);
		Dice3.setVisible(false);
		FaceN1.setVisible(false);
		FaceN2.setVisible(false);
		FaceN3.setVisible(false);
		for (int i=0;i<numDices;i++) {
			switch (i) {
				case 0: this.DiceList.add(Dice1);
						this.Dice1.setVisible(true);
						break;
				case 1: this.DiceList.add(Dice2);
						this.Dice2.setVisible(true);
						break;
				case 2: this.DiceList.add(Dice3);
						this.Dice3.setVisible(true);
						break;
			}
		}
	}
	public void Update1() {
	Dice typeDice1 = Dice1.getValue();
	Dice typeDice2 = Dice2.getValue();
	Dice typeDice3 = Dice3.getValue();
	FaceN1.setVisible(false);
	FaceN2.setVisible(false);
	FaceN3.setVisible(false);

	if ((typeDice1.toString().equals("Multiface")) || (typeDice1.toString().equals("Total Personalized"))) {
		FaceN1.setVisible(true);
	}
	if ((typeDice2.toString().equals("Multiface")) || (typeDice2.toString().equals("Total Personalized"))) {
		FaceN2.setVisible(true);
	}
	if ((typeDice3.toString().equals("Multiface")) || (typeDice3.toString().equals("Total Personalized"))) {
		FaceN3.setVisible(true);
	}
	}
	public void Back() {
		numPlayer.setVisible(true);
		GoToPlayerSelect.setVisible(true);
		Logo.setVisible(true);
		Text1.setVisible(true);
		GoToDiceSelect.setVisible(false);
		PawnSelection.setVisible(false);
	 	this.TextList.forEach(e->e.setVisible(false));
	 	this.TextList.clear();
	 	this.PawnList.forEach(e->e.setVisible(false));
	 	this.PawnList.clear();
	 	this.DiceList.forEach(e->e.setVisible(false));
	 	this.DiceList.clear();
		DiceNumber.setVisible(false);
		Update.setVisible(false);
		Update1.setVisible(false);
		FaceN1.setVisible(false);
		FaceN2.setVisible(false);
		FaceN3.setVisible(false);
		StartGame.setVisible(false);
		DiceSelection.setVisible(false);
	}
	public void SelectDice(){
		
		this.TextList.forEach(e->e.setVisible(false));
		this.PawnList.forEach(e->e.setVisible(false));
		numPlayer.setVisible(false);
		GoToPlayerSelect.setVisible(false);
		Logo.setVisible(false);
		Text1.setVisible(false);
		PawnSelection.setVisible(false);
		DiceNumber.setVisible(true);
		Update.setVisible(true);
		Update1.setVisible(true);
		GoToDiceSelect.setVisible(false);
		StartGame.setVisible(true);
		DiceSelection.setVisible(true);

		}
	public void StartGame() {
		Characters pawnP1 = PawnP1.getValue();
		Characters pawnP2 = PawnP2.getValue();
		Characters pawnP3 = PawnP3.getValue();
		Characters pawnP4 = PawnP4.getValue();
		Dice typeDice1 = Dice1.getValue();
		Dice typeDice2 = Dice2.getValue();
		Dice typeDice3 = Dice3.getValue();
		int faceN1=Integer.parseInt(FaceN1.getText());
		System.out.println(faceN1);
		int faceN2=Integer.parseInt(FaceN2.getText());
		int faceN3=Integer.parseInt(FaceN3.getText());
		
	}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.numPlayer.setItems(num);
        this.numPlayer.setValue(1);
        this.PawnP1.setItems(cha);
   	 	this.PawnP1.setValue(Characters.Baghera);
   	 	this.PawnP2.setItems(cha);
	 	this.PawnP2.setValue(Characters.Baghera);
	 	this.PawnP3.setItems(cha);
   	 	this.PawnP3.setValue(Characters.Baghera);
   	 	this.PawnP4.setItems(cha);
	 	this.PawnP4.setValue(Characters.Baghera);
	 	this.DiceNumber.setItems(numdice);
	 	this.DiceNumber.setValue(1);
	 	this.Dice1.setItems(dic);
	 	this.Dice1.setValue(Dice.CLASSIC);
	 	this.Dice2.setItems(dic);
	 	this.Dice2.setValue(Dice.CLASSIC);
	 	this.Dice3.setItems(dic);
	 	this.Dice3.setValue(Dice.CLASSIC);

	 	TextP1.setVisible(false);
	 	TextP2.setVisible(false);
	 	TextP3.setVisible(false);
	 	TextP4.setVisible(false);
	 	PawnP1.setVisible(false);
	 	PawnP2.setVisible(false);
	 	PawnP3.setVisible(false);
	 	PawnP4.setVisible(false);
	 	GoToDiceSelect.setVisible(false);
		PawnSelection.setVisible(false);
		DiceNumber.setVisible(false);
		Dice1.setVisible(false);
		Dice2.setVisible(false);
		Dice3.setVisible(false);
		Update.setVisible(false);
		Update1.setVisible(false);
		StartGame.setVisible(false);
		FaceN1.setVisible(false);
		FaceN2.setVisible(false);
		FaceN3.setVisible(false);
		DiceSelection.setVisible(false);
    }

}