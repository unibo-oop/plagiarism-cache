package graphics.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import knight.Game;

public class Info {

	public Button button;
	private BufferedImage image;

	public Info() {
		button = new Button(700, 600, 300, 100, "Back");

		try {
			image = ImageIO.read(getClass().getResource("/launcher.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Disegno la schermata delle info per visualizzare comandi e informazioni
	 *
	 * @param Graphics g
	 */
	public void render(Graphics g, Graphics g2) {
		g.drawImage(image, 0, 0, Game.getFrameWidth(), Game.getFrameHEIGHT(), null);
		g.setColor(Color.white);
		g.setFont(new Font("courier", Font.BOLD, 35));
		g2.setColor(Color.white);
		g2.setFont(new Font("courier", Font.BOLD, 22));
		g.drawString("COMANDI:", 100, 100);
		g2.drawString("'A' cammina a sinistra;", 100, 150);
		g2.drawString("'D' cammina a destra;", 100, 190);
		g2.drawString("'W' salta;", 100, 230);
		g2.drawString("'K' attacco.", 100, 270);
		g.drawString("INFO:", 600, 100);
		g.drawImage(Game.firstGhostDx.getBufferedImage(), 600, 120, 35, 35, null);
		g2.drawString("una vita, veloce;", 650, 150);
		g.drawImage(Game.secondGhostDx.getBufferedImage(), 600, 170, 35, 35, null);
		g2.drawString("una vita, resuscita;", 650, 200);
		g.drawImage(Game.thirdGhostDx.getBufferedImage(), 600, 220, 35, 35, null);
		g2.drawString("due vite;", 650, 250);
		g.drawImage(Game.specialCrystal.getBufferedImage(), 600, 270, 35, 35, null);
		g2.drawString("una vita e cristalli bonus.", 650, 300);

		button.render(g);
	}
}
