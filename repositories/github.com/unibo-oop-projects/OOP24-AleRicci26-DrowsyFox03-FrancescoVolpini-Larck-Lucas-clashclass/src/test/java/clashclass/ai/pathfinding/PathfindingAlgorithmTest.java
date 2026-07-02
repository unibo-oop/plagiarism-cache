package clashclass.ai.pathfinding;

import clashclass.commons.VectorInt2D;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PathfindingAlgorithmTest {

    @Test
    void testFindBestPath() {
        final var distanceHeuristics = new EuclideanDistanceHeuristicImpl();
        final var pathfindingAlgorithm = new AStarPathfindingImpl(distanceHeuristics);

        final var startNode = new PathNodeImpl(new VectorInt2D(0,0), 1, Optional.empty());
        final var endNode = new PathNodeImpl(new VectorInt2D(2,0), 1, Optional.empty());

        final var wallNode1 = new PathNodeImpl(new VectorInt2D(1,0), Float.POSITIVE_INFINITY, Optional.empty());
        final var wallNode2 = new PathNodeImpl(new VectorInt2D(1,1), Float.POSITIVE_INFINITY, Optional.empty());

        final var node1 = new PathNodeImpl(new VectorInt2D(0,1), 1, Optional.empty());
        final var node2 = new PathNodeImpl(new VectorInt2D(0,2), 1, Optional.empty());
        final var node3 = new PathNodeImpl(new VectorInt2D(1,2), 1, Optional.empty());
        final var node4 = new PathNodeImpl(new VectorInt2D(2,2), 1, Optional.empty());
        final var node5 = new PathNodeImpl(new VectorInt2D(2,1), 1, Optional.empty());

        final var nodes = new HashSet<PathNode>();

        nodes.add(startNode);
        nodes.add(endNode);
        nodes.add(wallNode1);
        nodes.add(wallNode2);
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
        nodes.add(node4);
        nodes.add(node5);

        final var nodeGrid = new PathNodeGridImpl(3, nodes);

        final var outputPath = pathfindingAlgorithm.findPath(nodeGrid, startNode, endNode).pathNodes();
        final var expectedPath = List.of(startNode, node1, node2, node3, node4, node5, endNode);

        assertEquals(expectedPath, outputPath);
    }
}
