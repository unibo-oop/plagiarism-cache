package menu.gameview;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import menu.Board;
import menu.State;
import menu.factories.LabelFactory;
import menu.factories.PanelBackgroundFactory;
import menu.factories.TitleFactory;
import util.Constants;
import util.Strings;

/**
 * A class that contains the aspects of the menu in game.
 */
public class StateInGameMenu implements State{

	private LabelFactory labelFactory = new LabelFactory();
	private TitleFactory titleFactory = new TitleFactory();
	private JPanel panel = new PanelBackgroundFactory(Strings.BackgroundImages.PANEL_BACKGROUND);
	private JPanel centerPanel = new JPanel();
	
	/*
	 * The costructor that create the Menu in game.
	 */
	public StateInGameMenu(Board board) {
		this.panel.setOpaque(false);
		this.panel.setLayout(new BorderLayout());
		this.panel.add(this.centerPanel, BorderLayout.CENTER);
		this.centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
		this.centerPanel.setOpaque(false);
		
		this.panel.add(this.titleFactory.createTitle("Game Menu", Constants.ObjectSize.titleSize, Constants.Colors.colorTitle), BorderLayout.NORTH);
		
		this.centerPanel.add(this.labelFactory.createButton(Strings.States.RESUME, board, "Center"));
		this.centerPanel.add(this.labelFactory.createButton(Strings.States.RESTART, board, "Center"));
		this.centerPanel.add(this.labelFactory.createButton(Strings.States.AUDIO_SETTINGS_IN_GAME, board, "Center"));
		this.centerPanel.add(this.labelFactory.createButton(Strings.States.GO_BACK_TO_MENU, board, "Center"));

		this.panel.addKeyListener(board.getController().getView());
		this.panel.setFocusable(true);
	}
	
	@Override
	public JPanel getMainPanel() {
		return this.panel;
	}

	
}
