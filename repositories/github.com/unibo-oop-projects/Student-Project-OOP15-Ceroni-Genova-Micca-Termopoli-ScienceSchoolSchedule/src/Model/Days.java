package Model;

/**
 * This class implements the list of days of the week
 * 
 * @author Francesco Ceroni
 * 
 */

public enum Days {
        MONDAY(0, "Lunedi'"),
        TUESDAY(1, "Martedi'"), 
        WEDNESDAY(2, "Mercoledi'"), 
        THURSDAY(3, "Giovedi'"), 
        FRIDAY(4, "Venerdi'");
    
    private final Integer y;
    private final String s;
    
    private Days(Integer l, String m){
        this.y = l;
        this.s = m;
    }
    
    public Integer getValue(){
        return this.y;
    }
    
    public String getString(){
    	return this.s;
    }
}
