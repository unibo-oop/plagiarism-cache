package view.games;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.GameController;
import model.games.Choice;
import view.images.ImageSetFactory;

public class ChoicesPanel extends JPanel {

	private static final long serialVersionUID = 517777326806540878L;
	
	private class ChoiceButton extends JButton {
		private static final long serialVersionUID = -5514095753852375730L;
		
		public int choiceId;
	}
	
	private GameController controller;
	private final Boolean allowClick;
	private final int decoderId;
	private final int roundId;	

	/*
	 * constructor for encoder: buttons are read only, click not enabled
	 */
	public ChoicesPanel() {
		this.decoderId=-1;
		this.roundId=-1;
		this.allowClick = false;
	}
	
	/*
	 * initialize method for decoder: buttons are enabled, parameters univocally specifies the choice
	 */
	public ChoicesPanel(int decoderId, int roundId) {
		this.decoderId=decoderId;
		this.roundId=roundId;
		this.allowClick=true;
	}
	
	public void setController(GameController controller) {
		this.controller = controller;
	}

	public void initialize(int codeLength) {
		
		this.removeAll();
		
		this.setLayout(new GridLayout(1,codeLength));
		
		for(int i=0; i<codeLength; i++) {
			this.add(createButton(i));
		}
	}
	
	private JButton createButton(int choiceId) {
		
		ChoiceButton button = new ChoiceButton();
		
		button.choiceId=choiceId;
		
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);

		if(allowClick) {		
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					int choiceId = ((ChoiceButton)arg0.getSource()).choiceId;
					controller.nextChoice(decoderId, roundId, choiceId);
				}

			});
		}
		return button;
	}
	
	public void update(Choice[] choices) { 
		this.update(choices, false);
	}
	
	public void update(Choice[] choices, Boolean hidden) { 
		for(int i=0; i<choices.length; i++) {			
			JButton button = (JButton)this.getComponents()[i];
			if(hidden) {
				button.setIcon(ImageSetFactory.getCurrentInstance().getQuestionMarkImage());
			} else {
				button.setIcon(ImageSetFactory.getCurrentInstance().getImage(choices[i]));
			}
		}
	}
}
