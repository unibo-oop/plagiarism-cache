package model.objectives;

import java.util.Optional;

import model.map.ObservableGameMap;
import model.objects.structures.Structure;
import model.objects.unit.Unit;
import model.player.Player;
import util.Coordinates;

/**
 * This is the standard Objective that is winning if the player is the last one
 * alive.
 */
public class LastStandingObjective extends AbstractObjective {

    private static final String DESCRIPTION = "Remain the only one";

    /**
     * Initialize the class.
     */
    public LastStandingObjective() {
        super(DESCRIPTION);
    }

    /** {@inheritDoc} **/
    @Override
    public boolean isCompleted(final ObservableGameMap actualGameMap, final Player player) {
        Optional<Unit> unit;
        Optional<Structure> structure;
        for (int i = 0; i < actualGameMap.getMapSize().getKey(); i++) {
            for (int j = 0; j < actualGameMap.getMapSize().getValue(); j++) {
                unit = actualGameMap.getUnit(new Coordinates(i, j));
                structure = actualGameMap.getStructure(new Coordinates(i, j));
                if (unit.isPresent() && unit.get().getOwner().isPresent()
                        && !unit.get().getOwner().get().equals(player)) {
                    return false;
                }
                if (structure.isPresent() && structure.get().getOwner().isPresent()
                        && !structure.get().getOwner().get().equals(player)) {
                    return false;
                }
            }
        }
        return true;
    }

}
