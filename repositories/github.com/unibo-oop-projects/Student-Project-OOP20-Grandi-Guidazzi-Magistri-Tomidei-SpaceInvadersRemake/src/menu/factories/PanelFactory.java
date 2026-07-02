package menu.factories;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import menu.Board;
import util.Constants;
import util.Strings;
/**
 * A simple JPanel factory to avoid repetitions.
 */
public class PanelFactory{
	
	private TitleFactory titleFactory = new TitleFactory();
	private LabelFactory labelFactory = new LabelFactory();
	private JPanel panel = new PanelBackgroundFactory(Strings.BackgroundImages.PANEL_BACKGROUND);
	
	/**
	 * Create a new JPanel with a standard background and a button inside in a standard position.
	 * 
	 * @param title
	 * @param board
	 * @return panel
	 */
	public JPanel createPanel(String title, Board board) {
		
		this.panel.setLayout(new BorderLayout());
		this.panel.add(this.titleFactory.createTitle(title, Constants.ObjectSize.titleSize, Constants.Colors.colorTitle), BorderLayout.NORTH);
		this.panel.add(this.labelFactory.createButton(Strings.States.GO_BACK_TO_MENU, board, "Left"), BorderLayout.SOUTH);
		
		return this.panel;
	}
	
	/**
	 * Create a new JPanel with black background and a button inside in a standard position.
	 * @param title
	 * @param board
	 * @return panel 
	 */
	public JPanel createBlackPanel(String title, Board board) {
		this.panel = new PanelBackgroundFactory(Strings.BackgroundImages.BLACK_BACKGROUND); 
		this.createPanel(title, board);
		return this.panel;
	}
}
