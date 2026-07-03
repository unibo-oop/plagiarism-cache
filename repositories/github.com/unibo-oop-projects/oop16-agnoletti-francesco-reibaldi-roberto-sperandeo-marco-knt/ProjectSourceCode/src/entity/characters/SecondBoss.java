package entity.characters;

import java.awt.Graphics;
import java.util.Random;

import tile.Tile;
import entity.Entity;
import entity.EnergyWave;
import knight.Game;
import knight.Handler;
import knight.Id;

public class SecondBoss extends Entity {

	private Random random = new Random();

	public SecondBoss(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);

		int dir = random.nextInt(2);
		this.lives = 3;

		switch (dir) {
		case 0:
			setVelX(-2);
			break;
		case 1:
			setVelX(2);
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
			g.drawImage(Game.secondBossSx.getBufferedImage(), x, y, width,
					height, null);
			if (getX()%(30)==0) {
				Game.handler.addEntity(new EnergyWave(0, getX()-70, getY(), 64, 64, true, Id.energyWaveBoss, handler));
			}
			if (getX()%192==0) {
				Game.handler.addEntity(new EnergyWave(1, getX()+70, getY(), 64, 64, true, Id.energyWaveBoss, handler));
			}
		} else {
			g.drawImage(Game.secondBossDx.getBufferedImage(), x, y, width, height, null);
			if (getX()%30 == 0) {
				Game.handler.addEntity(new EnergyWave(1, getX()+70, getY(), 64, 64, true, Id.energyWaveBoss, handler));
			}
			if (getX()%192==0) {
				Game.handler.addEntity(new EnergyWave(0, getX()-70, getY(), 64, 64, true, Id.energyWaveBoss, handler));
			}
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
					setVelX(2);
				}
				if (getBoundsRight().intersects(t.getBounds())) {
					setVelX(-2);
				}
			}
		}

		if (falling) {
			gravity += 0.1;
			setVelY((int) gravity);
		}
	}
}