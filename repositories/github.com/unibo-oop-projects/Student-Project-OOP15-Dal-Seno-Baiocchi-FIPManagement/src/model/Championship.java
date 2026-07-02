package model;

import java.io.Serializable;
/**
 * Define a championship
 * @author lucadalseno
 *
 */
public interface Championship extends Serializable {
    /**
     * 
     * @return the division of the Championship
     */
    Division getDivision();
    /**
     * 
     * @return the zone of a Championship
     */
    Zone getZone();
}
