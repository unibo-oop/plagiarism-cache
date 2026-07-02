package hollowmen.model.dungeon;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import hollowmen.enumerators.Difficulty;
import hollowmen.model.Dungeon;
import hollowmen.model.Enemy;
import hollowmen.model.Hero;
import hollowmen.model.LimitedCounter;
import hollowmen.model.Pokedex;
import hollowmen.model.Room;
import hollowmen.model.RoomEntity;
import hollowmen.model.Shop;
import hollowmen.model.Time;
import hollowmen.model.dungeon.time.TimerSingleton;
import hollowmen.model.utils.Actors;
import hollowmen.model.utils.Box2DUtils;
import hollowmen.model.utils.Constants;
import hollowmen.model.utils.GameOverException;
import hollowmen.model.utils.SimpleLimitedCounter;
import hollowmen.model.utils.UpperLimitReachException;
import hollowmen.utilities.ExceptionThrower;
import hollowmen.utilities.Pair;

/**
 * This class is the core part of the Model, it implements {@link Dungeon} interface<br>
 * This class use Singleton Pattern so that any other class can call it
 * <br>
 * @author pigio
 *
 */
public class DungeonSingleton implements Dungeon{

	private final float GRAVITY = 0.1f;
	private final float THICKNESS = 10f;
	private final int ITERATION_VELOCITY = 6;
	private final int ITERATION_POSITION = 3;
	
	private Room lobby = new Lobby();
	
	private LimitedCounter unlockedFloors;
	
	private int floorNumber = 0;
	
	private Room currentRoom;
	
	private Shop shop;
	
	private Hero hero;
	
	private Difficulty diff;
	
	private World world;
	
	private Pokedex poke;
	
	private Collection<RoomEntity> disposeList = new LinkedList<>();
	
	private boolean gameOver = false;
	
	private DungeonSingleton() {
		this.world = new World(new Vec2(0, GRAVITY));
		setUpBorder();
		this.world.setContactListener(new GameCollisionListener());
	};
	
	/**
	 * @return {@link DungeonSingleton} unique instance
	 */
	public static DungeonSingleton getInstance() {
		return Holder.Instance;
	}
	
	private static class Holder {
		public static DungeonSingleton Instance =  new DungeonSingleton();
	}
	
	/**
	 * {@inheritDoc Dungeon}
	 */
	@Override
	public void update(long deltaTime) throws GameOverException {
		this.disposeList.stream().forEach(r -> {
			this.currentRoom.removeEntity(r);
			world.destroyBody(r.getBody());
		});
		this.disposeList.clear();
		if(gameOver) {
			endRun();
			throw new GameOverException();
		}
		try {
			TimerSingleton.getInstance().addToValue(deltaTime);
		} catch (UpperLimitReachException e) {
			this.gameOver();
		}
		this.getCurrentRoom().getEnemies().stream().forEach(x -> x.move("By Pattern"));
		this.getCurrentRoom().getBullets().stream().forEach(x -> x.move("By Yourself"));
		world.step(deltaTime, ITERATION_VELOCITY, ITERATION_POSITION);
	}
	
	/**
	 * {@inheritDoc Dungeon}
	 */
	@Override
	public int getFloorNumber() {
		return this.floorNumber;
	}
	
	/**
	 * {@inheritDoc Dungeon}
	 */
	@Override
	public Room getCurrentRoom() {
		return (this.floorNumber == 0)?this.lobby:this.currentRoom;
	}
	
	/**
	 * {@inheritDoc Dungeon}
	 */
	@Override
	public void changeRoom(int newRoomNumber) {
		Box2DUtils.centerPosition(this.hero);
		if(newRoomNumber < 0) {
			this.currentRoom = this.currentRoom.getParentRoom();
		} else {
			this.currentRoom = this.currentRoom.getChildRoom(newRoomNumber);
			this.currentRoom.autoPopulate();
			this.poke.checkNewEnemy(this.currentRoom);
		}
		if(this.currentRoom.getRoomNumber() == 0 
				|| this.currentRoom.getRoomNumber() > Constants.ROOM_TO_VISIT) {
			this.gameOver();
		}
	}
	
	/**
	 * {@inheritDoc Dungeon}
	 */
	@Override
	public void goTo(int floorNumber) throws IllegalStateException {
		if(floorNumber <= 0) {
			this.gameOver();
			return;
		}
		ExceptionThrower.checkIllegalState(floorNumber, f -> f > this.unlockedFloors.getValue());
		TimerSingleton.getInstance().resetAndLimit(100000);
		this.floorNumber = floorNumber;
		this.currentRoom = new RoomImpl(this.lobby, Constants.CHILDROOMQUANTITY, 1);
		this.currentRoom.autoPopulate();
		this.poke.checkNewEnemy(this.currentRoom);
		Box2DUtils.centerPosition(this.hero);
	}


	/**
	 * {@inheritDoc Dungeon}
	 */
	@Override
	public Pair<Integer, Integer> getFloors() {
		return new Pair<>((int) this.unlockedFloors.getValue(), (int) this.unlockedFloors.getLimit());
	}

	/**
	 * {@inheritDoc Dungeon}
	 */
	@Override
	public void endRun() {
		if(this.currentRoom.getRoomNumber() > Constants.ROOM_TO_VISIT
				&& this.floorNumber == this.unlockedFloors.getValue()) {
			try {
				this.unlockedFloors.addToValue(1);
			} catch (UpperLimitReachException e) {};
		}
		Collection<Enemy> toDestroy = this.currentRoom.getEnemies().stream().collect(Collectors.toList());
		toDestroy.stream().forEach(x -> {
			this.currentRoom.removeEntity(x);
			this.world.destroyBody(x.getBody());
		});
		this.gameOver = false;
		this.floorNumber = 0;
		Actors.regenerate(this.hero);
	}

	/**
	 * {@inheritDoc Dungeon}
	 */
	@Override
	public Difficulty getDifficulty() {
		return this.diff;
	}

	/**
	 * {@inheritDoc Dungeon}
	 */
	@Override
	public Shop getShop() {
		return this.shop;
	}

	/**
	 * {@inheritDoc Dungeon}
	 */
	@Override
	public Hero getHero() {
		return this.hero;
	}

	/**
	 * {@inheritDoc Dungeon}
	 */
	@Override
	public World getWorld() {
		return this.world;
	}
	
	
	/**
	 * This method launches {@link GameOverException} on the next
	 * {@code update()} call
	 */
	public void gameOver() {
		this.gameOver = true;
	}
	
	private void setUpBorder() {

		float halfLength = (float)(Constants.WORLD_SIZE.getWidth()/2);
		float halfHeight = (float)(Constants.WORLD_SIZE.getHeight()/2);

		PolygonShape polygonShape = new PolygonShape();

		BodyDef bodyDef = new Box2DUtils.BodyDefBuilder()
				.type(BodyType.STATIC)
				.position((float) Constants.WORLD_SIZE.getCenterX(), (float) Constants.WORLD_SIZE.getCenterY())
				.build();

		FixtureDef groundFix = Box2DUtils.fixDefBuilder()
				.friction(0f)
				.restitution(0.5f)
				.shape(polygonShape)
				.filter(new Box2DUtils.FilterBuilder()
						.addCategory(FilterType.GROUND.getValue())
						.addMask(FilterType.GROUND.getValue())
						.addMask(FilterType.ENEMY.getValue())
						.addMask(FilterType.ENEMYATTACK.getValue())
						.addMask(FilterType.FLY.getValue())
						.addMask(FilterType.FLYLINE.getValue())
						.addMask(FilterType.HERO.getValue())
						.addMask(FilterType.HEROATTACK.getValue())
						.addMask(FilterType.JUMP.getValue())
						.addMask(FilterType.INTERACTABLE.getValue())
						.addMask(FilterType.WALL.getValue())
						.build())
				.build();

		FixtureDef wallFix = Box2DUtils.fixtureDefCloner(groundFix);
		wallFix.getFilter().categoryBits = FilterType.WALL.getValue();
		
		FixtureDef airLine = Box2DUtils.fixtureDefCloner(groundFix);
		airLine.getFilter().categoryBits = FilterType.FLYLINE.getValue();
		airLine.getFilter().maskBits = FilterType.FLY.getValue();
		
		Body body = world.createBody(bodyDef);

		//ground
		polygonShape.setAsBox(halfLength+THICKNESS, THICKNESS, new Vec2(0, halfHeight+THICKNESS), 0);
		body.createFixture(groundFix);
		//top
		polygonShape.setAsBox(halfLength+THICKNESS, THICKNESS, new Vec2(0, -(halfHeight+THICKNESS)), 0);
		body.createFixture(wallFix);
		//left
		polygonShape.setAsBox( THICKNESS, halfHeight+THICKNESS, new Vec2(-(halfLength+THICKNESS), 0), 0);
		body.createFixture(wallFix);
		//right
		polygonShape.setAsBox( THICKNESS, halfHeight+THICKNESS, new Vec2(halfLength+THICKNESS, 0), 0);
		body.createFixture(wallFix);
		//flyLine
		polygonShape.setAsBox(halfLength+THICKNESS, THICKNESS, new Vec2(0, 80), 0);
		body.createFixture(airLine);
	}
	
	/**
	 * {@inheritDoc Dungeon}
	 */
	@Override
	public Pokedex getPokedex() {
		return this.poke;
	}
	
	/**
	 * This method set the {@code Difficulty}
	 * @param diff {@link Difficulty}
	 */
	public void setDifficulty(Difficulty diff) {
		this.diff = diff;
	}
	
	/**
	 * This method set the {@code Hero}
	 * @param hero {@link Hero}
	 */
	public void setHero(Hero hero) {
		if(this.hero != null) {
			this.world.destroyBody(this.hero.getBody());
		}
		this.hero = hero;
	}
	
	/**
	 * @param lastUnlock last floor number reached 
	 * @param maxFloor max floor number reachable
	 */
	public void setUnlockedFloor(int lastUnlock, int maxFloor) {
		this.unlockedFloors = new SimpleLimitedCounter(lastUnlock, maxFloor);
	}
	
	/**
	 * 
	 * @param poke {@link Pokedex}
	 */
	public void setPokedex(Pokedex poke) {
		this.poke = poke;
	}

	/**
	 * This method adds <b>re</b> to the dispose list<br>
	 *  each update cycle
	 * the {@code Dungeon} will remove all the {@code RoomEntity} inside the
	 * dispose list which means remove them from the current {@code Room} and their
	 * {@code Body} from the {@code World} 
	 * @param re {@link RoomEntity}
	 */
	public void addToDisposeList(RoomEntity re) {
		this.disposeList.add(re);
	}

	/**
	 * @param shop {@link Shop}
	 */
	public void setShop(Shop shop) {
		this.shop = shop;
	}

	/**
	 * {@inheritDoc Dungeon}
	 */
	@Override
	public Time getTimer() {
		return TimerSingleton.getInstance();
	}

}
