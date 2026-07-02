package menu;

import java.awt.BorderLayout;


import javax.swing.BoxLayout;
import javax.swing.JPanel;

import menu.factories.LabelFactory;
import menu.factories.PanelFactory;
import util.Strings;

/**
 *	A class that contains all the object to create the StateOptions
 */
public class StateOptions implements State{

	private JPanel panel;
	private JPanel centerPanel = new JPanel();
	private PanelFactory panelFactory = new PanelFactory();
	private LabelFactory labelFactory = new LabelFactory();
	
	/**
	 * The constructor of the StateOptions,
	 * this state is showed when the button Options is pressed.
	 * @param board
	 */
	public StateOptions(Board board) {
		
		this.panel = this.panelFactory.createPanel(Strings.States.OPTIONS, board);
		this.panel.add(this.centerPanel, BorderLayout.CENTER);
		
		this.centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
		this.centerPanel.setOpaque(false);
		
		this.centerPanel.add(this.labelFactory.createButton(Strings.States.LEADERBOARD, board, "Center"));
		this.centerPanel.add(this.labelFactory.createButton(Strings.States.CHANGE_KEYS, board, "Center"));
		this.centerPanel.add(this.labelFactory.createButton(Strings.States.AUDIO_SETTINGS, board, "Center"));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public JPanel getMainPanel() {
		return this.panel;
	}

}
