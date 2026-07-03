package urls;
//Enumeration for characters' URLs
public enum CharacterURLImage {
	HENCHMAN("res/images/Henchman.gif"),
	METEORITE("res/images/Meteorite.gif"),
	NITRO("res/images/Nitro.gif"),
	TNT("res/images/TNT.gif"),
	ROCKBALL("res/images/Palla.gif"),
	WUMPAFRUIT("res/images/WumpaFruit.gif"),
	AKUAKU("res/images/AkuAku.gif"),
	LIFE("res/images/Life.gif");
	
	
	private String URL;
	
	private CharacterURLImage(final String URL) {
		this.URL=URL;
	}
	
	public String getURL() {
		return this.URL;
	}
}
