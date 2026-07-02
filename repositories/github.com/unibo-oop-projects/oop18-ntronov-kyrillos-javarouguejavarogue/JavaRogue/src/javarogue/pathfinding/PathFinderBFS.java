package javarogue.pathfinding;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Stack;

import javarogue.tileengine.Tile;
import javarogue.utility.Matrix;
import javarogue.utility.Position;

/**
 * 
 * BFS shortest path algorithm.
 *
 */
public class PathFinderBFS implements PathFinder {
	
	@Override
	public Optional<List<Position>> makePath(Matrix<Tile> map, List<Tile> exclusionList, Position origin, Position destination) {
		// Initialize path list
		List<Position> path = new LinkedList<>();
		// Make an adjacency list
		Map<Position, List<Position>> adjacency = this.makeAdjacencyList(map, exclusionList);
		// Mark all the vertices as not visited
		Map<Position, Boolean> visited = this.initVisitedMap(map);
		// Create a queue for BFS
		Queue<Position> queue = new LinkedList<>();
		// Create a path stack
		Stack<Position> pathStack = new Stack<>();
		// Enqueue the initial node
		queue.add(origin);
		// Add the initial node to the path stack
		pathStack.add(origin);
		// Mark the initial node as visited
		visited.put(origin, true);
		// While queue has elements
		while (!queue.isEmpty()) {
			// Dequeue a node
			Position current = queue.poll();
			// Get all adjacent nodes of current
			List<Position> adj = adjacency.get(current);
			// Iterate over adjacent nodes
			for (Position pos : adj) {
				// If position hasn't been visited
				if (!visited.get(pos)) {
					// Enqueue
					queue.add(pos);
					// Mark as visited
					visited.put(pos, true);
					// Add to stack
					pathStack.add(pos);
					if (current.equals(destination)) {
						break;
					}
				}
			}

		}
		// BFS complete, now we find the path
		// Set current to destination
		Position candidate;
		Position current = destination;
		// Add destination to the pathStack
		path.add(destination);
		// Iterate over pathStack
		while (!pathStack.isEmpty()) {
			candidate = pathStack.pop();
			// If candidate is a neighbor
			if (this.isNeighbor(current, candidate, adjacency)) {
				// Add it to the path
				path.add(candidate);
				// Update current
				current = candidate;
				// Is path over?
				if (candidate.equals(origin)) {
					if(path.size() <= 1) {
						return Optional.empty();
					} else {
						return Optional.of(path);
					}
				}
			}
		}
		// Finally, return path
		return Optional.empty();
	}

	private Map<Position, List<Position>> makeAdjacencyList(Matrix<Tile> map, List<Tile> exclusionList) {
		Map<Position, List<Position>> adjacency = new HashMap<>();
		map.doubleFor((i, j) -> {
			// Init entry
			Position current = new Position(i, j);
			adjacency.put(current, new LinkedList<>());
			// Check neighbors one by one:
			// Up
			if (i - 1 >= 0 && !exclusionList.contains(map.get(i - 1, j))) {
				adjacency.get(current).add(new Position(i - 1, j));
			}
			// Down
			if (i + 1 < map.getRows() && !exclusionList.contains(map.get(i + 1, j))) {
				adjacency.get(current).add(new Position(i + 1, j));
			}
			// Left
			if (j - 1 >= 0 && !exclusionList.contains(map.get(i, j - 1))) {
				adjacency.get(current).add(new Position(i, j - 1));
			}
			// Right
			if (j + 1 < map.getCols() && !exclusionList.contains(map.get(i, j + 1))) {
				adjacency.get(current).add(new Position(i, j + 1));
			}
		});
		return adjacency;
	}

	private Map<Position, Boolean> initVisitedMap(Matrix<Tile> map) {
		Map<Position, Boolean> visited = new HashMap<>();
		// Simply iterate all the nodes
		map.doubleFor((i, j) -> {
			visited.put(new Position(i, j), false);
		});
		return visited;
	}
	
	private boolean isNeighbor(Position posA, Position posB, Map<Position, List<Position>> adjacency) {
		return adjacency.get(posA).contains(posB);
	}

}
