package model.entities;

import model.Model;
import model.entitiesutil.EntityConstants;
import model.entitiesutil.GenericEntityType;
import model.entitiesutil.Player;
import model.entitiesutil.typeentities.GenericEntity;
import model.physics.EntityCollision.EdgeCollision;
import view.game.GameEvent;

/**
 * The entity with which the user with a generic player.
 */
public class Player1 extends Player{
	
	private final Model model;
	private boolean shoot;
	
	/**
	 * The constructor that create the entity.
	 * @param type 		is the type of player
	 * @param x			is the initial x coordinate  
	 * @param y			is the initial y coordinate 
	 * @param model		is the {@link Model}
	 */
	public Player1(SpecificEntityType type, double x, double y, Model model) {
		this.model = model;
		double speedX = this.model.getMaxWorldWidth()/100;
		this.create(type, x, y, EntityConstants.Player.INITIAL_WIDTH, EntityConstants.Player.INITIAL_HEIGHT,
				speedX, 0, EntityConstants.Player.MAX_HITS);
		
		this.shoot = false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void shoot() {
		this.model.getNewEntities().add(new PlayerBullet(this.getX(), getY() - this.getHeight()/2 - EntityConstants.PlayerBullet.INITIALHEIGHT/2));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doAfterCollisionWithEntity(GenericEntity entity) {
		if(entity.getEntityType().getGenericType().equals(GenericEntityType.ENEMY_BULLET) && this.isAlive()){
			this.incHits();
		} else if(entity.getEntityType().getGenericType().equals(GenericEntityType.BOSS)
				|| (entity.getEntityType().getGenericType().equals(GenericEntityType.GENERIC_ENEMY))){
			while (this.getHits() <= EntityConstants.Player.MAX_HITS) {
				this.incHits();
			}
		}		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doAfterCollisionWithEdge(EdgeCollision edge) {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateEntityPosition(GameEvent event, int cycles) {
		if(this.getX() - this.getWidth() / 2 > model.getMinWorldWidth()) {
			if(event.equals(GameEvent.LEFT)) {
				this.setMuX(-EntityConstants.Player.INITIAL_MU_X);
				this.setX(this.getX() + this.getMuX());
			} 
		}
		if(this.getX() + this.getWidth() / 2 < model.getMaxWorldWidth()) {
			if (event.equals(GameEvent.RIGHT)){
				this.setMuX(EntityConstants.Player.INITIAL_MU_X);
				this.setX(this.getX() + this.getMuX());
			}
		}
		if (event.equals(GameEvent.FIRE) && this.canShoot(cycles)) {
			this.shoot();
			this.shoot = false;
		}
	}
	
	/**
	 * Method that controls if the entity can shoot.
	 * @param cycles
	 * @return
	 */
	private boolean canShoot(int cycles) {
		int cycleToShoot = EntityConstants.Player.CYCLES_TO_SHOOT;
		this.shoot = (cycleToShoot == 0) ? true : 
			(cycles % cycleToShoot == 0) ? true : this.shoot;
		return this.shoot;
	}
}
