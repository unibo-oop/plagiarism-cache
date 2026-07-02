package it.unibo.monopoli.model.table;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represent the classic implementation of game's {@link Land}'s
 * {@link Group}.
 *
 */
public class ClassicLandGroup extends ClassicGroup implements LandGroup {

    private final List<Building> buildings;

    /**
     * Constructs an instance of {@link ClassicLandGroup}s. It needs a name and
     * all the {@link Ownership}s belonging to its.
     * 
     * @param name
     *            - {@link Group}'s name
     * @param members
     *            - {@link Group}'s members
     */
    public ClassicLandGroup(final String name, final Ownership... members) {
        super(name, members);
        this.buildings = new LinkedList<>();
    }

    @Override
    public List<Building> getBuildings() {
        return this.buildings;
    }

    @Override
    public void addBuilding(final Building building) {
        this.buildings.add(building);
    }

    @Override
    public void removeBuilding(final Building building) {
        this.buildings.remove(building);
    }

    @Override
    public boolean canBuiling() {
        return this.buildings.isEmpty() ? true : this.buildings.get(0) instanceof Home;
    }

}