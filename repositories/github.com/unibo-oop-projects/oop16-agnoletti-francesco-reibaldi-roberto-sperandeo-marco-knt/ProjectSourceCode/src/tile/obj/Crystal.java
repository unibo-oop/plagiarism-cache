package tile.obj;

import java.awt.Graphics;

import tile.Tile;
import knight.Game;
import knight.Handler;
import knight.Id;

public class Crystal extends Tile {

	public Crystal(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);

	}

	/**
	 * Disegno il cristallo nella posizione in cui voglio, caricando la relativa
	 * immagine
	 *
	 * @param Graphics g
	 * 
	 */
	public void render(Graphics g) {
		if (this.id == Id.crystal) {
			g.drawImage(Game.crystal.getBufferedImage(), x, y, width, height, null);
		} else {
			g.drawImage(Game.specialCrystal.getBufferedImage(), x, y, width, height, null);
		}
	}

	public void tick() {
		// Vuota perchè il cristallo sta fermo, in attesa di essere raccolto.
	}

}
