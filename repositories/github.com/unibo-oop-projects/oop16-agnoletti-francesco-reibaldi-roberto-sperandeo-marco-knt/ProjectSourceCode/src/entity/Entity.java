package entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import knight.Game;
import knight.Handler;
import knight.Id;

public abstract class Entity {

	public int x, y;
	public int xOrig, yOrig;
	public int width, height;
	public int lives;

	public boolean solid;

	public boolean powerUpGet = false;
	public boolean jumping = false;
	public boolean falling = true;
	public boolean fire = false;
	public boolean DX = true;
	public boolean SX = false;

	public int velX, velY;

	// fa riferimento alla classi ID
	public Id id;

	public double gravity = 0.0;

	public Handler handler;

	public Entity(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.solid = solid;
		this.id = id;
		this.handler = handler;
		this.xOrig = x;
		this.yOrig = y;

	}

	public abstract void render(Graphics g);

	public abstract void tick();

	/**
	 * Gestisce le morti del giocatore contando le vite rimaste. Durante
	 * l'evento die o gameover richiama il metodo per gli effetti
	 * sonori e fa apparire la schermata DeathScreen oppure GameOver.
	 *
	 */
	public void die() {
		handler.removeEntity(this);
		if (getId() == Id.player) {
			Game.lives--;
			handler.totCrystal = 0; // azzero il totale degli oggetti creati nel livello
			handler.totFirstGhost = 0;
			handler.totSecondGhost = 0;
			handler.totThirdGhost = 0;
			Game.crystalGet = 0; // azzero il totale degli oggetti trovati e nemici sconfitti
			Game.firstGhostKill = 0; 
			Game.thirdGhostKill = 0;
			Game.showDeathScreen = true;
			Game.beginGame = false;
			Game.playerHit.play();
			Game.themeTone.stop();
			if (Game.lives == 0) {
				Game.gameOverTone.play();
				Game.chrono.stopTime();
				Game.chrono.setTime();
				Game.totalPoints = (Game.totCrystalGet)+(Game.totGhostKill)+(Game.boss_1Kill)
						+ (Game.boss_2Kill);
				Game.rank.m.put((int) Game.totalPoints, Game.nome);
				Game.rank.write(Game.rank.m);
				Game.gameOver = true;
			}
		}
	}

	/**
	 * Gestisce la vittoria del giocatore, richiamando il relativo effetto
	 * sonoro e dando vita alla relativa schermata di win.
	 * 
	 */
	public void win() {
		Game.chrono.stopTime();
		Game.chrono.setTime();
		Game.totalPoints = (((Game.totCrystalGet*3)+(Game.totGhostKill*2)+(Game.boss_1Kill*4)+(Game.boss_2Kill*5)) 
				*Game.chrono.timeScore())*2;
		handler.removeEntity(this);
		if (getId() == Id.player) {
			Game.showDeathScreen = true;
			Game.gameOver = true;
			Game.winner = true;
			Game.themeTone.stop();
			Game.winnerTone.play();
			Game.rank.m.put((int) Game.totalPoints, Game.nome);
			Game.rank.write(Game.rank.m);
		}
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
	 * Setta il valore di Y
	 *
	 * @param Y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * setta il valore della velocità orizzontale
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
	 * Salva il valore della x al momento della creazione dell'entità
	 *
	 * @param
	 */
	public void setxOrig(int xOrig) {
		this.xOrig = xOrig;
	}

	/**
	 * Salva il valore della y al momento della creazione dell'entità
	 *
	 * @param
	 */
	public void setyOrig(int yOrig) {
		this.yOrig = yOrig;
	}

	public void hit() { // da mettere nel mob con due vite
		this.lives--;
	}

	public int getLives() {
		return lives;
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
	 * Restituisce il valore di Y
	 *
	 * @return this.y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Restituisce la posizione originale X dell'entità
	 *
	 * @return this.y
	 */
	public int getXorig() {
		return xOrig;
	}

	/**
	 * Restituisce la posizione originale X dell'entità
	 *
	 * @return this.y
	 */
	public int getYorig() {
		return yOrig;
	}

	/**
	 * restituisce la direzione e la velocità di x
	 * 
	 * @return this.x
	 */
	public int getVelX() {
		return velX;
	}

	/**
	 * restituisce la direzione e la velocità di y
	 * 
	 * @return this.x
	 */
	public int getVelY() {
		return velY;
	}

	/**
	 * Restituisce l'id dell'entità in questione
	 *
	 * @return id
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
	 * Restituisce un rettangolo, collision detection
	 *
	 * @return Rectangle(x, y, width, heigh)
	 */
	public Rectangle getBounds() { // per il collisiondetection
		return new Rectangle(x, y, width, height);
	}

	/**
	 * Restituisco un rettangolo corrispondente alle dimensioni del bordo
	 * superiore delle entità relative, collision detection
	 *
	 * @return Rectangle(getX()+10,getY(),width-20,5)
	 */
	public Rectangle getBoundsTop() {
		return new Rectangle(getX() + 10, getY(), width - 20, 5);
	}

	/**
	 * Restituisco un rettangolo corrispondente alle dimensioni del bordo
	 * inferiore delle entità relative, collision detection
	 *
	 * @return Rectangle(getX()+10,getY()+height-5,width-20,5);
	 */
	public Rectangle getBoundsBottom() {
		return new Rectangle(getX() + 10, getY() + height - 5, width - 20, 5);
	}

	/**
	 * Restituisco un rettangolo corrispondente alle dimensioni del bordo
	 * sinistro delle entità relative, collision detection
	 *
	 * @return Rectangle(getX(),getY()+10,5,height-20);
	 */
	public Rectangle getBoundsLeft() {
		return new Rectangle(getX(), getY() + 10, 5, height - 20);
	}

	/**
	 * Restituisco un rettangolo corrispondente alle dimensioni del bordo destro
	 * delle entità relative, collision detection
	 *
	 * @return Rectangle(getX()+width-5,getY()+10,5,height-20);
	 */
	public Rectangle getBoundsRight() {
		return new Rectangle(getX() + width - 5, getY() + 10, 5, height - 20);
	}
}