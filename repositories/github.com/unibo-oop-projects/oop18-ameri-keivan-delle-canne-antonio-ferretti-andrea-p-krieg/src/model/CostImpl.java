package model;

import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import model.resources.Resource;

/**
 * The CostImp is a class that implements Cost. This class implements all Cost's
 * methods, so there is a cost that can be get in its normal form or in its
 * string form. This class has an optional map which assigns a value to a
 * specific type of resource. If the map isn't present, the cost is equal to
 * something free.
 */
public class CostImpl implements Cost {

    private static final String FREE = "Free";

    private final Optional<Map<Resource, Integer>> cost;

    /**
     * Constructor.
     * 
     * @param cost is an optional map which assigns a value to a specific type of
     *             resource. If the map isn't present, the cost is equal to
     *             something free.
     */
    public CostImpl(final Optional<Map<Resource, Integer>> cost) {
        this.cost = cost;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Map<Resource, Integer>> getCost() {
        return this.cost;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (!cost.isPresent()) {
            return FREE;
        } else {
            String result = "";
            boolean first = true;
            for (final Entry<Resource, Integer> e : getCost().get().entrySet().stream()
                    .sorted((e1, e2) -> e1.getKey().getName().compareTo(e2.getKey().getName()))
                    .collect(Collectors.toList())) {
                if (first) {
                    result += (e.getValue() + " " + e.getKey().getName());
                    first = false;
                } else {
                    result += (", " + e.getValue() + " " + e.getKey().getName());
                }
            }
            return result;
        }
    }

}
