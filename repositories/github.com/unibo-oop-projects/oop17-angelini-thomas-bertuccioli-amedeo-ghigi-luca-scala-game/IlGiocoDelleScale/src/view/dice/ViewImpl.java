package view.dice;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.dice.Dice;
import model.dice.MultifaceDice;

public class ViewImpl implements View{

	@FXML
	private Button button;
	@FXML
	private Label viewDice;
	@FXML
	private ImageView image;
	private Dice dice;
	
	@Override
	public void update() {
		
		this.dice.roll();
		this.viewDice.setText(String.valueOf(this.dice.viewNum()));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.viewDice.setText(String.valueOf(0));
		this.dice=new MultifaceDice(6);
		this.button.setVisible(true);
		this.viewDice.setVisible(true);
	}
	
	
	
}
