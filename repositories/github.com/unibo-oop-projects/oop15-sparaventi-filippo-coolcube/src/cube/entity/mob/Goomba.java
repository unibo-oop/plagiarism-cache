package cube.entity.mob;

import java.awt.Graphics;
import java.util.Random;

import cube.Game;
import cube.Handler;
import cube.Id;
import cube.entity.Entity;
import cube.tile.Tile;

public class Goomba extends Entity {

	private Random random = new Random();
	
	public Goomba(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
		
		int dir = random.nextInt(2);
		
		switch(dir){
		case 0:
			setVelX(-5);
			break;
		case 1:
			setVelX(5);
			break;
		}
		
	}
	
	/**
	 * Disegno il cubo relativo al cattivo, caricando l'immagine relativa
	 *
	 * @param Graphics g
	 * 
	 */
	public void render(Graphics g) {
		g.drawImage(Game.goomba.getBufferedImage(), x, y, width, height, null);
	}

	/**
	 * Gestisto il nemico nel suo ambiente di gioco, facendolo muovere a destra e sinistra
	 * unico limite il muro, che gli permette di cambiare direzione
	 * e continuare così a muoversi
	 *
	 * 
	 */
	public void tick() {
		x+=velX;
		y+=velY;
		
		for(int i=0; i<handler.tile.size(); i++){
			Tile t = handler.tile.get(i);
			if(t.isSolid()){
				if(getBoundsBottom().intersects(t.getBounds())){
					setVelY(0);
					if(falling) falling = false;
 				}else if(!falling){
 						falling = true;
 						gravity = 0.8;
 				}
				if(getBoundsLeft().intersects(t.getBounds())){
					setVelX(5);
					
				}	
				if(getBoundsRight().intersects(t.getBounds())){
					setVelX(-5);
					
				}
			}
		}
		
		if(falling){
			gravity+=0.1;
			setVelY((int)gravity);
		}
		
	}

}
