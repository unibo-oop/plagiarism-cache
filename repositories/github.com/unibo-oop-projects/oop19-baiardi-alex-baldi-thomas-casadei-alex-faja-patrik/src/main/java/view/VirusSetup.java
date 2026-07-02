package view;

import java.util.Collection;
import controller.VirusManager;

/**
 * View controller of the virus section.
 */
public interface VirusSetup {

    /**
     * Get the name of the virus.
     * @return
     *  The name of the virus
     */
    String getName();

    /**
     * @return
     *  The infectivity rate of the virus
     */
    double getInfectivity();

    /**
     * @return
     *  The minimum duration of incubation
     */
    int getMinIncubationPeriod();

    /**
     * @return
     *  The maximum duration of incubation
     */
    int getMaxIncubationPeriod();

    /**
     * @return
     *  The minimum duration of recovery
     */
    int getMinRecoveryPeriod();

    /**
     * @return
     *  The maximum duration of recovery
     */
    int getMaxRecoveryPeriod();

    /**
     * @return
     *  The mortality rate of the virus
     */
    double  getMortality();

    /**
     * @param infectivity
     *  The infectivity rate
     */
    void setInfectity(double infectivity);

    /**
     * @param minIncubationPeriod
     *  The minimum duration of incubation 
     * @param maxIncubationPeriod
     *  The maximum duration of incubation
     */
    void setIncubationPeriod(int minIncubationPeriod, int maxIncubationPeriod);

    /**
     * @param minRecoveryPeriod
     *  The minimum duration of recovery
     * @param maxRecoveryPeriod
     *  The maximum duration of incubation
     */
    void setRecoveryPeriod(int minRecoveryPeriod, int maxRecoveryPeriod);

    /**
     * @param mortality
     *  The mortality rate
     */
    void setMortality(double mortality);

    /**
     * @param name
     *  The virus name
     */
    void setName(String name);

    /**
     * Sets the controller to communicate events.
     * 
     * @param vm controller that manage virus
     */
    void setController(VirusManager vm);

    /**
     * Load all the names of the saved virus.
     * 
     * @param virusNames
     *  Name of the saved virus
     */
    void uploadVirus(Collection<String> virusNames);
}
