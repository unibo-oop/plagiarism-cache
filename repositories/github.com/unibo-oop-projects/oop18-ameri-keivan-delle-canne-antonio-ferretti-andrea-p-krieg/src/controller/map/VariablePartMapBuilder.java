package controller.map;

import java.util.HashMap;
import java.util.Map;

import javafx.util.Pair;
import util.Coordinates;
import util.mapbuilder.AbstractGameMapBuilderUsingCaseBuilders;
import util.mapbuilder.CaseBuilder;

/**
 * builds only the variable part of the map. package protected.
 */
class VariablePartMapBuilder
        extends AbstractGameMapBuilderUsingCaseBuilders<Map<Coordinates, VariableCasePart>, VariableCasePart> {

    VariablePartMapBuilder(final Pair<Integer, Integer> size) {
        super(size, (Coordinates cords) -> new VariableCasePartBuilder());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Coordinates, VariableCasePart> build() {
        final Map<Coordinates, CaseBuilder<VariableCasePart>> mapToBuild = super.getMap();
        final Map<Coordinates, VariableCasePart> result = new HashMap<>();
        for (int i = 0; i < super.getSize().getKey(); i++) {
            for (int j = 0; j < super.getSize().getValue(); j++) {
                final Coordinates cords = new Coordinates(i, j);
                result.put(cords, mapToBuild.get(cords).build());
            }
        }
        return result;
    }

}
