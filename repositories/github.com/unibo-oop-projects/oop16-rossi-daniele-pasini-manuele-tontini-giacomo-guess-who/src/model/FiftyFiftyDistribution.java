package model;

import java.util.Map;
import java.util.Optional;

/**
 * Strategy to compute the specific key of a map based on a 50/50 distribution.
 */
public class FiftyFiftyDistribution implements DistributionStrategy<Double> {

    @Override
    public <T> Optional<T> computeDistribution(final Map<T, Double> m) {
            return this.computeFiftyFiftyDistribution(m);
    }

    private <T> Optional<T> computeFiftyFiftyDistribution(final Map<T, Double> m) {
        return m.entrySet().stream().sorted((e1, e2) -> 
                ((Double) (Math.abs(0.5 - e1.getValue()))).compareTo((Double) (Math.abs(0.5 - e2.getValue()))))
                .map(x-> x.getKey()).findFirst();
    }
}
