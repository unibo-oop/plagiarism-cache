package fargoal.model.entity.player.api;

import java.util.List;
import java.util.Map;

import fargoal.model.interactable.pickupable.inside_chest.spell.api.Spell;
import fargoal.model.interactable.pickupable.inside_chest.spell.impl.DriftSpell;
import fargoal.model.interactable.pickupable.inside_chest.spell.impl.InvisibilitySpell;
import fargoal.model.interactable.pickupable.inside_chest.spell.impl.LightSpell;
import fargoal.model.interactable.pickupable.inside_chest.spell.impl.RegenerationSpell;
import fargoal.model.interactable.pickupable.inside_chest.spell.impl.ShieldSpell;
import fargoal.model.interactable.pickupable.inside_chest.spell.impl.TeleportSpell;
import fargoal.model.interactable.pickupable.inside_chest.utility.impl.Beacon;
import fargoal.model.interactable.pickupable.inside_chest.utility.impl.EnchantedWeapon;
import fargoal.model.interactable.pickupable.inside_chest.utility.impl.HealingPotion;
import fargoal.model.interactable.pickupable.inside_chest.utility.impl.MagicSack;

/**
 * Represents the player's inventory, managing various items, spells and resources.
 * <p>
 * Implementing classes should provide mechanisms to track and modify 
 * the player's inventory dynamically during gameplay.
 * </p>
 */
public interface Inventory {

    /**
     * Method that returns the number of {@link HealingPotion}
     * that the player has.
     * 
     * @return - the number of {@link HealingPotion} in the inventory
     */
    Integer numberHealingPotions();

    /**
     * Method to use an {@link HealingPotion} that is in {@link Inventory}.
     */
    void useHealingPotion();

    /**
     * Method to add an {@link HealingPotion} in the {@link Inventory}.
     */
    void removeHealingPotion();

    /**
     * Method to remove an {@link HealingPotion} from the {@link Inventory}.
     */
    void addHealingPotion();

    /**
     * Method that returns the number of {@link Beacon}
     * that the player has.
     * 
     * @return - the number of {@link Beacon} in the inventory
     */
    Integer numberBeacons();

    /**
     * Method to use a {@link Beacon} that is in {@link Inventory}.
     */
    void useBeacon();

    /**
     * Method to remove a {@link Beacon} from the {@link Inventory}.
     */
    void removeBeacon();

    /**
     * Method to add a {@link Beacon} in the {@link Inventory}.
     */
    void addBeacon();

    /**
     * Method that returns the number of {@link MagicSack}
     * that the player has.
     * 
     * @return - the number of {@link MagicSack} in the inventory
     */
    Integer numberMagicSacks();

    /**
     * Method to add a {@link MagicSack} in the {@link Inventory}.
     */
    void addMagicSack();

    /**
     * Method that returns the number of {@link EnchantedWeapon}
     * that the player has.
     * 
     * @return - the number of {@link EnchantedWeapon} in the inventory
     */
    Integer numberEnchantedWeapons();

    /**
     * Method to add a {@link EnchantedWeapon} in the {@link Inventory}.
     */
    void addEnchantedWeapon();

    /**
     * Method that returns the number of map that the player 
     * has found.
     * 
     * @return - the number of map that the player has found
     */
    Integer numberOfMaps();

    /**
     * Method that adds a map in the list of the maps in the {@link Inventory}.
     */
    void addMap();

    /**
     * Method that removes a map in the list of maps in the {@link Inventory}.
     */
    void removeMap();

    /**
     * Method that returns if the list of maps contains the map
     * of the level given.
     * 
     * @param level - the level it checks if there's the map or not
     * @return true if there's the map of this level, false otherwise
     */
    boolean hasMap(Integer level);

    /**
     * Method that returns if there are no maps in the {@link Inventory}.
     * 
     * @return true if there are no maps, false otherwise
     */
    boolean isEmpty();

    /**
     * Method that returns a copy of the list of the maps
     * contained in the {@link Inventory}.
     * 
     * @return a copy of the list of the maps contained in {@link Inventory}
     */
    List<Integer> getListOfMaps();

    /**
     * Retrieves the current status of casted spells.
     * 
     * @return a {@link Map} where the {@code keys} are spell names and the {@code values} indicate
     * whether the spell is currently active (true) or not (false).
    */
    Map<String, Boolean> getSpellCasted();


    /**
     * Method that returns the number of {@link InvisibilitySpell}
     * that the player has.
     * 
     * @return - the number of {@link InvisibilitySpell} in the inventory
     */
    Integer numberInvisibilitySpells();

    /**
     * Method to add an {@link InvisibilitySpell} in the {@link Inventory}.
     */
    void addInvisibilitySpell();

    /**
     * Method to remove an {@link InvisibilitySpell} from the {@link Inventory}.
     */
    void removeInvisibilitySpell();

    /**
     * Method to use an {@link InvisibilitySpell} that is in {@link Inventory}.
     */
    void useInvisibilitySpell();

    /**
     * Method that returns the number of {@link TeleportSpell}
     * that the player has.
     * 
     * @return - the number of {@link TeleportSpell} in the inventory
     */
    Integer numberTeleportSpells();

    /**
     * Method to add a {@link TeleportSpell} in the {@link Inventory}.
     */
    void addTeleportSpell();

    /**
     * Method to remove a {@link TeleportSpell} from the {@link Inventory}.
     */
    void removeTeleportSpell();

    /**
     * Method to use a {@link TeleportSpell} that is in {@link Inventory}.
     */
    void useTeleportSpell();

    /**
     * Method that returns the number of {@link ShieldSpell}
     * that the player has.
     * 
     * @return - the number of {@link ShieldSpell} in the inventory
     */
    Integer numberShieldSpells();

    /**
     * Method to add a {@link ShieldSpell} in the {@link Inventory}.
     */
    void addShieldSpell();

    /**
     * Method to remove a {@link ShieldSpell} from the {@link Inventory}.
     */
    void removeShieldSpell();

    /**
     * Method to use a {@link ShieldSpell} that is in {@link Inventory}.
     */
    void useShieldSpell();

    /**
     * Method that returns the number of {@link RegenerationSpell}
     * that the player has.
     * 
     * @return - the number of {@link RegenerationSpell} in the inventory
     */
    Integer numberRegenerationSpell();

    /**
     * Method to add a {@link RegenerationSpell} in the {@link Inventory}.
     */
    void addRegenerationSpell();

    /**
     * Method to remove a {@link RegenerationSpell} from the {@link Inventory}.
     */
    void removeRegenerationSpell();

    /**
     * Method to use a {@link RegenerationSpell} that is in {@link Inventory}.
     */
    void useRegenerationSpell();

    /**
     * Method that returns the number of {@link DriftSpell}
     * that the player has.
     * 
     * @return - the number of {@link DriftSpell} in the inventory
     */
    Integer numberDriftSpells();

    /**
     * Method to add a {@link DriftSpell} in the {@link Inventory}.
     */
    void addDriftSpell();

    /**
     * Method to remove a {@link DriftSpell} from the {@link Inventory}.
     */
    void removeDriftSpell();

    /**
     * Method to use a {@link DriftSpell} that is in {@link Inventory}.
     */
    void useDriftSpell();

    /**
     * Method that returns the number of {@link LightSpell}
     * that the player has.
     * 
     * @return - the number of {@link LightSpell} in the inventory
     */
    Integer numberLightSpells();

    /**
     * Method to add a {@link LightSpell} in the {@link Inventory}.
     */
    void addLightSpell();

    /**
     * Method to remove a {@link LightSpell} from the {@link Inventory}.
     */
    void removeLightSpell();

    /**
     * Method to use a {@link LightSpell} that is in {@link Inventory}.
     */
    void useLightSpell();

    /**
     * Method to turn the light on if the light is off, and 
     * otherwise it turn light off if the light is on.
     */
    void turnLight();

    /**
     * This method returns a {@link List} of all types of spells in the inventory.
     * 
     * @return a {@link List} of spells.
     */
    List<Spell> getListAllSpell();

    /**
     * This method returns if the {@link Player} has some spells
     * in the inventory or not.
     * 
     * @return {@code true} if the {@link Player} has spells in {@link Inventory}, {@code false} otherwise.
     */
    boolean areThereSpells();
}
