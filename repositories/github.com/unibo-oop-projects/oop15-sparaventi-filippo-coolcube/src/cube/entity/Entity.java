package cube.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import cube.Game;
import cube.Handler;
import cube.Id;

public abstract class Entity {

	public int x, y;
	public int width, height;
	
	public boolean solid;
	
	public boolean jumping = false;
	public boolean falling = true;
	
	public int velX, velY;
	
	//fa riferimento alla classi ID
	public Id id;
	
	public double gravity = 0.0;
	
	public Handler handler;
	
	public Entity(int x, int y, int width, int height, boolean solid, Id id, Handler handler){
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
	 * Gestisce le morti del giocatore, contando le vite rimaste e durante l'evento 
	 * die o gameover che sia, richiama il metodo per gli effetti sonori e fa apparire
	 * la schermata DeathScreen oppure GameOver.
	 *
	 * .
	 */
	public void die(){
		handler.removeEntity(this);
		if(getId()==Id.player){
			Game.lives--;
			Game.showDeathScreen = true; 
			if(Game.lives <= 0) Game.gameOver = true;
			
			Game.themeTone.stop();
			
			if(Game.lives==0){
				Game.gameOverTone.play();
			}else{
				Game.dieTone.play();
			}
			
		}
		
	}
	
	/**
	 * Gestisce la vittoria del giocatore, richiamando il relativo effetto sonoro
	 * e dando vita alla relativa schermata di win.
	 *
	 * 
	 */
	public void win(){
		handler.removeEntity(this);
		if(getId()==Id.player){
			Game.showDeathScreen=true;
			Game.gameOver = true;
			Game.winner=true;
			Game.lives = 0;
			Game.themeTone.stop();
			Game.winnerTone.play();
		}
		
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
	 * setta il valore di X
	 *
	 * @param X
	 */
	public void setX(int x) {
		this.x = x;
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
	 * Setta il valore di Y
	 *
	 * @param Y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Restituisce l'id dell'entità in questione
	 *
	 * @return id
	 */
	public Id getId(){
		return id;
	}
	
	/**
	 * Restituisce un indice di solidità
	 *
	 * @return solid
	 */
	public boolean isSolid() {
		return solid;
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
	 * Restituisce un rettangolo, collision detection
	 *
	 * @return Rectangle(x, y, width, heigh) 
	 */
	public Rectangle getBounds(){  //per il collisiondetection
		return new Rectangle(x, y, width, height);
	}
	
	/**
	 * Rstituisco un rettangolo corrispondente alle dimensioni del bordo superiore 
	 * delle entità relative, collision detection 
	 *
	 * @return Rectangle(getX()+10,getY(),width-20,5) 
	 */
	public Rectangle getBoundsTop(){
		return new Rectangle(getX()+10,getY(),width-20,5);
	}
	
	/**
	 * Rstituisco un rettangolo corrispondente alle dimensioni del bordo inferiore
	 * delle entità relative, collision detection 
	 *
	 * @return Rectangle(getX()+10,getY()+height-5,width-20,5);
	 */
	public Rectangle getBoundsBottom(){
		return new Rectangle(getX()+10,getY()+height-5,width-20,5);
	}
	
	/**
	 * Rstituisco un rettangolo corrispondente alle dimensioni del bordo sinistro
	 * delle entità relative, collision detection 
	 *
	 * @return Rectangle(getX(),getY()+10,5,height-20);
	 */
	public Rectangle getBoundsLeft(){
		return new Rectangle(getX(),getY()+10,5,height-20);
	}
	
	/**
	 * Rstituisco un rettangolo corrispondente alle dimensioni del bordo destro
	 * delle entità relative, collision detection 
	 *
	 * @return Rectangle(getX()+width-5,getY()+10,5,height-20);
	 */
	public Rectangle getBoundsRight(){
		return new Rectangle(getX()+width-5,getY()+10,5,height-20);
	}
}
