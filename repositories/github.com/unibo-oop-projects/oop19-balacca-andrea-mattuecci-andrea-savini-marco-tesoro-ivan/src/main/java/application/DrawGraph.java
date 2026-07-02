package application;

import java.util.Date;
import java.util.HashMap;
import java.util.NavigableMap;

public interface DrawGraph {
	
	/**
     * @param start
     * @param end
     * @param ID
     * @return i consumi per disegnare il grafico
     */
	public NavigableMap<Date, HashMap<String, Float>> getGraphConsumi(Date start, Date end, String ID) throws DateNotFound;
	
	/**
     * @param start
     * @param end
     * @param ID
     * @param nClienti
     * @return i consumi per disegnare il grafico
     */
	public NavigableMap<Date, HashMap<String, Float>> getGraphPrevisioni(Date start, Date end, String ID, int nClienti) throws DateNotFound;

}
