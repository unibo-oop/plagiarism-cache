package tile;

import java.awt.Graphics;
import java.awt.Rectangle;

import knight.Handler;
import knight.Id;

public abstract class Tile {

	public int x, y;
	public int width, height;

	public boolean solid;

	public int velX, velY;

	public Id id;

	public Handler handler;

	public Tile(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
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
	 * permette la rimozione della "piastrella" trattata
	 *
	 */
	public void die() {
		handler.removetile(this);
	}

	/**
	 * setta il valore di X
	 *
	 * @param X
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * setta il valore di y
	 *
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Setta il valore della velocità orizzontale
	 *
	 * @param velX
	 */
	public void setVelX(int velX) {
		this.velX = velX;
	}

	/**
	 * Setta il valore della velocità verticale
	 *
	 * @param velY
	 */
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	/**
	 * restituisce la posizione X
	 * 
	 * @return this.x
	 */
	public int getX() {
		return x;
	}

	/**
	 * restituisce la posizione X
	 * 
	 * @return this.x
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * restituisce l'id relativo
	 * 
	 * @return this.x
	 */
	public Id getId() {
		return id;
	}

	/**
	 * Definisce se un elemento è un solido
	 *
	 * @return solid
	 */
	public boolean isSolid() {
		return solid;
	}
	
	/**
	 * Ritorna un rettangolo delle dimensioni della "piastrella" in quesitone
	 *
	 * @return Rectangle(x, y, width, height);
	 */
	public Rectangle getBounds() { // per il collisiondetection
		return new Rectangle(x, y, width, height);
	}
}
