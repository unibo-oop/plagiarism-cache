package util.mapbuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javafx.util.Pair;
import util.Coordinates;

/**
 * This abstract class is the starting point for the GameMap builders
 * implementations that use CaseBulder to set the variable part of the map.
 *
 * @param <X> the type of the returned element of the build method
 * @param <Y> the type of the cells in the map
 */
public abstract class AbstractGameMapBuilderUsingCaseBuilders<X, Y> implements GameMapBuilder<X> {

    private final Map<Coordinates, CaseBuilder<Y>> map;
    private final Pair<Integer, Integer> size;

    /**
     * 
     * @param size                the size of the map
     * @param caseBuilderGenerator the supplier of the CaseBuilders to use
     */
    public AbstractGameMapBuilderUsingCaseBuilders(final Pair<Integer, Integer> size,
            final Function<Coordinates, CaseBuilder<Y>> caseBuilderGenerator) {
        this.map = new HashMap<>();
        this.size = size;
        for (int i = 0; i < this.size.getKey(); i++) {
            for (int j = 0; j < this.size.getValue(); j++) {
                final Coordinates cords = new Coordinates(i, j);
                this.map.put(cords, caseBuilderGenerator.apply(cords));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameMapBuilder<X> setTop(final Coordinates cords, final String id) {
        this.map.get(cords).setTop(id);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameMapBuilder<X> setBottom(final Coordinates cords, final String id) {
        this.map.get(cords).setBottom(id);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameMapBuilder<X> setBorder(final Coordinates cords, final String id) {
        this.map.get(cords).setBorder(id);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract X build();

    /**
     * 
     * @return the actual state of the map to be built
     */
    protected Map<Coordinates, CaseBuilder<Y>> getMap() {
        return new HashMap<>(this.map);
    }

    /**
     * 
     * @return the size of the map
     */
    protected Pair<Integer, Integer> getSize() {
        return this.size;
    }

}
