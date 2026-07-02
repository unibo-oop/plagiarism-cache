package javarogue.objects;

import javarogue.behaviourmodules.StatNames;
import javarogue.tileengine.Tile;
import javarogue.utility.Position;

public class Sword extends GameObject {

	protected int Level = 1;
	
	public Sword(Position position) {
		super(position, ObjectType.SWORD_1, Tile.WEAPON);
		// Fill the StatIncrement hashmap
		this.StatsIncrement.put(StatNames.ATTACK, 5 * Level);
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
