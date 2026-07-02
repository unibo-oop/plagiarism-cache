package it.unibo.model.persistence.api;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The entire game state to be saved.
 */
public class SaveState {

    private final int coins;
    private final int highestScore;
    private final Set<String> ownedItems;
    private final Map<String, Integer> consumables;
    private final String selectedSkin;
    private final int selectedJumpLevel;
    private final int selectedSpeedLevel;

    /**
     * Construct a SaveState with all the required information.
     * 
     * @param coins              number of coin collected
     * @param highestScore       the highest score of the player
     * @param ownedItems         a set of owned item IDs
     * @param consumables        a map of consumable item IDs to their remaining
     *                           matches
     * @param selectedSkin       a String containing the selected skin ID.
     * @param selectedJumpLevel  a int containing the selected level of jump
     *                           power-up level.
     * @param selectedSpeedLevel a int containing the selected level of speed
     *                           power-up level.
     */
    public SaveState(final int coins, final int highestScore, final Set<String> ownedItems,
            final Map<String, Integer> consumables, final String selectedSkin, final int selectedJumpLevel,
            final int selectedSpeedLevel) {
        this.coins = coins;
        this.highestScore = highestScore;
        this.ownedItems = new HashSet<>(ownedItems);
        this.consumables = new HashMap<>(consumables);
        this.selectedSkin = selectedSkin;
        this.selectedJumpLevel = selectedJumpLevel;
        this.selectedSpeedLevel = selectedSpeedLevel;
    }

    /**
     * @return number of coin collected
     */
    public int getCoins() {
        return this.coins;
    }

    /**
     * @return the highest score of the player
     */
    public int getHighestScore() {
        return this.highestScore;
    }

    /**
     * @return a set of owned item IDs
     */
    public Set<String> getOwnedItems() {
        return Set.copyOf(this.ownedItems);
    }

    /**
     * @return a map of consumable item IDs to their remaining matches
     */
    public Map<String, Integer> getConsumables() {
        return Map.copyOf(this.consumables);
    }

    /**
     * @return a String containing the selected skin ID.
     */
    public String getSelectedSkin() {
        return this.selectedSkin;
    }

    /**
     * @return a int containing the selected level of jump power level.
     */
    public int getSelectedJumpLevel() {
        return this.selectedJumpLevel;
    }

    /**
     * @return a int containing the selected level of speed power level.
     */
    public int getSelectedSpeedLevel() {
        return this.selectedSpeedLevel;
    }

}
