package it.bomberman.entities;

import it.bomberman.collisions.Body;
import it.bomberman.collisions.Vector2;

/**
 * Classe che reacchiude le i dati che hanno in comune la maggior parte delle Classi che estendono Entity
 *(Restano escluse Wall e Explosion)
 */
public abstract class AbstractEntity implements Entity {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected EntityController controller;
	protected Body body;
	
	protected AbstractEntity(int x, int y, int width, int height, EntityController controller) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.controller = controller;
		initBody();
	}
	
	/**
	 * Template method da implementare per assicurare l'inizializzazione del body
	 */
	protected abstract void initBody();
	
	 @Override
	public Body getBody() {
		 return this.body;
	}
	 
	@Override
	public Vector2 getPosition() {
		return Vector2.unmodifiableVector2(new Vector2(x, y));
	}
}