package pro_ristorante_oop;

public class SurplusSeatsExceptions extends Exception{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String msg="there's a surplus of seats";
	 void Exception(String msg){
	    	System.err.println(msg);
	}

}
