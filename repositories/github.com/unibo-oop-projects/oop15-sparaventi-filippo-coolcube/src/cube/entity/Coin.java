package cube.entity;

import java.awt.Graphics;

import cube.Game;
import cube.Handler;
import cube.Id;
import cube.tile.Tile;

public class Coin extends Tile {

	public Coin(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
		
	}

	/**
	 * Disegno il coin (mela) nella posizione in cui voglio, caricando la relativa immagine
	 *
	 * @param Graphics g
	 * 
	 */
	public void render(Graphics g) {
		g.drawImage(Game.coin.getBufferedImage(), x, y, width, height, null);
	}

	
	public void tick() {
		//Vuota perchè la mela sta ferma, in attesa di essere presa.
	}

}
