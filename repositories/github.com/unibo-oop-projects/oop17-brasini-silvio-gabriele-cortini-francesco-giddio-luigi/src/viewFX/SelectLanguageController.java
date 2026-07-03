package viewFX;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;

public class SelectLanguageController extends AnchorPane implements Initializable {
	
	@FXML
	private ChoiceBox chbLanguage;
	
	@FXML
	private Button btnOKLanguage;
	
	@FXML
	private Label lblLanguage;
	
	final private ObservableList<String> language = FXCollections.observableArrayList("English","Italiano","Français","Pусский");
	final private String[] lang = new String[] {"en_US","it_IT","fr_FR","ru_RU"};

	private MainApp mainApp;
	
	
	@Override
    public void initialize(URL location, ResourceBundle resources) {
        chbLanguage.setItems(language);
        chbLanguage.setTooltip(new Tooltip("Select the language"));
    }

	public void setApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
	}
	
	public void selectLanguage (ActionEvent event) {
		mainApp.setLanguage(lang[chbLanguage.getSelectionModel().getSelectedIndex()]);
	}


}
