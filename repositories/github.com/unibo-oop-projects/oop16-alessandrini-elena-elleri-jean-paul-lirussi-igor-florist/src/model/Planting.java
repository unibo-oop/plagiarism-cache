package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

/**
 * interface of the "planting a plant" act. 
 */
public interface Planting extends Serializable {

    /**
     * 
     * @return the Employee who plants the plant
     */
    Employee getEmployee();

    /**
     * 
     * @return the plant planted.
     */
    Plant getPlant();

    /**
     * 
     * @return the dates of the this planting
     */
    Pair<LocalDate, LocalDate> getDates();

    /**
     * 
     * @return the period between the dates of the planting
     */
    Period getPeriod();

    /**
     * 
     * @return the period (the time expired) from the first planting until now
     */
    Period getTime();
}
