package entity;

import java.awt.Graphics;

import entity.Entity;
import knight.Game;
import knight.Handler;
import knight.Id;

public class PowerUp extends Entity {

	public PowerUp(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);

	}

	/**
	 * Disegno il potenziamento, caricando la relativa immagine
	 *
	 * @param Graphics g
	 * 
	 */
	public void render(Graphics g) {
		g.drawImage(Game.powerUp.getBufferedImage(), x, y, width, height, null);
	}

	public void tick() {
		//non usato
	}

}
