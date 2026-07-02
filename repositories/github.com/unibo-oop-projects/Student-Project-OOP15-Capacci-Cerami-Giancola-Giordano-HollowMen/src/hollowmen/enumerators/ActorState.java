package hollowmen.enumerators;

public enum ActorState {

	JUMPING,
	ATTACKING,
	STANDING,
	MOVING;
	
	@Override
	public String toString(){
		return this.name().toLowerCase();
	}
}
