package view.end;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class EndGuiBaloo implements Initializable{

	@FXML
	private Button Exit;
	
	@FXML
	private Button Return;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.Exit.setVisible(true);
		this.Return.setVisible(true);
	}

	
	public void Exit() {
		System.exit(0);
	}
	
	public void Return() {
		System.out.println("Miao!\n");
	}
	
}
