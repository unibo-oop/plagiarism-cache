package it.bomberman.collisions;

import java.util.HashSet;
import java.util.Set;

public class CollisionManager {
	private Set<ICollidable> collidables;

	public CollisionManager() {
		this.collidables = new HashSet<ICollidable>();
	}

	public void register(ICollidable coll) {
		this.collidables.add(coll);
	}
	
	public void remove(ICollidable coll) {
		this.collidables.remove(coll);
	}

	public boolean verifyCollision(ICollidable col) {
		/**
		 * Verifica se l'oggetto collide con qualcosa
		 */
		return this.collidables.stream()
				.filter(col::shouldCollide)
				.filter(c -> col.getBody().checkCollision(c.getBody()))
				.count() > 0;
	}

	public void checkAndResolveCollisions(ICollidable col) {
		this.collidables.stream()
				.filter(col::shouldCollide)
				.filter(c -> col.getBody().checkCollision(c.getBody()))
				.forEach(col::collision);
	}
}
