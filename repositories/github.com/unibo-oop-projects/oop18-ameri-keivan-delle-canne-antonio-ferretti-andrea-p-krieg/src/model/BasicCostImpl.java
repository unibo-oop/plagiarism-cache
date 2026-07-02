package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import model.resources.BasicResources;
import model.resources.Resource;

/**
 * The BasicCostImpl class is a class that extends CostImpl. It is used to
 * represent a cost formed by only BasicResources resources.
 *
 */
public class BasicCostImpl extends CostImpl {

    /**
     * Constructor.
     * 
     * @param gold       is an optional that can represent the cost of the gold
     *                   resource.
     * @param wood       is an optional that can represent the cost of the wood
     *                   resource.
     * @param population is an optional that can represent the cost of the
     *                   population resource.
     */
    public BasicCostImpl(final Optional<Integer> gold, final Optional<Integer> wood,
            final Optional<Integer> population) {
        super(createCostMap(gold, wood, population));
    }

    /**
     * Constructor. This constructor is used to represent an object without cost.
     */
    public BasicCostImpl() {
        super(Optional.empty());
    }

    private static Optional<Map<Resource, Integer>> createCostMap(final Optional<Integer> gold,
            final Optional<Integer> wood, final Optional<Integer> population) {
        final Map<Resource, Integer> map = new HashMap<>();
        verifyAndAdd(map, BasicResources.GOLD, gold);
        verifyAndAdd(map, BasicResources.WOOD, wood);
        verifyAndAdd(map, BasicResources.POPULATION, population);
        return map.isEmpty() ? Optional.empty() : Optional.of(map);
    }

    private static void verifyAndAdd(final Map<Resource, Integer> map, final Resource resource,
            final Optional<Integer> value) {
        if (value.isPresent() && resource instanceof BasicResources) {
            if (map.containsKey(resource)) {
                map.put(resource, map.get(resource) + value.get());
            } else {
                map.put(resource, value.get());
            }
        }
    }

    /**
     * This method is used to merge the current cost with a passed as a parameter.
     * Of the passed method, during the merge, only the resources of the
     * BasiResources type will be considered.
     * 
     * @param costToMerge is the cost to merge with the actual cost.
     * @return the merged cost.
     */
    public BasicCostImpl mergeBasicCost(final Cost costToMerge) {
        final Optional<Map<Resource, Integer>> cost;
        if (costToMerge.getCost().isPresent()) {
            if (!getCost().isPresent()) {
                cost = Optional.of(new HashMap<>());
            } else {
                cost = getCost();
            }
            costToMerge.getCost().get().entrySet()
                    .forEach(e -> verifyAndAdd(cost.get(), e.getKey(), Optional.of(e.getValue())));
            if (cost.get().size() == 0) {
                return new BasicCostImpl();
            }
        }
        return this;
    }
}
