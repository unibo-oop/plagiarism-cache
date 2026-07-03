package cube.entity.powerup;

import java.awt.Graphics;
import java.util.Random;

import cube.Game;
import cube.Handler;
import cube.Id;
import cube.entity.Entity;
import cube.tile.Tile;

public class Mushroom extends Entity{
	
	private Random random = new Random();

	public Mushroom(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
		
		int dir = random.nextInt(2);
		
		switch(dir){
		case 0:
			setVelX(-3);
			break;
		case 1:
			setVelX(3);
			break;
		}
		
	}

	/**
	 * Disegno il potenziamento, caricando la relativa immagine
	 *
	 * @param Graphics g
	 * 
	 */
	public void render(Graphics g) {
		g.drawImage(Game.mushroom.getBufferedImage(), x, y, width, height, null);
	}

	/**
	 * Gestisco il potenziamento nell'ambiente di gioco. Facendolo muovere orizzonatalmente 
	 * e cambiando direzione nel momento in cui incontra un ostacolo.
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
					setVelX(3);
					
				}	
				if(getBoundsRight().intersects(t.getBounds())){
					setVelX(-3);
					
				}
			}
		}
		
		if(falling){
			gravity+=0.1;
			setVelY((int)gravity);
		}
		
	}

}
