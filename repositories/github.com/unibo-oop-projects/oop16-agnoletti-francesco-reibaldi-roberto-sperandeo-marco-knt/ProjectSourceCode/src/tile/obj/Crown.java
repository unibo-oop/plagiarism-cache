package tile.obj;

import java.awt.Graphics;

import tile.Tile;
import knight.Game;
import knight.Handler;
import knight.Id;

public class Crown extends Tile {

	public Crown(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
	}

	/**
	 * Disegna la corona finale che porta alla vittoria
	 * caricando la relativa immagine
	 *
	 * @param Graphics g
	 */
	public void render(Graphics g) {
		g.drawImage(Game.crown.getBufferedImage(), getX(), getY(), width, height, null);
	}

	public void tick() {

	}

}
