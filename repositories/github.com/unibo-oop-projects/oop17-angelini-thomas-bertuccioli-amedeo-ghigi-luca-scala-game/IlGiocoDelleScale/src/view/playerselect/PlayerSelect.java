package view.playerselect;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import enumeration.Characters;

public class PlayerSelect implements Initializable {
	ObservableList<Characters> cha = FXCollections.observableArrayList(Arrays.asList(Characters.values()));
	@FXML
	public Button StartGame1;
	@FXML 
	public ChoiceBox<Characters> character;
	public void StartGame() {
		System.out.print(cha);
	}
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	 this.character.setItems(cha);
    	 this.character.setValue(Characters.Baghera);
    }
}