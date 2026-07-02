package javarogue.test.pathfinding;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import javarogue.pathfinding.PathFinderBFS;
import javarogue.pathfinding.PathFinderContext;
import javarogue.tileengine.Tile;
import javarogue.utility.Matrix;
import javarogue.utility.Position;

public class PathFinderBFSTest {

	@Test
	public void testPathfinding() {
		Matrix<Tile> map = new Matrix<>(10, 10);
		// Manually create a map with obstacles
		// W W W W W W W W W W
		// W S F F F W F F F W
		// W W W W F W F W F W
		// W F F F F F F W F W
		// W W F W W W W W F W
		// W W F F F W F F F W
		// W W F W F W F W W W
		// W W F W F F F F F W
		// W F F W W W W W E W
		// W W W W W W W W W W

		//Solution:
		// W W W W W W W W W W
		// W * * * * W F F F W
		// W W W W * W F W F W
		// W F * * * F F W F W
		// W W * W W W W W F W
		// W W * * * W F F F W
		// W W F W * W F W W W
		// W W F W * * * * * W
		// W F F W W W W W * W
		// W W W W W W W W W W

		map.fill(Tile.VOID);
		List<Position> floor = Arrays.asList(new Position(1, 1), new Position(1, 2), new Position(1, 3),
				new Position(1, 4), new Position(1, 6), new Position(1, 7), new Position(1, 8), new Position(2, 4),
				new Position(2, 6), new Position(2, 7), new Position(3, 1), new Position(3, 1), new Position(3, 2),
				new Position(3, 3), new Position(3, 4), new Position(3, 5), new Position(3, 6), new Position(3, 8),
				new Position(4, 2), new Position(4, 8), new Position(5, 2), new Position(5, 3), new Position(5, 4),
				new Position(5, 6), new Position(5, 7), new Position(5, 8), new Position(6, 2), new Position(6, 4),
				new Position(6, 6), new Position(7, 2), new Position(7, 4), new Position(7, 5), new Position(7, 6),
				new Position(7, 7), new Position(7, 8), new Position(8, 1), new Position(8, 2), new Position(8, 8));
		for(Position p : floor) {
			map.set(p.getX(), p.getY(), Tile.FLOOR);
		}
		PathFinderContext context = new PathFinderContext(new PathFinderBFS(), map);
		context.setExclusionList(Arrays.asList(Tile.VOID));
		assertTrue(context.findPath(new Position(1, 1), new Position(8, 8)).isPresent());

		List<Position> path = context.findPath(new Position(1, 1), new Position(8, 8)).get();
		List<Position> solution = Arrays.asList(new Position(8, 8), new Position(7, 8), new Position(7, 7),
				new Position(7, 6), new Position(7, 5), new Position(7, 4), new Position(6, 4), new Position(5, 4),
				new Position(5, 3), new Position(5, 2), new Position(4, 2), new Position(3, 2), new Position(3, 3),
				new Position(3, 4), new Position(2, 4), new Position(1, 4), new Position(1, 3), new Position(1, 2),
				new Position(1, 1));
		assertEquals(solution, path);
	}

}
