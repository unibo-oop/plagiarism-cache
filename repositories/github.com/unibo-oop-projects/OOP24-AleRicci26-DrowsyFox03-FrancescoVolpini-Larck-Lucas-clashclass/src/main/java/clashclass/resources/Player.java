package clashclass.resources;

import clashclass.elements.troops.TroopType;


import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

/**
 * Class that provide the player with different resources.
 */
public class Player {
    private static final int GENERIC_VALUE = 1_000_000;
    private final Map<ResourceType, ResourceManager>
            playerResources = new EnumMap<>(ResourceType.class);
    private final Map<TroopType, Integer> armyCampTroops = new EnumMap<>(TroopType.class);

    /**
     * Constructs the player.
     */
    public Player() {
        final ResourceManager goldManager = new ResourceManagerImpl(GENERIC_VALUE);
        final ResourceManager elixirManager = new ResourceManagerImpl(GENERIC_VALUE);
        final ResourceManager gemsManager = new ResourceManagerImpl(GENERIC_VALUE);

        playerResources.put(ResourceType.GOLD, goldManager);
        playerResources.put(ResourceType.ELIXIR, elixirManager);
        playerResources.put(ResourceType.GEMS, gemsManager);
    }

    /**
     * Gets the player resources map.
     *
     * @return the player resources map
     */
        public Map<ResourceType, ResourceManager> getPlayerResources() {
        return playerResources;
    }

    /**
     * Adds a certain amount of troops to the player army camp.
     *
     * @param troopType the type of the troops
     * @param count the number of the troops
     */
    public void addArmyCampTroop(final TroopType troopType, final int count) {
        if (this.armyCampTroops.containsKey(troopType)) {
            this.armyCampTroops.put(troopType, this.armyCampTroops.get(troopType) + count);
            return;
        }
        this.armyCampTroops.put(troopType, count);
    }

    /**
     * Removes a certain amount of troops from the player army camp.
     *
     * @param troopType the type of the troops
     * @param count the number of the troops
     */
    public void removeArmyCampTroop(final TroopType troopType, final int count) {
        if (this.armyCampTroops.containsKey(troopType)) {
            final var newCount = Math.max(this.armyCampTroops.get(troopType) - count, 0);
            this.armyCampTroops.put(troopType, newCount);
        }
    }

    /**
     * Checks if the army camp has a certain type of troop.
     *
     * @param troopType the type of the troop.
     *
     * @return true if the army camp has that type of troop
     */
    public boolean hasArmyCampTroop(final TroopType troopType) {
        return this.armyCampTroops.containsKey(troopType)
            && this.armyCampTroops.get(troopType) > 0;
    }

    /**
     * Gets all the troop types in the army camp.
     *
     * @return a set of all the troop types in the army camp
     */
    public Set<TroopType> getArmyCampTroopTypes() {
        return this.armyCampTroops.keySet();
    }

    /**
     * Gets the amount of troops of a given type in the army camp.
     *
     * @param troopType the troop type
     *
     * @return the amount of troops of a given type in the army camp
     */
    public int getArmyCampTroopCount(final TroopType troopType) {
        if (!this.armyCampTroops.containsKey(troopType)) {
            return 0;
        }
        return this.armyCampTroops.get(troopType);
    }
}
