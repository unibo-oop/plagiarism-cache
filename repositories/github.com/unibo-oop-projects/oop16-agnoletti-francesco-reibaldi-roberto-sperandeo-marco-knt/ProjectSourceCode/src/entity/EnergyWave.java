package entity;

import java.awt.Graphics;

import tile.Tile;
import entity.characters.*;
import knight.Game;
import knight.Handler;
import knight.Id;

public class EnergyWave extends Entity {

	int dir;

	public EnergyWave(int dir, int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
		this.dir = dir; // 0=sinistra; 1=destra

		if (dir == 0) {
			setVelX(-5);
		} else {
			setVelX(5);
		}
	}

	/**
	 * Disegno l'attacco, caricando la relativa immagine
	 *
	 * @param Graphics g
	 */
	public void render(Graphics g) {
		if (this.id == Id.energyWaveBoss) {
			if (dir == 0) {
				g.drawImage(Game.energyWaveBossSx.getBufferedImage(), x, y, width, height, null);
			} else {
				g.drawImage(Game.energyWaveBossDx.getBufferedImage(), x, y, width, height, null);
			}
		} else {
			g.drawImage(Game.energyWave.getBufferedImage(), x, y, width, height, null);
		}
	}

	/**
	 * Gestisco l'attacco nell'ambiente di gioco facendolo muovere
	 * orizzonatalmente 
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
				if (getBoundsLeft().intersects(t.getBounds())
						&& (t.getId() == Id.wall || t.getId() == Id.ground)) {
					die();
					Game.waveHit.play();
				}
				if (getBoundsRight().intersects(t.getBounds())) {
					die();
					Game.waveHit.play();
				}
				if (getBounds().intersects(t.getBounds())
						&& t.getId() == Id.crystal) {
					die();
					Game.waveHit.play();
				}
			}
		}

		for (int i = 0; i < handler.entity.size(); i++) {
			Entity e = handler.entity.get(i); // gestisce i tipi entity

			if (e.getId() == Id.powerUp) {
				if (getBounds().intersects(e.getBounds())) {
					die();
					Game.waveHit.play();
				}
			} else if (e.getId() == Id.firstGhost) { // mob 1
				if (getBounds().intersects(e.getBounds())) {
					e.die();
					die();
					Game.firstGhostKill++;
					Game.explode.play();
				}
			} else if (e.getId() == Id.secondGhost) { // mob 2 arancio rinasce
				if (getBounds().intersects(e.getBounds())) {
					e.die();
					die();
					Game.explode.play();
					Game.handler.addEntity(new SecondGhost(e.getXorig(), e.getYorig(), 64, 64, true, Id.secondGhost, Game.handler));
				}
			} else if (e.getId() == Id.thirdGhost) { // mob bordeaux 2 vite
				if (getBounds().intersects(e.getBounds())) {
					if (e.getLives() > 1) {
						e.hit();
						Game.waveHit.play();
					} else {
						e.die();
						Game.explode.play();
						Game.thirdGhostKill++;
					}
					die();
				}
			} else if (e.getId() == Id.firstBoss) {
				if (getBounds().intersects(e.getBounds())) {
					if (e.getLives() > 1) {
						e.hit();
						Game.explode.play();
					} else {
						e.die();
						Game.boss_1Kill = 1;
						Game.explode.play();
						Game.handler.addTile(new tile.obj.Crystal(e.getX(), e.getY(), 64, 64, true, Id.specialCystal, handler));
					}
					die();
				}
			} else if (e.getId() == Id.secondBoss) {
				if (getBounds().intersects(e.getBounds())) {
					if (e.getLives() > 1) {
						e.hit();
						Game.explode.play();
					} else {
						e.die();
						Game.boss_2Kill = 1;
						Game.explode.play();
						Game.handler.addTile(new tile.obj.Crystal(e.getX(), e.getY(), 64, 64, true, Id.specialCystal, handler));
					}
					die();
				}
			} else if (e.getId() == Id.player) {
				if (getBounds().intersects(e.getBounds())) {
					die();
					e.die();
					Game.playerHit.play();
				}
			} else if (e.getId() == Id.energyWaveBoss) {
				if (getBounds().intersects(e.getBounds())) {
					e.die();
					die();
					Game.waveHit.play();
				}
			}
		}

		if (falling) {
			gravity += 0.1;
			setVelY((int) gravity);
		}

	}
}
