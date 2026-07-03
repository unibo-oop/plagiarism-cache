package model;

import java.util.Map;
import java.util.Optional;

/**
 * Given a map, return a key which reflect a specific distribution (if exists).
 * @param <Z> numeric type of map values
 */
interface DistributionStrategy<Z extends Number> {

    <T> Optional<T> computeDistribution(Map<T, Z> m);
}
