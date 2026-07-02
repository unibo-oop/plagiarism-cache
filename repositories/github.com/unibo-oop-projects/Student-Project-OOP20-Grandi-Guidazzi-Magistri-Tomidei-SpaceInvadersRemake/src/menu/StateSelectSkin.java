package menu;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import menu.factories.ButtonFactory;
import menu.factories.PanelBackgroundFactory;
import menu.factories.TitleFactory;
import util.Constants;
import util.Strings;
/**
 * A class that allow to choose the skin for player.
 */
public class StateSelectSkin implements State{

	private TitleFactory titleFactory = new TitleFactory();
	private JButton button = new JButton(Strings.Skins.RANDOM_SKIN);
	private JPanel panel = new PanelBackgroundFactory(Strings.BackgroundImages.BLACK_BACKGROUND);
	private JPanel centerPanel = new JPanel();
	private JPanel gridPanel = new JPanel();
	private JButton firstSkin;
	private JButton secondSkin;
	private JButton thirdSkin;
	private JButton fourthSkin;
	private Random random = new Random();
	
	/**
	 * The constructor of the StateSelectSkin,
	 * this state gives the possibility to choose the skin of the player
	 * @param board
	 */
	public StateSelectSkin(Board board) {
		this.firstSkin = new ButtonFactory().createSkinButton(Strings.Skins.SKIN_1, board);
		this.secondSkin = new ButtonFactory().createSkinButton(Strings.Skins.SKIN_2, board);
		this.thirdSkin = new ButtonFactory().createSkinButton(Strings.Skins.SKIN_3, board);
		this.fourthSkin = new ButtonFactory().createSkinButton(Strings.Skins.SKIN_4, board);
		this.panel.setOpaque(false);
		this.panel.setLayout(new BorderLayout());
		this.panel.add(this.centerPanel, BorderLayout.CENTER);
		this.centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
		this.centerPanel.setOpaque(false);
		
		this.panel.add(this.titleFactory.createTitle("Select your Skin:", Constants.ObjectSize.titleSize, Constants.Colors.colorTitle), BorderLayout.NORTH);
		
		this.gridPanel.setLayout(new GridLayout(0,Constants.LayoutDimension.specificColumns,0,Constants.LayoutDimension.specificRow));
		this.gridPanel.setOpaque(false);
		this.centerPanel.add(this.gridPanel, BorderLayout.CENTER);
		
		this.gridPanel.add(this.firstSkin);
		this.gridPanel.add(this.secondSkin);
		this.gridPanel.add(this.thirdSkin);
		this.gridPanel.add(this.fourthSkin);
		
		this.centerPanel.add(this.button);
		this.button.addActionListener(e->{
			board.setPlayerSkin(this.selectRandomSkin());
			
			JOptionPane.showMessageDialog(null, "Welcome to Space Invaders Remix!"
					+ "\n\nTHINGS TO KNOW:"
					+ "\n\n- Use left/right arrow keys to move\n- Press spacebar to shoot"
                    + "\n- BOSS after each level\n- Shoot enemies to collect points and achieve high scores, but don't miss any shot!"
                    + "\n- Press R to restart the game while you're in the game menu"
                    + "\n- Press ESC to pause the game"
                    + "\n- Press ESC to resume the game while you're in the game menu"
                    + "\n- Press S to stop the game while you're in the game menu"
                    + "\n- All pixel art is original\n- PLAY WITH SOUND\n\nHAVE FUN!");
			
			board.getMenuController().changeState(new StateGame(board, board.getPlayerSkin()));
			board.getController().startNewGame();
		});
		this.button.setAlignmentX(Component.CENTER_ALIGNMENT);
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public JPanel getMainPanel() {
		return this.panel;
	}
	
	/**
	 * The method select randomly one of the four playerImage. 
	 */
	private String selectRandomSkin() {
		List<String> playerImageList = new ArrayList<>();
		playerImageList.add(Strings.Skins.SKIN_1);
		playerImageList.add(Strings.Skins.SKIN_2);
		playerImageList.add(Strings.Skins.SKIN_3);
		playerImageList.add(Strings.Skins.SKIN_4);
		String chooseItem = playerImageList.get(this.random.nextInt(playerImageList.size()));
		return chooseItem;
	}

}
