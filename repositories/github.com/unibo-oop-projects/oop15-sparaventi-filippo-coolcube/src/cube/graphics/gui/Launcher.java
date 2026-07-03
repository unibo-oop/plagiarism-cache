package cube.graphics.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import cube.Game;

public class Launcher {
	
	public Button[] buttons;
	private BufferedImage image;
	
	public Launcher(){
		buttons = new Button[2];
		
		buttons[0] = new Button(400, 55, 300, 100, "Start Game");
		buttons[1] = new Button(400, 165, 300, 100, "Exit");
		
		try {
			image = ImageIO.read(getClass().getResource("/launcher.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Disegno la schermata di inizio prima del gioco vero e proprio
	 *
	 * @param Graphics g
	 */
	public void render(Graphics g){
		g.drawImage(image, 0, 0, Game.getFrameWidth(), Game.getFrameHEIGHT(), null);
		
		for(int i=0; i<buttons.length; i++){
			buttons[i].render(g);
		}	
	}
}
