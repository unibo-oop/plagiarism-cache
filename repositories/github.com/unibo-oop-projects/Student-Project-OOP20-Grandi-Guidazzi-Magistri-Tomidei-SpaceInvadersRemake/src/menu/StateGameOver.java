package menu;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import menu.factories.LabelFactory;
import menu.factories.PanelBackgroundFactory;
import util.Strings;

/**
 *	A class that contains all the object to create the StateGameOver
 */
public class StateGameOver implements State{

	private PanelBackgroundFactory panel = new PanelBackgroundFactory(Strings.BackgroundImages.GAME_OVER_BACKGROUND);
	private LabelFactory labelFactory = new LabelFactory();
	
	/**
	 * The constructor of the StateGameOver,
	 * this state is showed when the player loses.
	 * @param board
	 */
	public StateGameOver(Board board) {
		this.panel.setLayout(new BorderLayout());
		this.panel.add(this.labelFactory.createButton(Strings.States.GO_BACK_TO_MENU, board, "Center"), BorderLayout.SOUTH);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public JPanel getMainPanel() {
		return this.panel;
	}

}
