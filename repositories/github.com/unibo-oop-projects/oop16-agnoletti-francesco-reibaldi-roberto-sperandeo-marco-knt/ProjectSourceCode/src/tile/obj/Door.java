package tile.obj;

import java.awt.Graphics;

import tile.Tile;
import knight.Game;
import knight.Handler;
import knight.Id;

public class Door extends Tile {

	public Door(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
	}

	/**
	 * Disegna la porta che permette il salto al livello successivo
	 * caricando la relativa immagine
	 *
	 * @param Graphics g
	 */
	public void render(Graphics g) {
		g.drawImage(Game.door.getBufferedImage(), getX(), getY(), width, height, null);
	}

	public void tick() {
		
	}

}
