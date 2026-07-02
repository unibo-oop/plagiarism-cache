package clashclass.ai.pathfinding;

import clashclass.commons.Vector2D;
import java.util.Queue;
import java.util.Set;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents an implementation of A*, a well-know pathfinding algorithm.
 * <a href="https://www.baeldung.com/java-a-star-pathfinding"/>
 */
public class AStarPathfindingImpl implements PathfindingAlgorithm {
    private final Queue<AStarPathNode> openSet;
    private final Set<AStarPathNode> closedSet;
    private final DistanceHeuristic distanceHeuristic;

    /**
     * Constructs the A* algorithm.
     *
     * @param distanceHeuristic the heuristic to use for distance estimation
     */
    public AStarPathfindingImpl(final DistanceHeuristic distanceHeuristic) {
        this.openSet = new PriorityQueue<>(Comparator.comparingDouble(AStarPathNode::getCostF));
        this.closedSet = new HashSet<>();
        this.distanceHeuristic = distanceHeuristic;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PathResult findPath(final PathNodeGrid pathNodeGrid, final PathNode startPathNode, final PathNode endPathNode) {
        final var nodes = pathNodeGrid.getNodes().stream()
                .map(AStarPathNode::new)
                .toList();

        final var startTemp = new AStarPathNode(startPathNode);
        final var endTemp = new AStarPathNode(endPathNode);

        final var start = nodes.stream()
                .filter(node -> node.equals(startTemp))
                .toList()
                .get(0);

        final var end = nodes.stream()
                .filter(node -> node.equals(endTemp))
                .toList()
                .get(0);

        this.openSet.clear();
        this.closedSet.clear();

        nodes.forEach(node -> {
                node.setCostH(this.distanceHeuristic.calculateDistance(node.getPosition(), end.getPosition()));
                node.setCostF(node.getCostG() + node.getCostH());
        });

        start.setCostG(0.0f);
        start.setCostH(this.distanceHeuristic.calculateDistance(start.getPosition(), end.getPosition()));
        start.setCostF(start.getCostG() + start.getCostH());

        end.setCostH(0.0f);
        end.setCostF(start.getCostG() + start.getCostH());

        this.openSet.add(start);

        while (!this.openSet.isEmpty()) {
            final var current = this.openSet.poll();

            if (current.equals(end)) {
                final var path = new ArrayList<AStarPathNode>();
                var currentNode = current;
                while (currentNode != null) {
                    path.add(0, currentNode);
                    currentNode = currentNode.getParentNode();
                }
                return new PathResult(path.stream()
                        .map(AStarPathNode::getPathNode)
                        .toList(), path.getLast().getCostG());
            }

            this.closedSet.add(current);

            pathNodeGrid.getNeighborsPositionsOfNode(current.getPathNode())
                    .forEach(neighborPosition -> {
                        final var neighbor = nodes.stream()
                                .filter(node ->
                                        (int) node.getPosition().x() == neighborPosition.x()
                                     && (int) node.getPosition().y() == neighborPosition.y())
                                .findFirst()
                                .get();

                        if (this.closedSet.contains(neighbor)) {
                            return;
                        }

                        final var newDistance = this.distanceHeuristic
                                .calculateDistance(current.getPosition(), neighbor.getPosition());
                        final var newCostG = current.getCostG() + neighbor.getCost() + newDistance;

                        if (!this.openSet.contains(neighbor) || newCostG < neighbor.getCostG()) {
                            neighbor.setParentNode(current);
                            neighbor.setCostG(newCostG);
                            neighbor.setCostH(this.distanceHeuristic
                                    .calculateDistance(neighbor.getPosition(), end.getPosition()));
                            neighbor.setCostF(neighbor.getCostG() + neighbor.getCostH());

                            this.openSet.remove(neighbor);
                            this.openSet.add(neighbor);
                        }
                    });
        }

        return new PathResult(List.of(), 0.0f);
    }

    private static final class AStarPathNode {
        private final PathNode pathNode;
        private AStarPathNode parentNode;
        private float costG;
        private float costH;
        private float costF;

        AStarPathNode(final PathNode pathNode) {
            this.pathNode = pathNode;
            this.costG = Float.POSITIVE_INFINITY;
            this.costH = Float.POSITIVE_INFINITY;
            this.costF = Float.POSITIVE_INFINITY;
            this.parentNode = null;
        }

        @Override
        public boolean equals(final Object obj) {
            if (obj == null) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            if (obj instanceof AStarPathNode) {
                final AStarPathNode other = (AStarPathNode) obj;
                return this.getPathNode().getPosition().x() == other.getPathNode().getPosition().x()
                        && this.getPathNode().getPosition().y() == other.getPathNode().getPosition().y();
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.getPathNode().getPosition());
        }

        public PathNode getPathNode() {
            return this.pathNode;
        }

        public Vector2D getPosition() {
            return new Vector2D(pathNode.getX(), pathNode.getY());
        }

        public float getCost() {
            return pathNode.getCost();
        }

        public float getCostG() {
            return costG;
        }

        public float getCostH() {
            return costH;
        }

        public float getCostF() {
            return costF;
        }

        public AStarPathNode getParentNode() {
            return this.parentNode;
        }

        public void setCostG(final float costG) {
            this.costG = costG;
        }

        public void setCostH(final float costH) {
            this.costH = costH;
        }

        public void setCostF(final float costF) {
            this.costF = costF;
        }

        public void setParentNode(final AStarPathNode parentNode) {
            this.parentNode = parentNode;
        }
    }
}
