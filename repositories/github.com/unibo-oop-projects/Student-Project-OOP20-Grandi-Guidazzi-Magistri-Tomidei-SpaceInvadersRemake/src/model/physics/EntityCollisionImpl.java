package model.physics;

import java.awt.Rectangle;
import java.util.List;
import java.util.stream.Collectors;

import model.World;
import model.entities.SpecificEntityType;
import model.entitiesutil.GenericEntityType;
import model.entitiesutil.typeentities.GenericEntity;
import model.entitiesutil.typeentities.MobileEntity;

/**
 * Implementation of {@link EntityCollision}
 */
public class EntityCollisionImpl implements EntityCollision{

	private World world;
	private List<GenericEntity> enemyEntities;
	private List<GenericEntity> playerEntities;
	
	/**
	 * Implementation of {@link EntityCollision}
	 */
	public EntityCollisionImpl(World world) {
		this.world = world;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void checkCollision() {	
		this.world.getLevelEntities().forEach(e ->{
			this.world.getLevelEntities().forEach(eLevel -> this.collision(e, eLevel));
			this.edgeCollision((MobileEntity) e);
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void checkCollision(GenericEntity e) {
		this.enemyEntities = this.world.getLevelEntities().stream().
				filter(i -> !i.getEntityType().getGenericType().equals(GenericEntityType.PLAYER)
						&& !i.getEntityType().equals(SpecificEntityType.PLAYER_1_BULLET))
				.collect(Collectors.toList());

		this.playerEntities = this.world.getLevelEntities().stream().
				filter(i -> i.getEntityType().getGenericType().equals(GenericEntityType.PLAYER)
						|| i.getEntityType().equals(SpecificEntityType.PLAYER_1_BULLET))
				.collect(Collectors.toList());

		if(e.getEntityType().getGenericType().equals(GenericEntityType.PLAYER) ||
				e.getEntityType().equals(SpecificEntityType.PLAYER_1_BULLET)) {
			this.enemyEntities.forEach(enemy -> this.collision(e, enemy));
		}
		else {
			this.playerEntities.forEach(player -> this.collision(e, player));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void collision(GenericEntity e, GenericEntity entityLevel) {
		if(!e.equals(entityLevel) && e.isAlive() && entityLevel.isAlive() && 
				!e.getEntityType().equals(entityLevel.getEntityType())){

			final Rectangle e1 = new Rectangle();
			final Rectangle e2 = new Rectangle();

			e1.setBounds((int)e.getX()-e.getWidth()/2, (int)e.getY()-e.getHeight()/2, e.getWidth(), e.getHeight());
			e2.setBounds((int)entityLevel.getX()-entityLevel.getWidth()/2, 
					(int)entityLevel.getY()-entityLevel.getHeight()/2, entityLevel.getWidth(), entityLevel.getHeight());
			if(e1.intersects(e2)) {
				e.doAfterCollisionWithEntity(entityLevel);
				entityLevel.doAfterCollisionWithEntity(e);
			}
		}
	}

	/**
	 * Check collision between an {@link GenericEntity} and the frame Edges
	 * 
	 * @param e is the {@link GenericEntity} to check
	 */
	private void edgeCollision(MobileEntity e) {
		if(e.isAlive()) {
			if(e.getX() - e.getWidth()/2 < world.getMinWorldWidth()) {
				e.doAfterCollisionWithEdge(EdgeCollision.LEFT);
			}
			if(e.getX() + e.getWidth()/2 + e.getMuX() > world.getMaxWorldWidth()) {
				e.doAfterCollisionWithEdge(EdgeCollision.RIGHT);
			}
			if(e.getY() - e.getHeight()/2 < world.getMinWorldHeight()) {
				e.doAfterCollisionWithEdge(EdgeCollision.TOP);
			}
			if(e.getY() + e.getHeight()/2 > world.getMaxWorldHeight()) {
				e.doAfterCollisionWithEdge(EdgeCollision.DOWN);
			}
		}
		
	}

}
