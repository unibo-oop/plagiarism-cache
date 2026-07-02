package hollowmen.view.juls.buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import hollowmen.view.UtilitySingleton;

/**
 * The {@link PaintedButton} Class creates customized buttons.
 * It represents the button's type most used for the menus in the app. 
 * 
 * @author Juls
 */
public class PaintedButton extends TranslucentButton {
	
	private static final long serialVersionUID = -4128202845780333356L;
	private Image buttonBG;
	private Image buttonOver;
	private Image buttonNA;
		
	public PaintedButton(String text) {
		super(text);
		super.setPreferences();
		this.setForeground(Color.WHITE);
	}
	
	public void loadImages() {
		buttonBG = UtilitySingleton.getInstance().getStorage().get("pButton").getImage();
		buttonOver = UtilitySingleton.getInstance().getStorage().get("pButtonOver").getImage();
		buttonNA = UtilitySingleton.getInstance().getStorage().get("pButtonNA").getImage();
	}

	@Override
	public void paintComponent(Graphics g) {
		loadImages();
		g.drawImage(buttonBG, 0, 8, null);
		if(isOver) {
			g.drawImage(buttonOver, 0, 8, null);
		}
		if(!isAvailable) {
			g.drawImage(buttonNA, 0, 8, null);
		}
		super.paintComponent(g);
	}
}
