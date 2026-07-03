package model;

public enum Belt {
	
	BIANCA("Bianca",0),
	BIANCAGIALLA("Bianco-gialla",1),
	GIALLA("Gialla",2),
	GIALLAVERDE("Giallo-verde",3),
	VERDE("Verde",4),
	VERDEBLU("Verde-blu",5),
	BLU("Blu",6),
	BLUROSSA("Blu-rossa",7),
	ROSSA("Rossa",8),
	ROSSANERA("Rossa-nera",9);
		
	private final int value;
    private final String description;
    
    Belt(final String description,final int value){
    	this.value = value;
    	this.description = description;
    }
    public int getValue(){
    	return this.value;
    }
    public String toString(){
    	return this.description;
    }
}
