package menu.gameview;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import menu.Board;
import menu.State;
import menu.factories.LabelFactory;
import menu.factories.PanelBackgroundFactory;
import menu.factories.SliderFactory;
import menu.factories.TitleFactory;
import util.Constants;
import util.Strings;

public class StateAudioSettingsInGame implements State{

	private JPanel panel = new PanelBackgroundFactory(Strings.BackgroundImages.PANEL_BACKGROUND);
	private JPanel centerPanel = new JPanel();
	private LabelFactory labelFactory = new LabelFactory();
	private TitleFactory titleFactory = new TitleFactory();
	private SliderFactory sliderFactory = new SliderFactory();
	
	/**
	 * The constructor of the StateAudioSettingsInGame,
	 * this state is showed when the button Audio settings is pressed.
	 * @param board
	 */
	public StateAudioSettingsInGame(Board board) {
		
		this.panel.setLayout(new BorderLayout());
		this.panel.setOpaque(false);
		this.panel.add(this.titleFactory.createTitle(Strings.States.AUDIO_SETTINGS, 
				Constants.ObjectSize.titleSize, Constants.Colors.colorTitle), BorderLayout.NORTH);
		this.panel.add(this.labelFactory.createButton(Strings.States.RETURN_TO_GAME_MENU, board, "Left"), BorderLayout.SOUTH);
		this.panel.add(this.centerPanel, BorderLayout.CENTER);
	
		this.centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
		this.centerPanel.setOpaque(false);
		this.centerPanel.add(this.titleFactory.createTitle("Choose an audio intensity:", 
				Constants.ObjectSize.subtitleSize, Constants.Colors.colorSubtitle), BorderLayout.CENTER);
		
		this.centerPanel.add(this.sliderFactory.create(board));
	}
	
	@Override
	public JPanel getMainPanel() {
		return this.panel;
	}

}
