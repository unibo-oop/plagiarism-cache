package hollowmen.model.dungeon;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import hollowmen.model.Interactable;
import hollowmen.model.Room;
import hollowmen.model.RoomEntity;
import hollowmen.model.roomentity.interactable.Door;
import hollowmen.model.utils.Algorithms;
import hollowmen.model.utils.Box2DUtils;
import hollowmen.model.utils.Constants;
import hollowmen.utilities.ExceptionThrower;
import hollowmen.utilities.RandomSelector;
import hollowmen.enumerators.RoomEntityName;
import hollowmen.model.Attack;
import hollowmen.model.Enemy;

/**
 * This class implements {@link Room}<br>
 * This class use the Compose pattern to track all the {@code Room}s
 * already visited
 * @author pigio
 *
 */
public class RoomImpl implements Room{
	
	private boolean needToGenerate = true;
	
	private Room parentRoom;
	
	private int childNumber;
		
	private Room[] childRoom = new Room[Constants.CHILDROOMQUANTITY];
	
	private Collection<Interactable> interactables = new LinkedList<>();
	
	private Collection<Enemy> enemies = new LinkedList<>();
	
	private Collection<Attack> bullets = new LinkedList<>();
	
	private int roomNumber;
	
	private final int roomNumberWithChild; 
	
	/**
	 * @param parentRoom {@link Room} that is this one's parents
	 * @param childNumber how many child this Room will have
	 * @param roomNumber the room number
	 */
	public RoomImpl(Room parentRoom, int childNumber, int roomNumber) {
		this.parentRoom = parentRoom;
		this.childNumber = childNumber;
		this.roomNumber = roomNumber;
		this.roomNumberWithChild = RandomSelector.getIntFromRange(0, Constants.CHILDROOMQUANTITY - 1);
	};
	
	/**
	 * This method must be called or the {@code Room} will be empty
	 */
	public void autoPopulate() {
		if(this.needToGenerate) {
			this.needToGenerate = false;
			for(int i = 0; i < this.childNumber; i++) {
				new Door(RoomEntityName.DOOR.toString(), i);
			}
			Box2DUtils.linearSpacing(this.interactables);
			Interactable backDoor = new Door(RoomEntityName.DOOR_BACK.toString(), -1);
			Box2DUtils.centerPosition(backDoor);
			if(this.childNumber != 0) {
				this.enemies = Algorithms.generateEnemy();
			} else {
				Algorithms.populateRoom(this);
			}
		}
	}

	/**
	 * {@inheritDoc Room}
	 */
	@Override
	public Room getParentRoom() {
		return this.parentRoom;
	}


	/**
	 * {@inheritDoc Room}
	 */
	@Override
	public Room getChildRoom(int choice) throws IllegalArgumentException {
		ExceptionThrower.<Integer>checkIllegalArgument(choice, i -> i < 0 || i > this.childNumber);
			return this.childRoom[choice] == null ? generateRoom(choice) : this.childRoom[choice]; 
	}

	
	private Room generateRoom(int choice) {
		RoomImpl roomToRet;
		if(choice == roomNumberWithChild) {
			roomToRet = new RoomImpl(this, Constants.CHILDROOMQUANTITY, this.getRoomNumber() + 1);
		} else {
			roomToRet = new RoomImpl(this, 0, this.getRoomNumber() + 1);
		}
		this.childRoom[choice] =  roomToRet;
		return roomToRet;
	}
	
	/**
	 * {@inheritDoc Room}
	 */
	@Override
	public Collection<RoomEntity> getAllEntities() {
		Collection<RoomEntity> coll = new LinkedList<>();
		coll.addAll(interactables);
		coll.addAll(bullets);
		coll.addAll(enemies);
		return coll;
	}

	/**
	 * {@inheritDoc Room}
	 */
	@Override
	public Collection<Interactable> getInteractable() {
		return Collections.unmodifiableCollection(interactables);
	}
	
	/**
	 * {@inheritDoc Room}
	 */
	@Override
	public Collection<Enemy> getEnemies() {
		return Collections.unmodifiableCollection(enemies);
	}

	/**
	 * {@inheritDoc Room}
	 */
	@Override
	public Collection<Attack> getBullets() {
		return Collections.unmodifiableCollection(bullets);
	}

	/**
	 * {@inheritDoc Room}
	 */
	@Override
	public int getRoomNumber() {
		return roomNumber;
	}

	/**
	 * {@inheritDoc Room}
	 */
	@Override
	public void addEntity(RoomEntity roomEntity) {
		if(roomEntity instanceof Attack) {
			bullets.add((Attack) roomEntity);
		}
		if(roomEntity instanceof Enemy) {
			enemies.add((Enemy) roomEntity);
		}
		if(roomEntity instanceof Interactable) {
			interactables.add((Interactable)roomEntity);
		}
	}

	/**
	 * {@inheritDoc Room}
	 */
	@Override
	public void removeEntity(RoomEntity roomEntity) throws IllegalArgumentException {
		if(roomEntity instanceof Attack) {
			bullets.remove(roomEntity);
		}
		if(roomEntity instanceof Enemy) {
			this.enemies.remove(roomEntity);
		}
		if(roomEntity instanceof Interactable) {
			interactables.remove(roomEntity);
		}
	}



}
