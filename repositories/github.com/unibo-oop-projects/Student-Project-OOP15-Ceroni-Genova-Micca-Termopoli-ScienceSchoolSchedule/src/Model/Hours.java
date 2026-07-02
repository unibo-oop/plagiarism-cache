package Model;

/**
 * This class implements a list of the various teaching hours during the day
 * 
 * @author Francesco Ceroni
 * 
 */

public enum Hours {
   
   PERIOD1("09:00-10:00"),
   PERIOD2("10:00-11:00"),
   PERIOD3("11:00-12:00"),
   PERIOD4("12:00-13:00"),
   PERIOD5("13:00-14:00"),
   PERIOD6("14:00-15:00"),
   PERIOD7("15:00-16:00"),
   PERIOD8("16:00-17:00"),
   PERIOD9("17:00-18:00");
    
    private final String y;
    
    private Hours(String l){
        this.y = l;
    }
    
    public String getValue(){
        return this.y;
    }
    
}
