package hollowmen.enumerators;

public enum RoomEntityName {

	HERO,
	DOOR,
	DOOR_BACK,
	SLIME,
	BAT,
	PUPPET,
	TREASURE,
	BULLET;
	
	@Override
	public String toString(){
		return this.name().toLowerCase();
	}
	
}
