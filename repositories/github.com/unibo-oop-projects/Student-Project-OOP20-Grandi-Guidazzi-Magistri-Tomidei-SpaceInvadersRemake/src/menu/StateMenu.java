package menu;

import java.awt.BorderLayout;


import javax.swing.BoxLayout;
import javax.swing.JPanel;

import menu.factories.LabelFactory;
import menu.factories.PanelBackgroundFactory;
import menu.factories.TitleFactory;
import util.Constants;
import util.Strings;

/**
 *	A class that contains all the object to create the StateMenu
 */
public class StateMenu implements State{

	private LabelFactory labelFactory = new LabelFactory();
	private TitleFactory titleFactory = new TitleFactory();
	private JPanel panel = new PanelBackgroundFactory(Strings.BackgroundImages.PANEL_BACKGROUND);
	private JPanel centerPanel = new JPanel();
	
	/**
	 * The constructor of the StateMenu,
	 * this state is showed at the start of the application or when the button Go back to menu is pressed.
	 * @param board
	 */
	public StateMenu(Board board) {
		this.panel.setOpaque(false);
		this.panel.setLayout(new BorderLayout());
		this.panel.add(this.centerPanel, BorderLayout.CENTER);
		this.centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
		this.centerPanel.setOpaque(false);
	 
		this.panel.add(this.titleFactory.createTitle("Welcome to Space Invaders Remake", Constants.ObjectSize.titleSize, Constants.Colors.colorTitle), BorderLayout.NORTH);
		
		this.centerPanel.add(this.labelFactory.createButton(Strings.States.START, board, "Center"));
		this.centerPanel.add(this.labelFactory.createButton(Strings.States.OPTIONS, board, "Center"));
		this.centerPanel.add(this.labelFactory.createButton(Strings.States.CREDITS, board, "Center"));
		this.centerPanel.add(this.labelFactory.createButton(Strings.States.ABOUT, board, "Center"));
		this.centerPanel.add(this.labelFactory.createButton(Strings.States.EXIT, board, "Center"));
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JPanel getMainPanel() {
		return this.panel;
	}
}
