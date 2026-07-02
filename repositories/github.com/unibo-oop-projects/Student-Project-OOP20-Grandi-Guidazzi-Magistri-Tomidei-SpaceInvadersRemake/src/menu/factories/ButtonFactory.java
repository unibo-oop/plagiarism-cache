package menu.factories;

import java.awt.Color;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import menu.Board;
import menu.StateGame;
import util.Constants;
/**
 * A class that makes buttons for choose the player image. 
 */
public class ButtonFactory {

	private JButton button = new JButton();
	private Image resizedImage;
	private Image image;
	
	/**
	 * The method create the button with the correct image associated. 
	 * @param skinUri, the string that is connected to the image.
	 * @param board, the main board
	 * @return
	 */
	public JButton createSkinButton(String skinUri, Board board) {
		try {
			this.image = ImageIO.read(ClassLoader.getSystemResource(skinUri));
			this.resizedImage = this.image.getScaledInstance(Constants.ObjectSize.imageDimension, Constants.ObjectSize.imageDimension, Image.SCALE_DEFAULT);
			button.setIcon(new ImageIcon(this.resizedImage));
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(board.getFrame(), "Update image error", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		this.button.setMaximumSize(new Dimension(Constants.ObjectSize.imageDimension, Constants.ObjectSize.imageDimension));
		this.button.setOpaque(false);
		this.button.setBackground(Color.black);
		this.button.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.button.addActionListener(e->{
			board.setPlayerSkin(skinUri);
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
		
		return this.button;
	}
}


