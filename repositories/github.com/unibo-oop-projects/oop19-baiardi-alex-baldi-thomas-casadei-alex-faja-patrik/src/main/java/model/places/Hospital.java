package model.places;

/**
 * Interface that models the place where ill people are hospitalized.
 */
public interface Hospital extends Place {

    /**
     * Removes people from the hospital giving them the outcome.
     * 
     * @param time the actual instant
     * @return Outcome of people hospitalization
     */
    HospitalizationOutcome exitWithOutcome(int time);

    /**
     * Checks if there are people in the hospital.
     * 
     * @return True if one or more people are in the hospital 
     *         False if nobody is in the hospital
     */
    boolean isAnyoneInHospital();
}
