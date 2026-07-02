package javarogue.objects;

import javarogue.behaviourmodules.StatNames;
import javarogue.tileengine.Tile;
import javarogue.utility.Position;

public class DefensePotion extends GameObject {

	public DefensePotion(Position position) {
		super(position, ObjectType.DEFENSE_POTION, Tile.POTION);
		// Fill the StatIncrement hashmap
		this.StatsIncrement.put(StatNames.DEFENSE, 10);
	}
	
	@Override
	public void DoAction() {
		// TODO Auto-generated method stub
		
	}

}
