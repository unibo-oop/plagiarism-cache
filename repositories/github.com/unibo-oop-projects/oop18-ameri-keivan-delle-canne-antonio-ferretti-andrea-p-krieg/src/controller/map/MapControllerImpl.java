package controller.map;

import static controller.ModelToViewConverterUtils.modelObjectToViewId;
import static controller.ModelToViewConverterUtils.modelSelectionToViewId;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import model.objects.structures.Structure;
import model.objects.unit.Unit;
import controller.AbstractSecondaryController;
import controller.selection.GameCommandsUsingSelection;
import controller.selection.Selection;
import javafx.util.Pair;
import util.Coordinates;
import util.mapbuilder.GameMapBuilder;
import util.mapbuilder.GameMapBuilderFactory;
import view.UpdatableView;

/**
 * Implementation of the map controller.
 */
public class MapControllerImpl extends AbstractSecondaryController implements MapController {

    private final GameCommandsUsingSelection model;
    private final GameMapBuilderFactory<Map<Coordinates, VariableCasePart>> gameMapBuilderFactory;

    /**
     * @param view  the view related to this controller
     * 
     * @param model the model of the game
     */
    public MapControllerImpl(final UpdatableView view, final GameCommandsUsingSelection model) {
        super(view);
        this.model = model;
        this.gameMapBuilderFactory = () -> new VariablePartMapBuilder(this.model.getMapSize());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Coordinates, String> getTerrains() {
        final Map<Coordinates, String> mapBase = new HashMap<>();
        for (int i = 0; i < this.model.getMapSize().getKey(); i++) {
            for (int j = 0; j < this.model.getMapSize().getValue(); j++) {
                final Coordinates cords = new Coordinates(i, j);
                mapBase.put(cords, modelObjectToViewId(this.model.getTerrain(cords)));
            }
        }
        return mapBase;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Coordinates, VariableCasePart> getActualMapState() {
        final GameMapBuilder<Map<Coordinates, VariableCasePart>> mapBuilder = this.gameMapBuilderFactory.get();
        for (int i = 0; i < this.model.getMapSize().getKey(); i++) {
            for (int j = 0; j < this.model.getMapSize().getValue(); j++) {
                final Coordinates cords = new Coordinates(i, j);
                final Optional<Unit> unit = this.model.getUnit(cords);
                final Optional<Structure> structure = this.model.getStructure(cords);
                final Optional<Selection> selection = this.model.getCaseSelection(cords);
                if (structure.isPresent()) {
                    mapBuilder.setBottom(cords, modelObjectToViewId(structure.get()));
                }
                if (unit.isPresent()) {
                    mapBuilder.setTop(cords, modelObjectToViewId(unit.get()));
                }
                if (selection.isPresent()) {
                    mapBuilder.setBorder(cords, modelSelectionToViewId(selection.get()));
                }
            }
        }
        return mapBuilder.build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cellHit(final Coordinates cords) {
        this.model.selectPosition(cords);
        super.notifyObserver();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Integer, Integer> getMapSize() {
        return this.model.getMapSize();
    }

}
