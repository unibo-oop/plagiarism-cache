package menu;

import java.awt.BorderLayout;


import javax.swing.BoxLayout;
import javax.swing.JPanel;

import menu.factories.PanelFactory;
import menu.factories.TitleFactory;
import util.Constants;
import util.Strings;

/**
 *	A class that contains all the object to create the StateCredits
 */
public class StateCredits implements State{

	private JPanel panel;
	private JPanel centerPanel = new JPanel();
	private TitleFactory titleFactory = new TitleFactory();
	private PanelFactory panelFactory = new PanelFactory();
	
	/**
	 * The constructor of the StateCredits,
	 * this state is showed when the button Credits is pressed.
	 * @param board
	 */
	public StateCredits(Board board) {
		this.panel = this.panelFactory.createPanel(Strings.States.CREDITS, board);
		this.panel.add(this.centerPanel, BorderLayout.CENTER);
		
		this.centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
		this.centerPanel.setOpaque(false);		

		this.centerPanel.add(this.titleFactory.createTitle("Grandi Luca", Constants.ObjectSize.subtitleSize, Constants.Colors.colorSubtitle));
		this.centerPanel.add(this.titleFactory.createTitle("Guidazzi Federico", Constants.ObjectSize.subtitleSize, Constants.Colors.colorSubtitle));
		this.centerPanel.add(this.titleFactory.createTitle("Magistri Melissa", Constants.ObjectSize.subtitleSize, Constants.Colors.colorSubtitle));
		this.centerPanel.add(this.titleFactory.createTitle("Tomidei Luca", Constants.ObjectSize.subtitleSize, Constants.Colors.colorSubtitle));

		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public JPanel getMainPanel() {
		return this.panel;
	}

}
