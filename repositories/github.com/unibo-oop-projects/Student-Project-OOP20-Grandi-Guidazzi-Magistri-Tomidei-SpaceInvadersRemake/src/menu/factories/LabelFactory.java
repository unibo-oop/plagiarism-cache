package menu.factories;

import java.awt.Color;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import menu.*;
import menu.gameview.StateAudioSettingsInGame;
import menu.gameview.StateInGameMenu;
import menuController.menuController;
import util.Audio;
import util.AudioImpl;
import util.AudioTrack;
//import util.AudioPlayer;
import util.Constants;
import util.Strings;

/**
 * A simple LabelFactory to avoid the repetitions.
*/
public class LabelFactory {
	
	private Audio audioPlayer = new AudioImpl();
	private menuController controller;
	
	/**
	 * Create a new label made from the input parameters and with action listeners.
	 * 
	 * @param buttonType
	 * @param board
	 * @param position
	 * @return the label built
	 */
	public JLabel createButton(String buttonType, Board board, String position) {
		this.controller = board.getMenuController();
		JLabel label = new JLabel(buttonType);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setMaximumSize(Constants.ObjectDimension.maxLabelDimension);
		label.setVisible(true);
		label.setForeground(Color.white);
		label.setFont(new Font("sans", Font.BOLD, 25));

		if(position.equals("Center")) {
			label.setHorizontalAlignment(SwingConstants.CENTER);
		}
		
		MouseAdapter mouseAdapter = new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
	            label.setForeground(Color.red);
	        }
			
	        public void mouseExited(MouseEvent e) {
	            label.setForeground(Color.white);
	        }
	        
	        public void mouseClicked(MouseEvent e) {
	        	audioPlayer.play(AudioTrack.BUTTON_PRESSED, Constants.AudioConstants.NOT_IN_LOOP);
	        	switch(label.getText()) {
	        	
	        		case Strings.States.START:
	        			controller.changeState(new StateSelectSkin(board));
	        			break;
	        			
	        		case Strings.States.EXIT:
	        			System.exit(0);
	        			break;
	        		
	        		case Strings.States.ABOUT:
	        			controller.changeState(new StateAbout(board));
	        			break;
	        		
	        		case Strings.States.OPTIONS:
	        			controller.changeState(new StateOptions(board));
	        			break;
	        		
	        		case Strings.States.CREDITS:
	        			controller.changeState(new StateCredits(board));
	        			break;
	        		
	        		case Strings.States.GO_BACK_TO_MENU:
	        			controller.changeState(new StateMenu(board));
	        			break;
	        			
	        		case Strings.States.RETURN_TO_GAME_MENU:
	        			controller.changeState(new StateInGameMenu(board));
	        			break;
	        		
	        		case Strings.States.LEADERBOARD:
	        			controller.changeState(new StateLeaderboard(board));
	        			break;
	        		
	        		case Strings.States.CHANGE_KEYS:
	        			controller.changeState(new StateChangeKeys(board));
	        			break;
	        		
	        		case Strings.States.AUDIO_SETTINGS:
	        			controller.changeState(new StateAudioSettings(board));
	        			break;
	        			
	        		case Strings.States.AUDIO_SETTINGS_IN_GAME:
	        			controller.changeState(new StateAudioSettingsInGame(board));
	        			break;
	       
	        		case Strings.States.RESTART:
	        			board.getController().getViewStatusManager().restart();
	        			controller.changeState(new StateGame(board));
	        			break;
	        			
	        		case Strings.States.RESUME: 
	        			board.getController().getViewStatusManager().resume();
	        			controller.changeState(new StateGame(board));
	        			break;
	        			
	        		case Strings.States.MORE_INFO:
	        			try {
							Desktop.getDesktop().browse(java.net.URI.create(Strings.Links.MORE_INFO_LINK));
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(board.getFrame(), "Link not found", "Error", JOptionPane.ERROR_MESSAGE);
						}
	        			break;
	        	}
			}
		};
		label.addMouseListener(mouseAdapter);
		label.addMouseMotionListener(mouseAdapter);
		return label;
	}
	
	
	
}
