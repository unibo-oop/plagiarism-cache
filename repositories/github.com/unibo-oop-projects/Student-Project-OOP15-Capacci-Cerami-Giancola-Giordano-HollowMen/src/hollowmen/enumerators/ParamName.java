package hollowmen.enumerators;

public enum ParamName {

	ATTACK,
	DEFENSE,
	HPMAX,
	HP,
	ATTACKSPEED,
	ATTACKRANGE,
	MOVSPEED,
	DURATION,
	COOLDOWN;
	
	@Override
	public String toString(){
		return this.name().toLowerCase();
	}
	
}
