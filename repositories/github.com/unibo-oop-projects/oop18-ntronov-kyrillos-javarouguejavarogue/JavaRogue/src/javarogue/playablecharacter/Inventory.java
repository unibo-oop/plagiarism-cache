package javarogue.playablecharacter;

import java.util.List;

import javarogue.objects.GameObject;

/**
 * Interface that allows the character to have an inventory.
 * Subject interface of an Observer pattern via the equip() method. The observer is the class having the inventory.
 *
 */
public interface Inventory {

	/**
	 * the inventory.
	 * @return the inventory.
	 */
	List<GameObject> getInventory();
	/**
	 * allows the character to equip an item.
	 * @param toEquip the object to be equipped
	 */
	void equip(GameObject toEquip);
	/**
	 * adds a looted GameObject to the inventory.
	 * @param looted the object looted-
	 */
	void loot(GameObject looted);
	/**
	 * registers a character. Add method of the subject-
	 * @param character the character
	 */
	void addCharacter(PlayableCharacter character);
	/**
	 * the character
	 * @return the character
	 */
	PlayableCharacter getCharacter();
}
