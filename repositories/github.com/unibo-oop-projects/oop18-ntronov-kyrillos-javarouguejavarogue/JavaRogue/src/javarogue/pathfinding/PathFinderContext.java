package javarogue.pathfinding;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javarogue.tileengine.Tile;
import javarogue.utility.Matrix;
import javarogue.utility.Position;

/**
 * 
 * Context Class for pathfindig Strategy pattern
 *
 */
public class PathFinderContext {

	private PathFinder pathFinder;
	private Matrix<Tile> map;
	private List<Tile> exclusionList;

	/**
	 * Build a new pathfinding context with selected path finding algorithm
	 * 
	 * @param pathFinder The pathfinding algorithm.
	 * @param map        The referenced tile map.
	 */
	public PathFinderContext(PathFinder pathFinder, Matrix<Tile> map) {
		this.pathFinder = pathFinder;
		this.map = map;
		this.exclusionList = new LinkedList<>();
	}

	/**
	 * Sets the list of tiles considered untraversable.
	 * 
	 * @param exclusionList The list of tiles considered untraversable.
	 */
	public void setExclusionList(List<Tile> exclusionList) {
		this.exclusionList = exclusionList;
	}

	/**
	 * Sets the current pathfinding algorithm.
	 * 
	 * @param pathFinder The path finding algorithm.
	 */
	public void setPathFinder(PathFinder pathFinder) {
		this.pathFinder = pathFinder;
	}

	/**
	 * Calculates the shortest path between two points.
	 * 
	 * @param origin      The origin point.
	 * @param destination The destination point.
	 * @return list of positions representing the path or empty if path couldn't be
	 *         calculated.
	 */
	public Optional<List<Position>> findPath(Position origin, Position destination) {
		return this.pathFinder.makePath(map, exclusionList, origin, destination);
	}

}
