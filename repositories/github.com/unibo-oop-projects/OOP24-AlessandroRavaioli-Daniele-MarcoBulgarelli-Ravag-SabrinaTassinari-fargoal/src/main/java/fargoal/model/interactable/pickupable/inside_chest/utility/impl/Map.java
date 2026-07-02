package fargoal.model.interactable.pickupable.inside_chest.utility.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fargoal.model.interactable.pickupable.inside_chest.utility.api.AbstractUtility;
import fargoal.model.interactable.pickupable.inside_chest.utility.api.UtilityType;
import fargoal.model.manager.api.FloorManager;

/**
 * This class implements a map, which the player can found in a chest.
 * The map reveal a determined floor (between first floor and twenth floor) and when the player 
 * goes in that specific floor he knows all the element in it.
 */
public class Map extends AbstractUtility {

    private static final int MAX_MAP_LEVEL = 19;
    private final List<Integer> listOfMaps;
    private final Random random;

    /**
     * This is the constructor of the class. It store right away the map and it set the level it reveal.
     */
    public Map() {
        this.random = new Random();
        listOfMaps = new ArrayList<>();
        this.setNumberInInventory(0);
    }

    /**
     * A private method that calculates the map level.
     * @return the level the map reveal.
     */
    private int getMapLevel() {
        return this.random.nextInt(MAX_MAP_LEVEL) + 1;
    }

    /** {@inheritDoc} */
    @Override
    public String getChestItemName() {
        return UtilityType.MAP.getName();
    }

    /** {@inheritDoc} */
    @Override
    public void store() {
        this.addUtility(this.getMapLevel());
    }

    /** {@inheritDoc} */
    @Override
    public int getNumberInInventory() {
        return this.listOfMaps.size();
    }

    /**
     * Getter for the list of the maps the player has.
     * @return the list of the maps.
     */
    public List<Integer> getListOfMaps() {
        return List.copyOf(this.listOfMaps);
    }

    /**
     * This method add an utility in the player's inventory.
     * @param floorNumber - the number of the level the map refers to.
     */
    private void addUtility(final Integer floorNumber) {
        if (floorNumber == null || floorNumber <= 0) {
            throw new IllegalArgumentException("Floor number cannot be a negative, null or 0 value.");
        }
        if (!this.listOfMaps.contains(floorNumber)) {
            this.listOfMaps.add(floorNumber);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void removeUtility() {
        this.listOfMaps.removeFirst();
    }

    /**
     * This method removes a map of a specific level from the player's inventory.
     * @param floorNumber - it contains all the element of the floor.
     * @return true if the map has been removed, false otherwise.
     */
    public boolean removeMapLevel(final Integer floorNumber) {
        if (floorNumber == null || floorNumber <= 0) {
            throw new IllegalArgumentException("Floor number cannot be a negative, null or 0 value.");
        }
        if (this.listOfMaps.contains(floorNumber)) {
            this.listOfMaps.remove(floorNumber);
            return true;
        } else {
            return false;
        }
    }

    /** {@inheritDoc} */
    @Override
    public void effect(final FloorManager floorManager) {
    }

    /** {@inheritDoc} */
    @Override
    public void addToPlayer() {
    }

}
