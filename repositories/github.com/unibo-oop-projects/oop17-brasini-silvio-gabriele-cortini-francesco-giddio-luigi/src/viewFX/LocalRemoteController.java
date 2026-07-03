package viewFX;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LocalRemoteController extends AnchorPane implements Initializable {
	
	@FXML
	private Button btnOKLocalRemote;
	
	@FXML
	private Label lblLocalRemote;
	
	@FXML
	private RadioButton rbtLocal;
	
	@FXML
	private RadioButton rbtRemote;
	
	@FXML
	private RadioButton rbtPathDB;
	
	@FXML
	private TextField txfPathDB;
	
	@FXML
	private TextField txfRemote;
	
	private MainApp mainApp;
	
	@Override
    public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
	}

}
