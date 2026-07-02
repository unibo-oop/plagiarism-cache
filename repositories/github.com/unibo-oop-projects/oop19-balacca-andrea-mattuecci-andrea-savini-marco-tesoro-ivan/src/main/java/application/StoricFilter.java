package application;

import java.util.Date;
import java.util.Map;

/**
 * @author marco
 *
 */
public interface StoricFilter {

    /**
     * @param catena
     * @param idhotel
     * @param idDisp
     * @param dateStart
     * @param dateEnd
     * @return
     */
    Map<String, Float> searchload (final Catena catena, final String idhotel, final String idDisp, final Date dateStart, final Date dateEnd);
    
    
    /**
     * @param catena
     * @param idhotel
     * @param idDisp
     * @param dateStart
     * @param dateEnd
     * @return
     */
    Map<String, Float> searchdump (final Catena catena, final String idhotel, final String idDisp, final Date dateStart, final Date dateEnd);
    
}
