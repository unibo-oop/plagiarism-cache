package view.menus;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import view.BoardColorPalette;

/**
 * 
 * A simple class used to set the style of JButton
 *
 */
public class DecoreButtonMenu {
	
	private final Font fontForMain = new Font("Arial", Font.BOLD, 20);
	
	public void decoreThisButton(final JButton button) {
		
		button.setFont(fontForMain);
		button.setBackground(Color.WHITE);
		button.setForeground(Color.decode(BoardColorPalette.CARRIBEAN_GREEN.getHexRGB()));
		button.setOpaque(true);
	}

}
