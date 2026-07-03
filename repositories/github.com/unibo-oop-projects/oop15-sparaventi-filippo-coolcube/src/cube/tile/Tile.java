package cube.tile;

import java.awt.Graphics;
import java.awt.Rectangle;

import cube.Handler;
import cube.Id;


public abstract class Tile {

	public int x, y;
	public int width, height;
	
	public boolean solid;
	
	public int velX, velY;
	
	public Id id;
	
	public Handler handler;
	
	public Tile(int x, int y, int width, int height, boolean solid, Id id, Handler handler ){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.solid = solid;
		this.id = id;
		this.handler = handler;
		
	}
	
	public abstract void render(Graphics g);
	public abstract void tick();
	
	/**
	 * permette la rimozione della "piastrella" in questione
	 *
	 */
	public void die(){
		handler.removetile(this);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Id getId(){
		return id;
	}

	public boolean isSolid() {
		return solid;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	/**
	 * Ritorna un rettangolo delle dimensioni della "piastrella" in quesitone
	 *
	 * @return Rectangle(x, y, width, height);
	 */
	public Rectangle getBounds(){  //per il collisiondetection
		return new Rectangle(x, y, width, height);
	}
}
