package javarogue.generation.corridor;

import java.util.List;

import javarogue.pathfinding.PathFinderBFS;
import javarogue.pathfinding.PathFinderContext;
import javarogue.tileengine.Tile;
import javarogue.utility.Matrix;
import javarogue.utility.Position;

public class CorridorImpl implements Corridor {

	private Matrix<Tile> map;
	private Position origin;
	private Position destination;
	private List<Position> path;

	/**
	 * Builds a corridor
	 * 
	 * @param origin      Origin of the corridor.
	 * @param destination Destination if the corridor.
	 * @param map         Tile map in which to generate the corridor
	 */
	public CorridorImpl(Position origin, Position destination, Matrix<Tile> map) {
		this.map = map;
		if (!this.isInBounds(origin)) {
			throw new IllegalArgumentException("Supplied origin is invalid!");
		} else if (!this.isInBounds(destination)) {
			throw new IllegalArgumentException("Supplied destination is invalid");
		}
		this.origin = origin;
		this.destination = destination;
	}

	/**
	 * Generate the corridor on the provided tile map using BFS.
	 */
	@Override
	public void generate() {
		// Simply find the shortestPath between origin and destination and draws it
		PathFinderContext pathFinder = new PathFinderContext(new PathFinderBFS(), map);
		this.path = pathFinder.findPath(this.origin, this.destination).get();
		this.addTiles(path);
	}

	private void addTiles(List<Position> path) {
		// Simply iterate the path list and add floor tiles to the map accordingly
		for (Position pos : path) {
			this.map.set(pos.getX(), pos.getY(), Tile.FLOOR);
		}
	}

	private boolean isInBounds(Position pos) {
		return pos.getX() >= 0 && pos.getY() >= 0 && pos.getX() < this.map.getRows() && pos.getY() < this.map.getCols();
	}
}
