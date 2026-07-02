package application;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author marco
 *
 */
public class StoricFilterImpl implements StoricFilter {

    /**
     * @param catena the container of typology
     * @param idhotel the name of the hotel
     * @param idDisp the name of the dispensa
     * @param dateStart the search start date
     * @param dateEnd the search end date 
     * @return new Map<String, Float>
     */
	public Map<String, Float> searchload (final Catena catena, final String idhotel, final String idDisp, final Date dateStart, final Date dateEnd){

		HashMap<String, Float> tmpC = new HashMap<>();
		
		if (catena.ottieniUnAlbergo(idhotel).isPresent()
				&& catena.ottieniUnAlbergo(idhotel).get().ottieniUnaDispensa(idDisp).isPresent()) {
			for (final var x : catena.ottieniUnAlbergo(idhotel).get().ottieniUnaDispensa(idDisp).get().getCarichi().keySet()) {			
				if (x.after(dateStart) && x.before(dateEnd)) {
					tmpC.putAll(catena.ottieniUnAlbergo(idhotel).get().ottieniUnaDispensa(idDisp).get().getCarichi().get(x));
				}
			}
		}
		
		return tmpC;
	}


    /**
     * @param catena the container of typology
     * @param idhotel the name of the hotel
     * @param idDisp the name of the dispensa
     * @param dateStart the search start date
     * @param dateEnd the search end date 
     * @return new Map<String, Float>
     */
	public Map<String, Float> searchdump(final Catena catena, final String idhotel, final String idDisp, final Date dateStart, final Date dateEnd) {

		HashMap<String, Float> tmpS = new HashMap<>();
		if (catena.ottieniUnAlbergo(idhotel).isPresent()
				&& catena.ottieniUnAlbergo(idhotel).get().ottieniUnaDispensa(idDisp).isPresent()) {
			
			for (final var x : catena.ottieniUnAlbergo(idhotel).get().ottieniUnaDispensa(idDisp).get().getScarichi().keySet()) {
				if (x.after(dateStart) && x.before(dateEnd)) {				
					tmpS.putAll(catena.ottieniUnAlbergo(idhotel).get().ottieniUnaDispensa(idDisp).get().getScarichi().get(x));
				}
			}	
		}
		return tmpS;
	}

}
