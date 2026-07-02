package javarogue.objects;

import javarogue.tileengine.Tile;
import javarogue.utility.Position;

public class Chest extends GameObject {

	protected GameObject Content = null;
	
	public Chest(Position position) {
		super(position, ObjectType.CHEST, Tile.CHEST);
		// Fills the chest with an object
		Content = ObjectFactory.GetInstance().CreateObject(ObjectType.randomType(), position);
	}

	public GameObject GetContent() {
		// TODO
		// Implement a "key" to open the chest
		return Content;
	}
	
	@Override
	public void DoAction() {
		
	}

}
