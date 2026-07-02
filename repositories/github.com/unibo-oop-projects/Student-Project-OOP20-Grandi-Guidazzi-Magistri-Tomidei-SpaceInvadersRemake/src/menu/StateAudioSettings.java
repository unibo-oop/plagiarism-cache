package menu;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import menu.factories.PanelFactory;
import menu.factories.SliderFactory;
import menu.factories.TitleFactory;
import util.Constants;
import util.Strings;

/**
 *	A class that contains all the objects to create the StateAudioSettings
 */
public class StateAudioSettings implements State{

	private JPanel panel;
	private JPanel centerPanel = new JPanel();
	private TitleFactory titleFactory = new TitleFactory();
	private PanelFactory panelFactory = new PanelFactory();
	private SliderFactory sliderFactory = new SliderFactory();
	
	/**
	 * The constructor of the StateAudioSettings,
	 * this state is showed when the button Audio settings is pressed.
	 * @param board
	 */
	public StateAudioSettings(Board board) {
		
		this.panel = this.panelFactory.createPanel(Strings.States.AUDIO_SETTINGS, board);
		this.panel.add(this.centerPanel, BorderLayout.CENTER);
	
		this.centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
		this.centerPanel.setOpaque(false);
		this.centerPanel.add(this.titleFactory.createTitle("Choose an audio intensity:", Constants.ObjectSize.subtitleSize, Constants.Colors.colorSubtitle), BorderLayout.CENTER);
		
		this.centerPanel.add(this.sliderFactory.create(board));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public JPanel getMainPanel() {
		return this.panel;
	}

}
