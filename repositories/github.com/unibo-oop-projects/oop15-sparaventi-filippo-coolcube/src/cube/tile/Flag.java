package cube.tile;

import java.awt.Graphics;

import cube.Game;
import cube.Handler;
import cube.Id;

public class Flag extends Tile{

	public Flag(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);

	}
	
	/**
	 * Disegna la bandierina finale che permette il salto al livello successivo
	 * caricando la relativa immagine
	 *
	 * @param Graphics g
	 */
	public void render(Graphics g) {
		g.drawImage(Game.flag.getBufferedImage(), getX(), getY(), width, height, null);
	}
	
	public void tick() {
	}

}
