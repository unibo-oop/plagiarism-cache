package utils.constants;

public enum Workmonth {
	JANUARY ("January"),
	FEBRUARY ("February"),
	MARCH ("March"),
	APRIL ("April"),
	MAY ("May"),
	JUNE ("June"),
	JULY ("July"),
	AUGUST ("August"),
	SEPTEMBER ("September"),
	OCTOBER ("October"),
	NOVEMBER ("November"),
	DECEMBER ("December");
	
    private final String month;
    Workmonth(String month) { this.month = month; }
    public String getValue() { return this.month; }
			
}
