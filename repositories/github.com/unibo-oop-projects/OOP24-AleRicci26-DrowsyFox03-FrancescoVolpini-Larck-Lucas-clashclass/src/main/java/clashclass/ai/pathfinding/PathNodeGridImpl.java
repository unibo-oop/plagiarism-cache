package clashclass.ai.pathfinding;

import clashclass.commons.VectorInt2D;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents an implementation of PathNodeGrid.
 */
public class PathNodeGridImpl implements PathNodeGrid {
    private final int size;
    private final PathNode[][] nodes;

    /**
     * Constructs the PathNodeGrid.
     *
     * @param size the size of the grid
     * @param nodes the list of nodes
     */
    public PathNodeGridImpl(final int size, final Set<PathNode> nodes) {
        this.size = size;
        this.nodes = new PathNode[size][size];

        nodes.forEach(node -> this.nodes[node.getX()][node.getY()] = node);

        IntStream.range(0, size).forEach(i ->
                IntStream.range(0, size).forEach(j -> {
                    if (this.nodes[i][j] == null) {
                        final var position = new VectorInt2D(i, j);
                        this.nodes[i][j] = new PathNodeImpl(position, 0, Optional.empty());
                    }
                }));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PathNode getNode(final int x, final int y) {
        return this.nodes[x][y];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<PathNode> getNodes() {
        return Arrays.stream(this.nodes)
                .flatMap(Arrays::stream)
                .collect(Collectors.toUnmodifiableSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<PathNode> getNeighborsOfNode(final PathNode node) {
        final var x = node.getX();
        final var y = node.getY();

        final var kernel = List.of(-1, 0, 1);

        return kernel.stream()
                .filter(i -> x + i >= 0 && x + i < size)
                .flatMap(i -> kernel.stream()
                        .filter(j -> y + j >= 0 && y + j < size
                            && !(i == 0 && j == 0) && (i == 0 || j == 0))
                        .map(j -> this.nodes[x + i][y + j]))
                .collect(Collectors.toUnmodifiableSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<VectorInt2D> getNeighborsPositionsOfNode(final PathNode node) {
        return this.getNeighborsOfNode(node).stream()
                .map(PathNode::getPosition)
                .collect(Collectors.toUnmodifiableSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAtPosition(final VectorInt2D position, final int width, final int height) {
        IntStream.range(0, width).forEach(i ->
                IntStream.range(0, height).forEach(j -> {
                    this.nodes[position.x() - i][position.y() - j] = new PathNodeImpl(position, 0, Optional.empty());
                }));
    }
}
