package view.games;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import controller.GameController;
import model.players.Encoder;

public class EncoderPanel extends JPanel {

	private static final long serialVersionUID = -7028993059897544250L;

	private final ChoicesPanel choicesPanel;
	//private Boolean codeHidden;
	
	public EncoderPanel() {
		
		//this.codeHidden = true;
		
		choicesPanel = new ChoicesPanel();
		this.setLayout(new BorderLayout());
		this.add(choicesPanel);
	}
	
	/*
	public void setCodeHidden(Boolean codeHidden) {
		this.codeHidden = codeHidden;
	}
	
	public Boolean getCodeHidden() {
		return this.codeHidden;
	}
	*/
	
	public void setController(GameController controller) {
		choicesPanel.setController(controller);
	}
	
	public void initialize(Encoder encoder) {
		this.setBorder(BorderFactory.createTitledBorder(encoder.getDisplayName()));
		choicesPanel.initialize(encoder.getSecretCode().length);
	}
	
	public void Update(Encoder encoder) {
		choicesPanel.update(encoder.getSecretCode(), encoder.getCodeHidden());
	}
}