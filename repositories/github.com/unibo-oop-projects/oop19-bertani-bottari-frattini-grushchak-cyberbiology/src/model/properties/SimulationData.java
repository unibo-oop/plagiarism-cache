package model.properties;

import java.util.ResourceBundle;

/**
 * 
 * Interface for access to all properties of simulation.
 *
 */
public interface SimulationData {
    /**
     * @return current language bundle.
     */
    ResourceBundle getResourseBundle();
}
