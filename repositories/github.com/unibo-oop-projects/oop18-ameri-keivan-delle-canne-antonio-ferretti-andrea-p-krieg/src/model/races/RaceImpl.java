package model.races;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import model.Cost;
import model.objects.unit.UnitType;

/**
 * The RaceImpl class implements Race interface. It represents the "skeleton" of
 * a race and is extended by all the classes that represent a true race.
 */
public class RaceImpl implements Race {

    private static final int STR_INDEX = 0;
    private static final int HP_INDEX = 1;
    private static final int MOV_RANGE_INDEX = 2;
    private static final int ATT_RANGE_INDEX = 3;
    private static final int POSSIBLE_ATT_INDEX = 4;

    private final Map<UnitType, List<Optional<Integer>>> unitStatsBoost;
    private final Map<UnitType, Cost> unitCostBoost;
    private final String name;

    /**
     * RaceImpl constructor.
     * 
     * @param name           is the race's name.
     * @param unitStatsBoost is a map that contains the stats boosts of the unit
     *                       types.
     * @param unitCostBoost  is a map that contains the cost boosts of the unit
     *                       types.
     */
    public RaceImpl(final String name, final Map<UnitType, List<Optional<Integer>>> unitStatsBoost,
            final Map<UnitType, Cost> unitCostBoost) {
        this.name = name;
        this.unitStatsBoost = unitStatsBoost;
        this.unitCostBoost = unitCostBoost;
    }

    private int getBoost(final UnitType unitType, final int index) {
        return unitStatsBoost.get(unitType).get(index).isPresent() ? unitStatsBoost.get(unitType).get(index).get() : 0;
    }

    /** {@inheritDoc} **/
    @Override
    public String getRaceName() {
        return this.name;
    }

    /** {@inheritDoc} **/
    @Override
    public int getStrBoost(final UnitType unitType) {
        return getBoost(unitType, STR_INDEX);
    }

    /** {@inheritDoc} **/
    @Override
    public int getHpBoost(final UnitType unitType) {
        return getBoost(unitType, HP_INDEX);
    }

    /** {@inheritDoc} **/
    @Override
    public int getMovRangeBoost(final UnitType unitType) {
        return getBoost(unitType, MOV_RANGE_INDEX);
    }

    /** {@inheritDoc} **/
    @Override
    public int getAttRangeBoost(final UnitType unitType) {
        return getBoost(unitType, ATT_RANGE_INDEX);
    }

    /** {@inheritDoc} **/
    @Override
    public int getPossibleAttBoost(final UnitType unitType) {
        return getBoost(unitType, POSSIBLE_ATT_INDEX);
    }

    /** {@inheritDoc} **/
    @Override
    public Cost getCostBoost(final UnitType unitType) {
        return unitCostBoost.get(unitType);
    }

}
