package cube.tile;

import java.awt.Graphics;

import cube.Game;
import cube.Handler;
import cube.Id;

public class Wall extends Tile {

	public Wall(int x, int y, int width, int height, boolean solid, Id id,
			Handler handler) {
		super(x, y, width, height, solid, id, handler);
		
	}

	/**
	 * Disegno la "piastrella" muro caricando la reltiva immagine
	 *
	 * @param Graphics g
	 */
	public void render(Graphics g) {
		g.drawImage(Game.grass.getBufferedImage(), x, y, width, height, null);
		
	}

	public void tick() {
		//non usata perchè il muro un volta disegnato non fa niente.
	}

}
