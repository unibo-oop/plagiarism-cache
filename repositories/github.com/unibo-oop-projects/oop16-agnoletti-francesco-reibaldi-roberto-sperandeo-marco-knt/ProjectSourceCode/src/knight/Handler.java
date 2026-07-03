package knight;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import tile.*;
import tile.map.*;
import tile.obj.*;
import entity.*;
import entity.characters.*;

public class Handler {

	public LinkedList<Entity> entity = new LinkedList<Entity>();
	public LinkedList<Tile> tile = new LinkedList<Tile>();
	public int totCrystal; 
	public int totFirstGhost;
	public int totSecondGhost;
	public int totThirdGhost;

	/**
	 * Disegno solo l'area visibile del livello
	 *
	 * @param Graphics g
	 */
	public void render(Graphics g) {
		for (int i = 0; i < entity.size(); i++) {
			Entity e = entity.get(i);
			if (Game.getVisibleArea()!=null	&& e.getBounds().intersects(Game.getVisibleArea())){
				e.render(g);
			}
		}
		for (int i = 0; i < tile.size(); i++) {
			Tile t = tile.get(i);
			if (Game.getVisibleArea()!=null	&& t.getBounds().intersects(Game.getVisibleArea())){
				t.render(g);
			}
		}
	}

	/**
	 * Gestisco l'area
	 *
	 */
	public void tick() {
		for (int i = 0; i < entity.size(); i++) {
			Entity e = entity.get(i);
			e.tick();
		}
		for (int i = 0; i < tile.size(); i++) {
			Tile t = tile.get(i);
			if (Game.getVisibleArea()!=null && t.getBounds().intersects(Game.getVisibleArea())){
				t.tick();
			}
		}
	}

	/**
	 * Aggiungo l'entità in questione
	 *
	 * @param Entity
	 *            e
	 */
	public void addEntity(Entity e) {
		entity.add(e);
	}

	/**
	 * Rimuovo l'entità in questione
	 *
	 * @param Entity
	 *            e
	 */
	public void removeEntity(Entity e) {
		entity.remove(e);
	}

	/**
	 * Aggiungo la piastrella in questione
	 *
	 * @param Tile
	 *            t
	 */
	public void addTile(Tile t) {
		tile.add(t);
	}

	/**
	 * Rimuovo la piastrella in questione
	 *
	 * @param Tile
	 *            t
	 */
	public void removetile(Tile t) {
		tile.remove(t);
	}

	/**
	 * Disegno il livello. Tutto ciò avviene caricando dai file l'immagine
	 * livello ed analizzandola. Dove ho i pixel neri ad esempio creo il muro.
	 * Dove ho il puxel rosso creo il potenziamento e così via con tutte le
	 * entità
	 *
	 * @param BufferedImage level
	 */
	public void createLevel(BufferedImage level) {
		int width = level.getWidth();
		int height = level.getHeight();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int pixel = level.getRGB(x, y);

				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 0 && green == 0 && blue == 0)
					addTile(new Ground(x * 64, y * 64, 64, 64, true, Id.ground, this));
				if (red == 64 && green == 64 && blue == 64)
					addTile(new Wall(x * 64, y * 64, 64, 64, true, Id.wall, this));
				if (red == 255 && green == 216 && blue == 0)
					addEntity(new Player(x * 64, y * 64, 64, 64, false, Id.player, this));
				if (red == 0 && green == 255 && blue == 255)
					addEntity(new PowerUp(x * 64, y * 64, 64, 64, true, Id.powerUp, this));
				if (red == 255 && green == 0 && blue == 0) {
					addEntity(new FirstGhost(x * 64, y * 64, 64, 64, true, 	Id.firstGhost, this));
					totFirstGhost++;
				}
				if (red == 255 && green == 106 && blue == 0) {
					addEntity(new SecondGhost(x * 64, y * 64, 64, 64, true, Id.secondGhost, this));
					totSecondGhost++;
				}
				if (red == 127 && green == 0 && blue == 0) {
					addEntity(new ThirdGhost(x * 64, y * 64, 64, 64, true, Id.thirdGhost, this));
					totThirdGhost++;
				}
				if (red == 178 && green == 0 && blue == 255) {
					addEntity(new FirstBoss(x * 64, y * 64, 64, 64, true, Id.firstBoss, this));

				}
				if (red == 255 && green == 0 && blue == 220)
					addEntity(new SecondBoss(x * 64, y * 64, 64, 64, true, Id.secondBoss, this));
				if (red == 0 && green == 38 && blue == 255) {
					addTile(new Crystal(x * 64, y * 64, 64, 64, true, Id.crystal, this));
					totCrystal++;
				}
				// cristallo rosso lo droppa il boss
				if (red == 0 && green == 255 && blue == 33)
					addTile(new Door(x * 64, y * 64, 64, 64, true, Id.door,	this));
				if (red == 76 && green == 255 && blue == 0)
					addTile(new Crown(x * 64, y * 64, 64, 64, true, Id.crown, this));
			}

		}
	}

	/**
	 * Pulisco il livello cancellando tutto
	 *
	 */
	public void clearLevel() {
		entity.clear();
		tile.clear();
	}
}