package javarogue.playablecharacter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javarogue.objects.GameObject;

public class InventoryImpl implements Inventory {
	
	private final List<GameObject> inventoryList;
	private PlayableCharacter character;

	
	public InventoryImpl() {
		this.inventoryList = new ArrayList<GameObject>();
	}
	
	@Override
	public List<GameObject> getInventory() {
		return Collections.unmodifiableList(this.inventoryList);
	}

	@Override
	public void equip(GameObject toEquip) {
		if (this.inventoryList.contains(toEquip)) {
			this.character.equipObject(toEquip);
			this.inventoryList.remove(this.inventoryList.indexOf(toEquip));
		}
	}

	@Override
	public void loot(GameObject looted) {
		this.inventoryList.add(looted);
		this.getCharacter().addObjectToInventory(looted);
	}

	@Override
	public void addCharacter(PlayableCharacter character) {
		this.character = character;
	}
	
	@Override
	public PlayableCharacter getCharacter() {
		return this.character;
	}

}
