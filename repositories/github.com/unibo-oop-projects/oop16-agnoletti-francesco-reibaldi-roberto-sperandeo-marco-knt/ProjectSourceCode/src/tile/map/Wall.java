package tile.map;

import java.awt.Graphics;

import tile.Tile;
import knight.Game;
import knight.Handler;
import knight.Id;

public class Wall extends Tile {

	public Wall(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);

	}

	/**
	 * Disegno la "piastrella" muro caricando la relativa immagine
	 *
	 * @param Graphics g
	 */
	public void render(Graphics g) {
		g.drawImage(Game.wall.getBufferedImage(), x, y, width, height, null);

	}

	public void tick() {
		// non usata perchè il muro un volta disegnato non fa niente.
	}

}
