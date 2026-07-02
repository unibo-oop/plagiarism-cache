package menu;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import menu.factories.LabelFactory;
import menu.factories.PanelFactory;
import util.Strings;

/**
 *	A class that contains all the object to create the stateAbout
 */
public class StateAbout implements State{

	private JPanel panel;
	private JPanel centerPanel = new JPanel();
	private PanelFactory panelFactory = new PanelFactory();
	private LabelFactory labelFactory = new LabelFactory();
	private JTextArea textArea = new JTextArea();
	
	/**
	 * The constructor of the state about,
	 * this state is showed when the button About the game... is pressed.
	 * @param board
	 */
	public StateAbout(Board board) {
		this.panel = this.panelFactory.createBlackPanel(Strings.States.ABOUT, board);
		this.textArea.setText(Strings.Texts.ABOUT_TEXT);
		this.textArea.setFont(new Font("sans", Font.BOLD, 25));
		this.textArea.setLineWrap(true);
		this.textArea.setWrapStyleWord(true);
		this.textArea.setOpaque(false);
		this.textArea.setEnabled(false);
		this.centerPanel.setOpaque(false);
		this.centerPanel.setLayout(new BorderLayout());
		this.panel.add(this.centerPanel, BorderLayout.CENTER);
		
		this.centerPanel.add(this.textArea, BorderLayout.NORTH);
		this.centerPanel.add(this.labelFactory.createButton(Strings.States.MORE_INFO, board, "Center"), BorderLayout.CENTER);
	
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public JPanel getMainPanel() {
		return this.panel;
	}

}
