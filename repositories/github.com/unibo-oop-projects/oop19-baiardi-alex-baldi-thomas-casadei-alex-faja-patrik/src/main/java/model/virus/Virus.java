package model.virus;

/**
 * Interface that models the virus.
 */
public interface Virus {

    /**
     * Gets the infectivity rate of the virus.
     * 
     * @return virus' infectivity
     * 
     */
     double getInfectivity();

    /**
     * Gets the mortality rate of virus.
     * 
     * @return virus' mortality
     */
     double getMortality();

     /**
      * Gets the incubation period of this virus.
      *
      * @return virus' maximum incubation period
      */
     int getIncubationPeriod();

     /**
      * Gets a clone of the current Virus with the same values.
      * (The incubation and recovery period is recalculated) 
      * 
      * @return a clone of the Virus
      */
     Virus duplicate();

     /**
      * Gets the recovery period of this virus.
      * 
      * @return virus' recovery period
      */
     int getRecoveryPeriod();
}
