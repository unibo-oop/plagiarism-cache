package javarogue.generation.rooms;

import java.util.List;
import java.util.Map;

import javarogue.tileengine.Tile;
import javarogue.utility.Position;

/**
 * 
 * A Room is a collection of walkable tiles surrounded by walls.
 *
 */
public interface Room {

	/**
	 * 
	 * Test whether the room can be generated at the desired position.
	 * 
	 * @return Whether the room can be generated.
	 */
	public boolean canBeGenerated();

	/**
	 * Generates the room on the referenced tile map.
	 */
	public void generate();
			
	/**
	 * 
	 * @return The list of tiles associated with the room.
	 */
	public Map<Position, Tile> getTileCoordinates();
		
	/**
	 * 
	 * @return The list of floor tiles associated with the room.
	 */
	public List<Position> getFloor();
		
}
