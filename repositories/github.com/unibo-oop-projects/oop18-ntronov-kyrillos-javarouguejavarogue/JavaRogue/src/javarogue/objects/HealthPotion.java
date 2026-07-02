package javarogue.objects;

import javarogue.behaviourmodules.StatNames;
import javarogue.tileengine.Tile;
import javarogue.utility.Position;

public class HealthPotion extends GameObject {

	public HealthPotion(Position position) {
		super(position, ObjectType.HEALTH_POTION, Tile.POTION);
		// Fill the StatIncrement hashmap
		this.StatsIncrement.put(StatNames.HP, 10);
	}

	@Override
	public void DoAction() {
		// DO NOTHING
		
	}

}
