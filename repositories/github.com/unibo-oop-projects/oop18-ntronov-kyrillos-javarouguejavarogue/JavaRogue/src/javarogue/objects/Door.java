package javarogue.objects;

import javarogue.tileengine.Tile;
import javarogue.utility.Position;

public class Door extends GameObject {

	protected boolean IsOpen = false;
	
	public Door(Position position, boolean horizontal) {
		super(position, ObjectType.DOOR, Tile.DOOR_VERT);
		if(horizontal) {
			this.tile = Tile.DOOR_HORZ;
		}
	}
	
	@Override
	public ObjectState GetState() {
		return this.state;
	}

	@Override
	public void DoAction() {
		IsOpen = !IsOpen;
		this.tile = Tile.ALPHA;
	}


}
