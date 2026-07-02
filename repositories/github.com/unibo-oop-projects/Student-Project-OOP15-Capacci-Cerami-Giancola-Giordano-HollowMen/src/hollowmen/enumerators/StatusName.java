package hollowmen.enumerators;

public enum StatusName {

	RECHARGE,
	GHOST;
	
	@Override
	public String toString(){
		return this.name().toLowerCase();
	}
	
}
