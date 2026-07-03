package tile.map;

import java.awt.Graphics;

import tile.Tile;
import knight.Game;
import knight.Handler;
import knight.Id;

public class Ground extends Tile {

	public Ground(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);

	}

	/**
	 * Disegno la "piastrella" terreno caricando la relativa immagine
	 *
	 * @param Graphics g
	 */
	public void render(Graphics g) {
		g.drawImage(Game.ground.getBufferedImage(), x, y, width, height, null);
	}

	public void tick() {
		// non usata perchè il terreno un volta disegnato non fa niente.
	}

}
