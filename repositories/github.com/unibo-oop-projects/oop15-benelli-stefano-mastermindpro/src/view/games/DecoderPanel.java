package view.games;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.GameController;
import model.games.Round;
import model.players.Decoder;

public class DecoderPanel extends JPanel {

	private class RoundButton extends JButton {

		private static final long serialVersionUID = 3283183815411574502L;
		public int roundId;
		
		public RoundButton(String text) {
			super(text);
		}
	}
	
	private static final long serialVersionUID = 1280865894025682374L;

	private JPanel[] panels;
	private RoundButton[] buttons;
	private ChoicesPanel[] choices;
	private HintsPanel[] hints;
	private final int decoderId;
	
	private GameController controller;
	
	public DecoderPanel(int decoderId) {
		this.decoderId = decoderId;
	}

	public void setController(GameController controller) {
		
		this.controller = controller;
		
		for(int i=0; i<choices.length; i++) {
			choices[i].setController(controller);
		}
	}
	
	public void initialize(Decoder decoder) {

		this.setLayout(new GridLayout(decoder.getRounds().length, 1));
		this.removeAll();

		this.choices = new ChoicesPanel[decoder.getRounds().length];
		this.hints = new HintsPanel[decoder.getRounds().length];
		this.buttons = new RoundButton[decoder.getRounds().length];
		this.panels = new JPanel[decoder.getRounds().length];
		
		for(int i=decoder.getRounds().length-1; i>=0; i--) {
			
			Round round = decoder.getRounds()[i];
			
			JPanel p = new JPanel(new BorderLayout());
			
			buttons[i] = new RoundButton("SUBMIT");
			buttons[i].setContentAreaFilled(false);
			buttons[i].setBorderPainted(false);
			buttons[i].roundId = i;
			buttons[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					RoundButton b = ((RoundButton)arg0.getSource());
					controller.submitRound(decoderId, b.roundId);
				}
				
			});
			
			choices[i] = new ChoicesPanel(this.decoderId, i);
			choices[i].initialize(round.getChoices().length);
			
			hints[i] = new HintsPanel();
			hints[i].initialize(round.getHints().length);

			p.add(choices[i], BorderLayout.CENTER);
			p.add(hints[i], BorderLayout.SOUTH);
			
			panels[i] = new JPanel(new BorderLayout());
			panels[i].add(buttons[i], BorderLayout.EAST);
			panels[i].add(p, BorderLayout.CENTER);
			
			this.add(panels[i]);
		}
	}
	
	public void update(Decoder decoder) {

		if(decoder.getCodeFound()) {
			String displayName  = decoder.getDisplayName() + " - GAME WON IN " + decoder.getRoundsSubmitted() + " ROUNDS!";
			this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED), displayName));
		} else {
			this.setBorder(BorderFactory.createTitledBorder(decoder.getDisplayName()));
		}
		
		for(int i=0; i<decoder.getRounds().length; i++) {
			Round round = decoder.getRounds()[i];
			boolean enabled = round.getIsValid() && decoder.getIsActiveRound(round);
			
			if(decoder.getIsActiveRound(round)) {
				panels[i].setBorder(BorderFactory.createLineBorder(Color.ORANGE));
			} else {
				panels[i].setBorder(BorderFactory.createEmptyBorder());
			}
			
			buttons[i].setEnabled(enabled);
			
			choices[i].update(round.getChoices());
			hints[i].update(round.getHints());

			if(round.getIsCodeFound()) {
				choices[i].setBackground(Color.YELLOW);
				hints[i].setBackground(Color.YELLOW);
				panels[i].setBackground(Color.YELLOW);
			}
		}
	}
}