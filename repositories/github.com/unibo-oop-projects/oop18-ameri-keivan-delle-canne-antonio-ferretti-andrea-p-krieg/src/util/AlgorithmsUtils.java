package util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.google.common.graph.MutableGraph;

/**
 * 
 * a static class with useful algorithms.
 */
public final class AlgorithmsUtils {

    private AlgorithmsUtils() {
    }

    /**
     * a BFS traversal from a given source start.
     * 
     * @param <X>   the type of the nodes in the graph
     * @param start the starting node
     * @param graph the graph to traverse
     * @return a map from nodes of the graph to their distance from start
     */
    public static <X> Map<X, Integer> breadthFirstSearch(final X start, final MutableGraph<X> graph) {
        final Map<X, Integer> result = new HashMap<>();
        // Mark all the vertices as not visited(By default
        // set as false)
        final Map<X, Boolean> visited = new HashMap<>();
        graph.nodes().forEach(n -> visited.put(n, false));
        // Create a queue for BFS
        final LinkedList<X> queue = new LinkedList<>();

        // Mark the current node as visited and enqueue it
        visited.put(start, true);
        queue.add(start);
        result.put(start, 0);
        while (queue.size() != 0) {
            // Dequeue a vertex from queue and print it
            final X actual = queue.poll();
            // Get all adjacent vertices of the dequeued vertex actual
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            for (final X node : graph.adjacentNodes(actual)) {
                if (!visited.get(node)) {
                    visited.put(node, true);
                    queue.add(node);
                    result.put(node, result.get(actual) + 1);
                }
            }
        }
        return result;
    }
}
