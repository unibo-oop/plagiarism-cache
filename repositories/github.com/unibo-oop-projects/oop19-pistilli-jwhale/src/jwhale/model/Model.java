package jwhale.model;

import jwhale.model.connector.ConnectionException;
import jwhale.model.connector.DaemonResponseException;
/**
 * Model interface. Allows complete management of Docker environment.
 *
 */
public interface Model {
    /**
     * Create Environment.
     * @param url
     *          environment url.
     * @param port
     *          enironment port.
     * @param name
     *          environment name.
     * @throws ConnectionException
     * @throws DaemonResponseException
     */
    void createEnv(String url, String port, String name) 
            throws ConnectionException, DaemonResponseException;
    /**
     * Remove a specific environment.
     * @param envName
     *          environment to remove.
     */
    void removeEnv(String envName);
    /**
     * Get environment instance.
     * @param envName
     *          environment name.
     * @return
     *          environment instance.
     */
    Environment getEnv(String envName);

}
