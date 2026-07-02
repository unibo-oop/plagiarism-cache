package javarogue.objects;

import javarogue.tileengine.Tile;
import javarogue.utility.Position;

import java.util.HashMap;
import java.util.Map;

import javarogue.behaviourmodules.StatNames;

public abstract class GameObject {
	
	protected Tile tile;
	protected ObjectType type;
	protected ObjectState state;
	protected Position position;
	
	protected final Map<StatNames, Integer> StatsIncrement;
	
	
	public GameObject(Position position, ObjectType type, Tile tile) {
		this.position = position;
		this.state = ObjectState.ACTIVE;
		this.type = type;
		this.tile = tile;
		this.StatsIncrement = new HashMap<StatNames, Integer>();
	}
	
	/**
	 * 
	 * @return The tile used to draw the object on the screen
	 */
	public Tile GetTile() {
		return tile;
	}
	
	/**
	 * @return the object type
	 */
	public ObjectType GetType() {
		return type;
	}
	
	/**
	 * The state of the object.
	 * This defines if the object is active, unactive, and
	 * what the player can do with it
	 */
	public ObjectState GetState() {
		return state;
	}
	
	/**
	 * 
	 * @return The object position in the map
	 */
	public Position GetPosition() {
		return position;
	}
	
	public Map<StatNames, Integer> GetStatsIncrements() {
		return StatsIncrement;
	}
	
	/**
	 * Performs the object action.
	 * This could affect the player, an enemy or the environment
	 */
	public abstract void DoAction();
	
	
}
