package utils.constants;

public enum Shift {
	MORNING("Mattina"),
	AFTERNOON("Pomeriggio");
	
	
    private final String shift;
    Shift(String shift) { this.shift = shift; }
    public String getValue() { return this.shift; }
			
}
