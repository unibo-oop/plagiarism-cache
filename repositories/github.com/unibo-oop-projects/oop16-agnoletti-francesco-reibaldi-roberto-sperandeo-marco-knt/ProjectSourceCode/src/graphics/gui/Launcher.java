package graphics.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import knight.Game;

public class Launcher {

	public Button[] buttons;
	private BufferedImage image;

	public Launcher() throws IOException {
		buttons = new Button[4];

		buttons[0] = new Button(400, 55, 300, 100, "Start Game");
		buttons[1] = new Button(400, 165, 300, 100, "Ranking");
		buttons[2] = new Button(400, 275, 300, 100, "Info");
		buttons[3] = new Button(400, 385, 300, 100, "Exit");

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
	public void render(Graphics g) {
		g.drawImage(image, 0, 0, Game.getFrameWidth(), Game.getFrameHEIGHT(),
				null);

		for (int i = 0; i < buttons.length; i++) {
			buttons[i].render(g);
		}
	}
}
