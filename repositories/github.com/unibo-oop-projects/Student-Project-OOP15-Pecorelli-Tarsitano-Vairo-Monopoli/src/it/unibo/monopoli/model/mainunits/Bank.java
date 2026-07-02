package it.unibo.monopoli.model.mainunits;

import java.util.List;

import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.Ownership;

/**
 * This interface represent the game's bank. Usually is here that are kept all
 * the money, {@link Building}s and {@link Ownership}s at the beginning of the
 * game.
 *
 */
public interface Bank extends Owner {

    /**
     * Sets the {@link Ownership}s belonging to the {@link Bank}.
     * 
     * @param ownerships
     *            - the {@link List} of {@link Ownership}s belonging to the
     *            {@link Bank}
     */
    void setOwnerships(List<Ownership> ownerships);

    /**
     * Return a {@link List} of the remained {@link Ownership}s.
     * 
     * @return a {@link List} of {@link Ownership}s
     */
    List<Ownership> getLeftOwnership();

    /**
     * Return a {@link List} of the remained {@link Building}s.
     * 
     * @return a {@link List} of {@link Building}s
     */
    List<Building> getLeftBuilding();

    /**
     * Add a new {@link Ownership} to those of the bank.
     * 
     * @param ownership
     *            - the one to add
     */
    void addOwnership(Ownership ownership);

    /**
     * Returns the {@link Ownership} with the ID in input and removes it from
     * the {@link Bank}.
     * 
     * @param ownershipsIndex
     *            - the ID of the {@link Ownership}
     * @return the specific {@link Ownership}
     */
    Ownership getOwnership(int ownershipsIndex);

    /**
     * Returns a random {@link Ownership} from those belonging to the
     * {@link Bank} and removes it from the {@link Bank}.
     * 
     * @return a random {@link Ownership}
     */
    Ownership getOwnership();

    /**
     * Add a new {@link Building} to those of the bank.
     * 
     * @param building
     *            - the one to add
     */
    void addBuilding(Building building);

    /**
     * Returns a {@link Building} of the same type as the input and removes it
     * from the {@link Bank}.
     * 
     * @param buildingsType
     *            - the type of {@link Building} to get
     * @return a {@link Bank}'s {@link Building} of the same type as the input
     */
    Building getBuilding(Building buildingsType);

}
