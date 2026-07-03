package entity.characters;

import java.awt.Graphics;

import tile.Tile;
import entity.Entity;
import knight.Game;
import knight.Handler;
import knight.Id;

public class Player extends Entity {

	public Player(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);

	}

	/**
	 * A seconda dello stato in cui mi trovo (fermo, in salto, in movimento)
	 * disegno il cavaliere caricando l'immagine adeguata
	 *
	 * @param Graphics g
	 * 
	 */
	public void render(Graphics g) {
		// cambio immagine quando salta o cade
		if ((getVelY() > 0 || getVelY() < 0) && (getVelX() >= 0)) {
			DX = true;
			SX = false;
			g.drawImage(Game.playerJumpDx.getBufferedImage(), x, y, width, height, null);
		} else if ((getVelY() > 0 || getVelY() < 0) && (getVelX() < 0)) {
			DX = false;
			SX = true;
			g.drawImage(Game.playerJumpSx.getBufferedImage(), x, y, width, height, null);
		} else if (getVelX() < 0) { // se va verso sx
			SX = true;
			DX = false;
			if ((getX() % (-8) == 0)) { // cambio immagine mentre scorre verso sinistra
				g.drawImage(Game.playerSx_1.getBufferedImage(), x, y, width, height, null);
			} else {
				g.drawImage(Game.playerSx_2.getBufferedImage(), x, y, width, height, null);
			}
		} else if (fire) { // cambio immagine quando spara
			if (DX) {
				g.drawImage(Game.playerFireDx.getBufferedImage(), x, y, width, height, null);
			} else if (SX) {
				g.drawImage(Game.playerFireSx.getBufferedImage(), x, y, width, height, null);
			}
		} else if (getVelX() > 0) { // se va verso destra
			SX = false;
			DX = true;
			if ((getX() % (8) == 0)) { // cambio immagine mentre scorre verso destra
				g.drawImage(Game.playerDx_1.getBufferedImage(), x, y, width, height, null);
			} else {
				g.drawImage(Game.playerDx_2.getBufferedImage(), x, y, width, height, null);
			}
		} else if (getVelX() == 0) {
			if (DX) {
				g.drawImage(Game.playerDx_1.getBufferedImage(), x, y, width, height, null);
			} else if (SX) {
				g.drawImage(Game.playerSx_1.getBufferedImage(), x, y, width, height, null);
			}
		}
	}

	/**
	 * Gestisce il cavaliere nel suo ambiente di gioco, gestendo i comportamenti
	 * nelle diverse condizioni in cui si possono trovare. Es: Morte se tocca il
	 * mostro.
	 *
	 */
	public void tick() {
		x += velX;
		y += velY;

		for (int i = 0; i < handler.tile.size(); i++) {
			Tile t = handler.tile.get(i); // gestisce tipi tile
			if (t.isSolid()) {
				if (getBoundsTop().intersects(t.getBounds())
						&& t.getId() == Id.wall) {
					setVelY(0);
					if (jumping) {
						jumping = false;
						gravity = 0.8;
						falling = true;
					}
				}
				if (getBoundsBottom().intersects(t.getBounds())
						&& (t.getId() == Id.wall || t.getId() == Id.ground)) {
					setVelY(0);
					if (falling)
						falling = false;
				} else if (!falling && !jumping) {
					falling = true;
					gravity = 0.8;
				}
				if (getBoundsLeft().intersects(t.getBounds())
						&& (t.getId() == Id.wall || t.getId() == Id.ground)) {
					setVelX(0);
					x = t.getX() + t.width;
				}
				if (getBoundsRight().intersects(t.getBounds())
						&& (t.getId() == Id.wall || t.getId() == Id.ground)) {
					setVelX(0);
					x = t.getX() - t.width;
				}
				if (getBounds().intersects(t.getBounds())
						&& (t.getId() == Id.crystal || t.getId() == Id.specialCystal)) {
					if (t.getId() == Id.crystal) {
						Game.crystalGet++;
					} else {
						Game.crystalGet += 5;
						Game.lives++;
					}
					Game.getObj.play();
					t.die();
				}

				if (getBounds().intersects(t.getBounds())
						&& (t.getId() == Id.door || t.getId() == Id.crown)) {
					if (t.getId() == Id.crown) {
						win();
						Game.themeTone.stop();
					} else {
						
						Game.switchLevel();
					}
				}
			}
		}

		for (int i = 0; i < handler.entity.size(); i++) {
			Entity e = handler.entity.get(i); // gestisce i tipi entity

			if (e.getId() == Id.powerUp) {
				if (getBounds().intersects(e.getBounds())) {
					this.powerUpGet = true;
					Game.getObj.play();
					e.die();
				}
			} else if ((e.getId() == Id.firstGhost)||(e.getId() == Id.secondGhost)||(e.getId() == Id.thirdGhost)
						||(e.getId() == Id.firstBoss)||(e.getId() == Id.secondBoss)) {
				if (getBounds().intersects(e.getBounds())) {
					Game.playerHit.play();
					die();
				} else if (e.getId() == Id.energyWave) {
					e.die();
				}
			}
		}

		if (jumping) {
			gravity -= 0.17;
			setVelY((int) -gravity);
			if (gravity <= 0.5) {
				jumping = false;
				falling = true;
			}
		}
		
		if (falling) {
			gravity += 0.17;
			setVelY((int) gravity);
		}
	}
}