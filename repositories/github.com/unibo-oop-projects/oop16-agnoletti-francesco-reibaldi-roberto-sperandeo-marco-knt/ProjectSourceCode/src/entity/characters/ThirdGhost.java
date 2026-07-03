package entity.characters;

import java.awt.Graphics;
import java.util.Random;

import tile.Tile;
import entity.Entity;
import knight.Game;
import knight.Handler;
import knight.Id;

public class ThirdGhost extends Entity {

	private Random random = new Random();

	public ThirdGhost(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);

		int dir = random.nextInt(2);
		this.lives = 2;

		switch (dir) {
		case 0:
			setVelX(-4);
			break;
		case 1:
			setVelX(4);
			break;
		}
	}

	/**
	 * Disegno il mostro caricando la relativa l'immagine a seconda del
	 * movimento
	 *
	 * @param Graphics g
	 * 
	 */
	public void render(Graphics g) {
		if (getVelX() < 0) { // se va verso sx
			g.drawImage(Game.thirdGhostSx.getBufferedImage(), x, y, width, height, null);
		} else {
			g.drawImage(Game.thirdGhostDx.getBufferedImage(), x, y, width, height, null);
		}
	}

	/**
	 * Gestisto il nemico nel suo ambiente di gioco facendolo muovere a destra
	 * e sinistra, unico limite il muro che gli permette di cambiare direzione 
	 * 
	 */
	public void tick() {
		x += velX;
		y += velY;

		for (int i = 0; i < handler.tile.size(); i++) {
			Tile t = handler.tile.get(i);
			if (t.isSolid()) {
				if (getBoundsBottom().intersects(t.getBounds())) {
					setVelY(0);
					if (falling)
						falling = false;
				} else if (!falling) {
					falling = true;
					gravity = 0.8;
				}
				if (getBoundsLeft().intersects(t.getBounds())) {
					setVelX(4);
				}
				if (getBoundsRight().intersects(t.getBounds())) {
					setVelX(-4);
				}
			}
		}

		if (falling) {
			gravity += 0.1;
			setVelY((int) gravity);
		}
	}
}