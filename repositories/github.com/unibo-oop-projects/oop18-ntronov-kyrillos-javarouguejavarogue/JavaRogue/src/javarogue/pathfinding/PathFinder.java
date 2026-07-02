package javarogue.pathfinding;

import java.util.List;
import java.util.Optional;

import javarogue.tileengine.Tile;
import javarogue.utility.Matrix;
import javarogue.utility.Position;

/**
 * 
 * A pathfinding algorithm between two positions.
 *
 */
public interface PathFinder {

	/**
	 * 
	 * @param map           Referenced TileMap.
	 * @param exclusionList List of tiles considered untraversable.
	 * @param origin        Position of the origin
	 * @param destination   Position of the destination
	 * @return	A list of positions representing the found path.
	 */
	public Optional<List<Position>> makePath(Matrix<Tile> map, List<Tile> exclusionList, Position origin, Position destination);

}
