package model.construction;

import javafx.util.Pair;

/**
 * This class models every kind of building except the road.
 */
public final class Building implements Construction {
    /**
     * The type of Building.
     */
    private final ConstructionType type;
    /**
     * The position of the building on the Game map.
     */
    private final Pair<Integer, Integer> position;

    /**
     * Default constructor.
     * @param type
     *          The type of the building to be created.
     * @param position
     *          The position of the building on the Game map.
     */
    public Building(final ConstructionType type, final Pair<Integer, Integer> position) {
        this.type = type;
        this.position = position;
    }

    @Override
    public ConstructionType getType() {
        return this.type;
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return this.position;
    }

    @Override
    public boolean isReachedByRoad() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isLocked() {
        return this.isReachedByRoad(); //Block also if there aren't enough resources.
    }
}
