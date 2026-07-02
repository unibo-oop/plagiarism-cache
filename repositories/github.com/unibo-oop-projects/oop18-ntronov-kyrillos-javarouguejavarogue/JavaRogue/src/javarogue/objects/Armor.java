package javarogue.objects;

import javarogue.behaviourmodules.StatNames;
import javarogue.tileengine.Tile;
import javarogue.utility.Position;

public class Armor extends GameObject {

	protected int Level = 1;
	
	public Armor(Position position) {
		super(position, ObjectType.ARMOR_1, Tile.ARMOR);
		// Fill the StatIncrement hashmap
		this.StatsIncrement.put(StatNames.DEFENSE, 5 * Level);
	}
	
	public void SetLevel(int level) {
		this.Level = level;
	}
	
	public int GetLevel() {
		return this.Level;
	}

	@Override
	public void DoAction() {
		// Do nothing
	}

}
