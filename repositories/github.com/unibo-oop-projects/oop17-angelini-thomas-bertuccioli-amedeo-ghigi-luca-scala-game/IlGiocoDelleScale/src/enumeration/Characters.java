package enumeration;

public enum Characters {
	

	Baghera("Baghera"),
	
	Baloo("Baloo"),
	
	ShereKhan("Shere Khan"),
	
	KingLouie("King Louie");
	
	
	private final String character;
	
	
	Characters(final String character){
		this.character = character;
	}
	
	
	public String toString() {
		return this.character;
	}
}

