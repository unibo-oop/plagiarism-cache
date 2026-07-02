package model;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import model.entities.Alien;
import model.entities.AlienGroup;
import model.entities.Boss1;
import model.entities.Boss2;
import model.entities.Boss3;
import model.entities.Player1;
import model.entities.PlayerBullet;
import model.entities.SpecificEntityType;
import model.entitiesutil.Enemy;
import model.entitiesutil.EntityConstants;
import model.entitiesutil.GenericEntityType;
import model.entitiesutil.MappedEntity;
import model.entitiesutil.MappedEntityImpl;
import model.entitiesutil.typeentities.AutoMovableEntity;
import model.entitiesutil.typeentities.GenericEntity;
import model.entitiesutil.typeentities.UserEntity;
import model.physics.EntityCollision;
import model.physics.EntityCollisionImpl;
import util.Pair;
import view.game.GameEvent;

/**
 * {@link World} implementation
 *
 */
public class WorldImpl implements World{
	
	private final int INITIAL_MAX_WIDTH = 800;
	private final int INITIAL_MAX_HEIGHT = 600;
	private final int INITIAL_MIN_WIDTH = 0;
	private final int INITIAL_MIN_HEIGHT = 0;

	private final int LAST_LEVEL = 6;
	private final int FIRST_LEVEL = 1;

	private double MAX_WIDTH;
	private double MAX_HEIGHT;
	private double MIN_WIDTH;
	private double MIN_HEIGHT;

	private Set<GenericEntity> entities;
	private Set<GenericEntity> newEntities;

	private final Model model;
	private final EntityCollision collisionManager;
	private final LvLoader loaderManager;
	private final AlienGroup alienGroup;
	private int score;
	private int lvlNum;

	/**
	 * {@link World} implementation
	 *
	 */
	public WorldImpl(Model model) {
		this.model = model;
		this.collisionManager = new EntityCollisionImpl(this);
		this.entities = new HashSet<>();
		this.newEntities = new HashSet<>();	
		this.alienGroup = new AlienGroup(this.model);
		this.score = 0;
		this.lvlNum =  this.FIRST_LEVEL;
		this.loaderManager = new LvLoaderImpl();

		MAX_WIDTH = (this.INITIAL_MAX_WIDTH == 0) ? 100 + this.INITIAL_MIN_WIDTH : this.INITIAL_MAX_WIDTH;
		MAX_HEIGHT = (this.INITIAL_MAX_HEIGHT == 0) ? 100 + this.INITIAL_MIN_HEIGHT : this.INITIAL_MAX_HEIGHT;
		MIN_WIDTH = this.INITIAL_MIN_WIDTH;
		MIN_HEIGHT = this.INITIAL_MIN_HEIGHT;
		
	}

	/**
	 * method that initializes the layer and resizes the game edges to accommodate all entities
	 */
	private void nextLevel() {

		Set<GenericEntity> eLevel = new HashSet<>();

		eLevel.add(new Player1(SpecificEntityType.PLAYER_1, (this.INITIAL_MAX_WIDTH+this.INITIAL_MIN_WIDTH)/2,
				(this.INITIAL_MAX_HEIGHT - EntityConstants.Player.INITIAL_HEIGHT/2), this.model));

		Optional<Level> lv = this.loaderManager.loadLevel(lvlNum);
		int numAliens = lv.get().getAliens();
		Optional<String> bossType = lv.get().getBoss();

		eLevel.addAll(this.placeEnemy(bossType, numAliens,
				this.INITIAL_MAX_WIDTH, this.INITIAL_MIN_WIDTH, this.INITIAL_MAX_HEIGHT, this.INITIAL_MIN_HEIGHT));
		
		eLevel.forEach(e ->{
			if(!e.getEntityType().getGenericType().equals(GenericEntityType.GENERIC_ENEMY)) {
				e.setPos((e.getX() > this.MAX_WIDTH) ? this.MAX_WIDTH :
					(e.getX() < this.MIN_WIDTH) ? this.MIN_WIDTH : e.getX(),
						(e.getY() > this.MAX_HEIGHT) ? this.MAX_HEIGHT :
							(e.getY() < this.MIN_HEIGHT) ? this.MIN_HEIGHT : e.getY());
			}
		});

		this.setWorldBounds(eLevel);

		this.entities.add(new Player1(SpecificEntityType.PLAYER_1, (this.MAX_WIDTH+this.MIN_WIDTH)/2,
				(this.MAX_HEIGHT - EntityConstants.Player.INITIAL_HEIGHT/2), this.model));
		
		this.entities.addAll(this.placeEnemy(bossType, numAliens,
				this.MAX_WIDTH, this.MIN_WIDTH, this.MAX_HEIGHT, this.MIN_HEIGHT));

		this.entities.forEach(e ->{

			if(e.getX() + e.getWidth()/2 > this.MAX_WIDTH) {
				e.setX(this.INITIAL_MAX_WIDTH);
			}
			if(e.getX() - e.getWidth()/2 < this.MIN_WIDTH) {
				e.setX(this.INITIAL_MIN_WIDTH);
			}
			if(e.getY() + e.getHeight()/2 > this.MAX_HEIGHT) {
				e.setY(this.INITIAL_MAX_HEIGHT);
			}
			if(e.getY() - e.getHeight()/2 < this.MIN_HEIGHT) {
				e.setY(this.INITIAL_MIN_HEIGHT);
			}
		});
	}

	/**
	 * method that sets the limits of the game world
	 * @param eLevel
	 */
	private void setWorldBounds(Set<GenericEntity> eLevel){
		for(var e: eLevel) {
			this.MAX_WIDTH = (e.getX() + e.getWidth()/2 > this.MAX_WIDTH) ? e.getX() + e.getWidth() : this.MAX_WIDTH;
			this.MAX_HEIGHT = (e.getY() + e.getHeight()/2 > this.MAX_HEIGHT) ? e.getY() + e.getHeight() : this.MAX_HEIGHT;
			this.MIN_WIDTH = (e.getX() - e.getWidth()/2 < this.MIN_WIDTH) ? e.getX() - e.getWidth() : this.MIN_WIDTH;
			this.MIN_HEIGHT = (e.getY() - e.getHeight()/2 < this.MIN_HEIGHT) ? e.getY() - e.getHeight() : this.MIN_HEIGHT;
		}
	}

	/**
	 * method that checks if the input string belongs to the enumeration
	 * @param string
	 * @return
	 */
	private boolean isBossType(String string) {
		for (var t : SpecificEntityType.values()) {
			if (t.toString().equals(string.toUpperCase())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * method that places entities at the beginning of the level
	 * @param bossType
	 * @param numAliens
	 * @param maxWidth
	 * @param minWidth
	 * @param maxHeight
	 * @param minHeight
	 * @return
	 */
	private Set<GenericEntity> placeEnemy(Optional <String> bossType, int numAliens,
			double maxWidth, double minWidth, double maxHeight, double minHeight) {

		Set<GenericEntity> set = new HashSet<>();

		if(numAliens != 0) {
			set.addAll(this.alienGroup.createAlienGroup(numAliens));
		}
		
		if(!bossType.isEmpty() && this.isBossType(bossType.get())) {
			switch (bossType.get().toUpperCase()) {
			case "BOSS_1" : 
				set.add(new Boss1((int) ((maxWidth+minWidth)/2),
						(int) (minHeight + EntityConstants.Boss1.INITIAL_HEIGHT), this.model));
				break;
			case "BOSS_2" : 
				set.add(new Boss2((int)(((maxWidth+minWidth)/2)),
						(int) (minHeight + EntityConstants.Boss1.INITIAL_HEIGHT), this.model));
				break;
			case "BOSS_3" : 
				set.add(new Boss3((int)(((maxWidth+minWidth)/2)),
						(int) (minHeight + EntityConstants.Boss1.INITIAL_HEIGHT), this.model));
			}
		}
		return set;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<GenericEntity> getLevelEntities() {
		return this.entities.stream().filter(e -> e.isAlive()).collect(Collectors.toSet());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateEntityLevel(int cycles) {
		if(this.isGameOver()) {
			this.model.processGameOver();
		}
		if(!this.isEnemiesAlive()) {
			this.startNextLevel();
		}
		if(!this.enemyCapableOfFiring().isEmpty()) {
			this.enemyCapableOfFiring().stream()
				.filter(e -> !(e instanceof Alien))
				.filter(e -> cycles!=0)
				.filter(e -> e.canShoot(cycles))
				.forEach(e ->e.shoot());
			if(cycles != 0) {
				this.alienGroup.shoot(cycles);
			}
			if(!this.newEntities.isEmpty()) {
				this.entities.addAll(this.newEntities);
				this.newEntities.clear();
			}
		}
		this.getAutoMovableEntitiesLevel().forEach(e -> e.updateEntityPosition());
		this.collisionManager.checkCollision();

		this.score += this.entities.stream()
				.filter(e -> !e.getEntityType().getGenericType().equals(GenericEntityType.PLAYER_BULLET))
				.filter(e -> !e.isAlive())
				.mapToInt(e -> e.getEntityType().getEntityValue())
				.sum();
		
		this.score += this.entities.stream()
				.filter(e -> e instanceof PlayerBullet)
				.filter(e -> !e.isAlive())
				.map(e -> (PlayerBullet)e)
				.filter(e -> !e.touchEntity())
				.mapToInt(e -> e.getEntityType().getEntityValue())
				.sum();
				
		this.entities.removeAll(this.entities.stream()
				.filter(e -> !e.isAlive())
				.collect(Collectors.toSet()));
	}

	/**
	 * method that returns a set of all entities that move autonomously
	 * @return
	 */
	private Set<AutoMovableEntity> getAutoMovableEntitiesLevel() {
		Set<AutoMovableEntity> set = new HashSet<>();
		this.entities.forEach(e -> {
			if((e instanceof AutoMovableEntity) && e.isAlive()) {
				set.add((AutoMovableEntity)e);
			}
		});
		return set;
	}
	
	/**
	 * method that returns a set of player entities
	 * @return
	 */
	private Set<UserEntity> getUserEntity() {
		Set<UserEntity> set = new HashSet<>();
		this.entities.forEach(e -> {
			if((e instanceof UserEntity) && e.isAlive()) {
				set.add((UserEntity)e);
			}
		});
		return set;
	}
	
	/**
	 * method that returns a set of enemies
	 * @return
	 */
	private Set<Enemy> getEnemy(){
		return this.entities.stream()
				.filter(e -> e instanceof Enemy)
				.filter(e -> e.isAlive())
				.map(e -> (Enemy)e)
				.collect(Collectors.toSet());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<MappedEntity> getMappedEntities(){
		Set<MappedEntity> set = new HashSet<>();
		this.entities.stream().filter(e -> e.isAlive()).forEach(e ->{
			Pair<Double,Double> pos = this.getMappedCoordinate(e.getPos());
			set.add(new MappedEntityImpl(e.getEntityType(), pos.getX(), pos.getY(), this.getMapppedWidth(e.getWidth()),
					this.getMappedHeight(e.getHeight())));
		});
		return set;
	}
	
	/**
	 * method that returns the mapped coordinates
	 * @param pos
	 * @return
	 */
	private Pair<Double, Double> getMappedCoordinate(Pair<Double, Double> pos){
		double x = 0 + (pos.getX() - MIN_WIDTH) * (this.model.getController().getWindowWidth() - 0)/
				(MAX_WIDTH - MIN_WIDTH);
		double y = 0 + (pos.getY() - MIN_HEIGHT) * (this.model.getController().getWindowHeight() - 0)/
				(MAX_HEIGHT - MIN_HEIGHT);
		return new Pair<Double, Double>(x, y);
	}

	/**
	 * method that returns the mapped width
	 * @param width
	 * @return
	 */
	private int getMapppedWidth(int width) {
		double min = 0 + (0 - MIN_WIDTH) * (this.model.getController().getWindowWidth() - 0)/
				(MAX_WIDTH - MIN_WIDTH);
		double max = 0 + (width - MIN_WIDTH) * (this.model.getController().getWindowWidth() - 0)/
				(MAX_WIDTH - MIN_WIDTH);
		return (int) (max - min);
	}

	/**
	 * method that returns the mapped height
	 * @param height
	 * @return
	 */
	private int getMappedHeight(int height) {
		double min = 0 + (0 - MIN_HEIGHT) * (this.model.getController().getWindowHeight() - 0)/
				(MAX_HEIGHT - MIN_HEIGHT);
		double max = 0 + (height - MIN_HEIGHT) * (this.model.getController().getWindowHeight() - 0)/
				(MAX_HEIGHT - MIN_HEIGHT);
		return (int) (max - min);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Alien> getAlienList() {
		return this.entities.stream().filter(e -> e instanceof Alien).filter(e -> e.isAlive()).map(e -> (Alien)e).collect(Collectors.toList());
	}
	
	/**
	 * method that return a set of enemy which are capable of firing
	 * @return
	 */
	private Set<Enemy> enemyCapableOfFiring() {
		return this.entities.stream().filter(e -> e instanceof Enemy).filter(e -> e.isAlive()).map(e -> (Enemy)e).collect(Collectors.toSet());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getMaxWorldWidth() {
		return this.MAX_WIDTH;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getMaxWorldHeight() {
		return this.MAX_HEIGHT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getMinWorldWidth() {
		return this.MIN_WIDTH;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getMinWorldHeight() {
		return this.MIN_HEIGHT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void restartLevel() {
		this.score = 0;
		this.lvlNum = this.FIRST_LEVEL;
		this.startNextLevel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getScore() {
		return this.score;
	}
	
	/**
	 * method that checks if there are still enemies alive
	 * @return
	 */
	private boolean isEnemiesAlive() {
		return !this.getEnemy().isEmpty();
	}

	/**
	 * method that checks if the player is still alive
	 * @return
	 */
	private boolean isPlayerAlive() {
		return !this.getUserEntity().isEmpty();
	}
	
	/**
	 * method that checks if it is game over
	 * @return
	 */
	private boolean isGameOver() {
		if(this.isEnemiesAlive() && this.isPlayerAlive()) {
			double maxEnemy = this.getEnemy().stream().map(e -> e.getY() + e.getHeight()/2).max(Double::compare).get();
			double minPlayer = this.getUserEntity().stream().map(e -> e.getY() - e.getHeight()/2).min(Double::compare).get();
			return maxEnemy > minPlayer;
		}
		return !this.isPlayerAlive();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<GenericEntity> getNewEntities() {
		return this.newEntities;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startNextLevel() {
		this.entities.clear();
		this.newEntities.clear();
		if(!(this.lvlNum > this.LAST_LEVEL)) {
			this.nextLevel();
			this.lvlNum++;
		}
		else{
			this.model.getController().victory();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void processInput(List<GameEvent> events, int cycles) {
		events.forEach(e -> this.getUserEntity().forEach(eL -> eL.updateEntityPosition(e, cycles)));
	}
}
