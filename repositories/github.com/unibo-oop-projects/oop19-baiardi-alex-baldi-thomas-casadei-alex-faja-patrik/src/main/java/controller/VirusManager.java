package controller;

import model.virus.Virus;

/**
 * Control the virus data. 
 */
public interface VirusManager {

    /**
     *
     * Read all the virus from the virus file.
     */
    void readVirus();

    /**
     * Charge all the selected virus data.
     * @param virusCode
     *                  The Virus Id code.
     */
    void loadVirusSettings(String virusCode);

    /**
     * Save the virus setup on the file.
     */
    void saveVirus();

    /**
     * @return
     *      The Virus.
     */
    Virus initializeVirus();

}
