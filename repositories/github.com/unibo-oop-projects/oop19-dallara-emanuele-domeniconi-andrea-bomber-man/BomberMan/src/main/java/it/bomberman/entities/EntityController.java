package it.bomberman.entities;

import it.bomberman.collisions.ICollidable;

public interface EntityController {
	public void register(Entity entity);

	public void notifyDisposal(Entity entity);

	public boolean verifyCollision(ICollidable coll);

	public void checkAndResolveCollisions(ICollidable coll);
}
