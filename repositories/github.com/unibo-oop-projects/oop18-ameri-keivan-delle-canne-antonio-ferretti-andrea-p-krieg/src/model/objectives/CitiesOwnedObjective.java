package model.objectives;

import java.util.Optional;

import model.map.ObservableGameMap;
import model.objects.structures.Capital;
import model.objects.structures.City;
import model.objects.structures.Structure;
import model.player.Player;
import util.Coordinates;

/**
 * The objective of conquering and holding 8 cities simultaneously.
 */
public class CitiesOwnedObjective extends AbstractObjective {

    private static final int NUMBER_OF_CITIES = 8;
    private static final String DESCRIPTION = "Own 8 cities";

    /**
     * Initialize the class.
     */
    public CitiesOwnedObjective() {
        super(DESCRIPTION);
    }

    /** {@inheritDoc} **/
    @Override
    public boolean isCompleted(final ObservableGameMap actualGameMap, final Player player) {
        int cities = 0;
        for (int i = 0; i < actualGameMap.getMapSize().getKey(); i++) {
            for (int j = 0; j < actualGameMap.getMapSize().getValue(); j++) {
                final Optional<Structure> structure = actualGameMap.getStructure(new Coordinates(i, j));
                if (structure.isPresent() && structure.get() instanceof City && !(structure.get() instanceof Capital)
                        && structure.get().getOwner().isPresent() && structure.get().getOwner().get().equals(player)) {
                    cities++;
                }
            }
        }
        return cities >= NUMBER_OF_CITIES ? true : false;
    }

}
