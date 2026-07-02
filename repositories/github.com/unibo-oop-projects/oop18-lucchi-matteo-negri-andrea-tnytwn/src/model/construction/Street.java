package model.construction;

import javafx.util.Pair;

/**
 *
 */
public final class Street implements Construction {
    /**
     * 
     */
    private final Pair<Integer, Integer> position;

    /**
     * Default constructor.
     * @param position
     *          The position of the Game map where the street is being built.
     */
    public Street(final Pair<Integer, Integer> position) {
        this.position = position;
    }

    @Override
    public ConstructionType getType() {
        return ConstructionType.STRADA;
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return this.position;
    }

    @Override
    public boolean isReachedByRoad() {
        //Need also to check
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isLocked() {
        return false;
    }
}
