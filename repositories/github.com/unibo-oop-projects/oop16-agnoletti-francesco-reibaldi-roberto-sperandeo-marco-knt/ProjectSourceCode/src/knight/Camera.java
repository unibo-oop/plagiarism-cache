package knight;

import entity.Entity;
import knight.Game;

public class Camera {

	public int x, y;

	/**
	 * gestisco il movimento della schermata di gioco a seconda della posizione
	 * del giocatore
	 *
	 * @param Entity player
	 */
	public void tick(Entity player) {
		setX(-player.getX() + Game.WIDTH * 2);
		setY(-player.getY() + Game.HEIGHT * 2);
	}

	/**
	 * Setta il valore di x
	 *
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Setta il valore di Y
	 *
	 * @param Y
	 */
	public void setY(int y) {
		this.y = y;
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
	 * restituisce la posizione y
	 * 
	 * @return this.y
	 */
	public int getY() {
		return y;
	}
}
